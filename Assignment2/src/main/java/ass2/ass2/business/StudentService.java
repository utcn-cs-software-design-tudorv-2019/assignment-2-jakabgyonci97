package ass2.ass2.business;

import ass2.ass2.business.model.EnrolledCourse;
import ass2.ass2.persistence.entity.*;
import ass2.ass2.persistence.repository.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private static final int FIRSTNAME_LIMIT = 20;
    private static final int LASTNAME_LIMIT = 20;
    private static final int ICN_LIMIT = 8;
    private static final int PNC_LIMIT = 13;
    private static final int GROUP_LIMIT = 6;
    private static final double GRADE_LOWEST_LIMIT = 1.0;
    private static final double GRADE_HIGHEST_LIMIT = 10.0;
    private static final int ADDRESS_LIMIT = 30;
    private static final int PHONE_NUM_LIMIT = 10;
    private static final int ENROLLMENT_KEY_LIMIT = 10;
    private static final String ENROLLMENT_ERROR = "Enrollment key was invalid!";
    private static final String NULL_ERROR = "You have nothing to delete!";

    @Inject
    private PersonalInfoRepository piRepo;

    @Inject
    private ContactInfoRepository ciRepo;

    @Inject
    private StudentInfoRepository siRepo;

    @Inject
    private EnrollmentRepository enrollmentRepository;

    @Inject
    private CourseRepository courseRepository;

    @Inject
    private StudentActivityRepository saRepo;

    private Validator validator;

    public StudentService() {
        validator = new Validator();
    }

    /**
     * create/view/update student's personal information
     */
    public String createPersonalInfo(Student student, PersonalInformation pi) {
        ValidatorResponse v1 = validator.validateFirstName(pi.getFirstName(), FIRSTNAME_LIMIT, Validator.CheckType.CHECK_ALL);
        ValidatorResponse v2 = validator.validateLastName(pi.getLastName(), LASTNAME_LIMIT, Validator.CheckType.CHECK_ALL);
        ValidatorResponse v3 = validator.validateIdentityCardNumber(pi.getIcn(), ICN_LIMIT, Validator.CheckType.CHECK_ALL);
        ValidatorResponse v4 = validator.validatePersonalNumericalCode(pi.getPnc(), PNC_LIMIT, Validator.CheckType.CHECK_ALL);
        if (!v1.isValid()) return v1.getMessage();
        if (!v2.isValid()) return v2.getMessage();
        if (!v3.isValid()) return v3.getMessage();
        if (!v4.isValid()) return v4.getMessage();

        pi.setIdStudent(student.getStudentid());
        piRepo.save(pi);

        logStudentActivity(student.getStudentid(), "CREATE", "Create personal information section");
        return null;
    }

    public PersonalInformation viewPersonalInfo(Student student) {
        return piRepo.findPersonalInformationByIdStudent(student.getStudentid());
    }

    public String updatePersonalInfo(PersonalInformation piOld, PersonalInformation pi) {
        if (piOld == null) return NULL_ERROR;
        ValidatorResponse v1 = validator.validateFirstName(pi.getFirstName(), FIRSTNAME_LIMIT, Validator.CheckType.NO_NULL_CHECK);
        ValidatorResponse v2 = validator.validateLastName(pi.getLastName(), LASTNAME_LIMIT, Validator.CheckType.NO_NULL_CHECK);
        ValidatorResponse v3 = validator.validateIdentityCardNumber(pi.getIcn(), ICN_LIMIT, Validator.CheckType.NO_NULL_CHECK);
        ValidatorResponse v4 = validator.validatePersonalNumericalCode(pi.getPnc(), PNC_LIMIT, Validator.CheckType.NO_NULL_CHECK);
        if (!v1.isValid()) return v1.getMessage();
        if (!v2.isValid()) return v2.getMessage();
        if (!v3.isValid()) return v3.getMessage();
        if (!v4.isValid()) return v4.getMessage();

        PersonalInformation piNew = piOld.clone();
        if (pi.getFirstName() != null && !pi.getFirstName().isEmpty()) piNew.setFirstName(pi.getFirstName());
        if (pi.getLastName() != null && !pi.getLastName().isEmpty()) piNew.setLastName(pi.getLastName());
        if (pi.getIcn() != null && !pi.getIcn().isEmpty()) piNew.setIcn(pi.getIcn());
        if (pi.getPnc() != null && !pi.getPnc().isEmpty()) piNew.setPnc(pi.getPnc());

        piRepo.delete(piOld);
        piRepo.save(piNew);

        logStudentActivity(piOld.getIdStudent(), "UPDATE", "Update personal information section");
        return null;
    }

    /**
     * create/view/update/delete student's student information
     */
    public String createStudentInfo(Student student, StudentInformation si) {
        ValidatorResponse v1 = validator.validatePersonalNumericalCode(si.getStudGroup(), GROUP_LIMIT, Validator.CheckType.CHECK_ALL);
        ValidatorResponse v2 = validator.validateScholarShipState(si.getScholarShipState(), Validator.CheckType.CHECK_ALL);
        ValidatorResponse v3 = validator.validateDoubleNumber(Double.toString(si.getGradeAvrg()), GRADE_LOWEST_LIMIT, GRADE_HIGHEST_LIMIT, Validator.CheckType.CHECK_ALL);
        if (!v1.isValid()) return v1.getMessage();
        if (!v2.isValid()) return v2.getMessage();
        if (!v3.isValid()) return v3.getMessage();
        if (!StudentInformation.isValidScholarShip(si.getScholarShipState())) return "INVALID SCHOLARSHIP STATUS!";

        si.setIdStudent(student.getStudentid());
        siRepo.save(si);

        logStudentActivity(student.getStudentid(), "CREATE", "Create student information section");
        return null;
    }

    public StudentInformation viewStudentInfo(Student student) {
        return siRepo.findStudentInformationByIdStudent(student.getStudentid());
    }

    public String updateStudentInfo(StudentInformation siOld, StudentInformation si) {
        if (siOld == null) return NULL_ERROR;
        ValidatorResponse v1 = validator.validatePersonalNumericalCode(si.getStudGroup(), GROUP_LIMIT, Validator.CheckType.NO_NULL_CHECK);
        ValidatorResponse v2 = validator.validateScholarShipState(si.getScholarShipState(), Validator.CheckType.NO_NULL_CHECK);
        ValidatorResponse v3 = validator.validateDoubleNumber(Double.toString(si.getGradeAvrg()), GRADE_LOWEST_LIMIT, GRADE_HIGHEST_LIMIT, Validator.CheckType.NO_NULL_CHECK);

        if (!v1.isValid()) return v1.getMessage();
        if (!v2.isValid()) return v2.getMessage();
        if (!v3.isValid()) return v3.getMessage();

        StudentInformation siNew = siOld.clone();
        if (si.getStudGroup() != null && !si.getStudGroup().isEmpty()) siNew.setStudGroup(si.getStudGroup());
        if (si.getScholarShipState() != null && !si.getScholarShipState().isEmpty()) {
            siNew.setScholarShipState(si.getScholarShipState());
        }
        if (si.getGradeAvrg() != siOld.getGradeAvrg()) siNew.setGradeAvrg(si.getGradeAvrg());

        siRepo.delete(siOld);
        siRepo.save(siNew);

        logStudentActivity(siOld.getIdStudent(), "UPDATE", "Update student information section");
        return null;
    }

    public String deleteStudentInfo(StudentInformation si) {
        if (si == null) return NULL_ERROR;
        siRepo.delete(si);
        logStudentActivity(si.getIdStudent(), "DELETE", "Delete student information section");
        return null;
    }

    /**
     * create/view/update/delete student's contact information
     */
    public String createContactInfo(Student student, ContactInformation ci) {
        System.out.println(ci);

        ValidatorResponse v1 = validator.validateAddress(ci.getAddress(), ADDRESS_LIMIT, Validator.CheckType.CHECK_ALL);
        ValidatorResponse v2 = validator.validatePersonalNumericalCode(ci.getPhoneNumber(), PHONE_NUM_LIMIT, Validator.CheckType.CHECK_ALL);
        ValidatorResponse v3 = validator.validateEmailAddress(ci.getEmailAddress(), ADDRESS_LIMIT, Validator.CheckType.CHECK_ALL);
        if (!v1.isValid()) return v1.getMessage();
        if (!v2.isValid()) return v2.getMessage();
        if (!v3.isValid()) return v3.getMessage();

        ciRepo.save(ci);

        logStudentActivity(student.getStudentid(), "CREATE", "Create contact information section");
        return null;
    }

    public ContactInformation viewContactInfo(Student student) {
        return ciRepo.findByIdStudent(student.getStudentid());
    }

    public String updateContactInfo(ContactInformation ciOld, ContactInformation ci) {
        if (ciOld == null) return NULL_ERROR;
        ValidatorResponse v1 = validator.validateAddress(ci.getAddress(), ADDRESS_LIMIT, Validator.CheckType.NO_NULL_CHECK);
        ValidatorResponse v2 = validator.validatePersonalNumericalCode(ci.getPhoneNumber(), PHONE_NUM_LIMIT, Validator.CheckType.NO_NULL_CHECK);
        ValidatorResponse v3 = validator.validateEmailAddress(ci.getEmailAddress(), ADDRESS_LIMIT, Validator.CheckType.NO_NULL_CHECK);
        if (!v1.isValid()) return v1.getMessage();
        if (!v2.isValid()) return v2.getMessage();
        if (!v3.isValid()) return v3.getMessage();

        ContactInformation ciNew = ciOld.clone();
        if (ci.getAddress() != null && !ci.getAddress().isEmpty()) ciNew.setAddress(ci.getAddress());
        if (ci.getPhoneNumber() != null && !ci.getPhoneNumber().isEmpty()) ciNew.setPhoneNumber(ci.getPhoneNumber());
        if (ci.getEmailAddress() != null && !ci.getEmailAddress().isEmpty()) ciNew.setEmailAddress(ci.getEmailAddress());

        ciRepo.delete(ciOld);
        ciRepo.save(ciNew);
        logStudentActivity(ciOld.getIdStudent(), "UPDATE", "Update contact information section");
        return null;
    }

    public String deleteContactInfo(ContactInformation ci) {
        if (ci == null) return NULL_ERROR;
        ciRepo.delete(ci);
        logStudentActivity(ci.getIdStudent(), "DELETE", "Delete contact information section");
        return null;
    }

    /**
     * process student enrollment - search for course, enroll student for course
     */
    public Course searchForCourse(String courseSession) {
        if (!validator.validateCourseSearch(courseSession, Validator.CheckType.CHECK_ALL).isValid()) return null;

        String[] splitText = courseSession.split("-");
        if (splitText.length != 2) return null;
        String courseName = splitText[0];
        String session = splitText[1];

        return courseRepository.findByNameAndSession(courseName, session);
    }

    public String processEnrollment(Student student, Course course, String enrollmentKey) {
        ValidatorResponse vR = validator.validatePassword(enrollmentKey, ENROLLMENT_KEY_LIMIT, Validator.CheckType.CHECK_ALL);
        if (!vR.isValid()) return vR.getMessage();
        if (enrollmentKey.compareTo(course.getEnrollmentKey()) != 0) return ENROLLMENT_ERROR;

        Enrollment e = new Enrollment(course.getCourseId(), student.getStudentid(), 0.0);
        enrollmentRepository.save(e);
        logStudentActivity(student.getStudentid(), "ENROLLMENT", "Student enrollment to " + course.getName());
        return null;
    }

    public List<EnrolledCourse> findEnrolledCourses(Student student) {
        List<Enrollment> enrollments = enrollmentRepository.findAllByIdStudent(student.getStudentid());
        List<EnrolledCourse> enrolledCourses = new ArrayList<>();
        for (Enrollment itr : enrollments) {
            Course course = courseRepository.findCourseByCourseId(itr.getIdCourse());
            EnrolledCourse e = new EnrolledCourse(course.getName(), course.getSession(), course.getExamDate(), itr.getFinalGrade());
            enrolledCourses.add(e);
        }
        return enrolledCourses;
    }

    private void logStudentActivity(int idStudent, String activityType, String description) {
        StudentActivity sa = new StudentActivity(idStudent, Date.valueOf(LocalDate.now()), activityType, description);
        saRepo.save(sa);
    }

}
