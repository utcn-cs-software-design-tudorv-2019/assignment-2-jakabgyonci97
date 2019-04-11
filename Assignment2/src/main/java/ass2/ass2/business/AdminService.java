package ass2.ass2.business;

import ass2.ass2.business.model.StudentProfile;
import ass2.ass2.persistence.entity.*;
import ass2.ass2.persistence.repository.*;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service()
public class AdminService {
    private static final int ID_LOWEST_LIMIT = 1000;
    private static final int ID_HIGHEST_LIMIT = 600000;
    private static final int FIRSTNAME_LIMIT = 20;
    private static final int LASTNAME_LIMIT = 20;
    private static final int GROUP_LIMIT = 6;
    private static final double GRADE_LOWEST_LIMIT = 1.0;
    private static final double GRADE_HIGHEST_LIMIT = 10.0;
    private static final String STUDENT_INFO_ERROR = "This student already has student information section!";
    private static final String PERSONAL_INFO_ERROR = "This student already has personal information section";
    private static final String DATABASE_ERROR = "Database error!Sorry Boss!";

    @Inject
    private StudentRepository studentRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private PersonalInfoRepository piRepo;

    @Inject
    private StudentInfoRepository siRepo;

    @Inject
    private StudentActivityRepository saRepo;

    private Validator validator;

    public AdminService(){
        validator = new Validator();
    }

    /**create/view/update/delete student profiles
     *observation: creating student:   1.creating a full new student: user, student
     *                                 2.creating student information for existing student*/
    public String createStudent(StudentProfile studentProfile){
        ValidatorResponse v1 = validator.validateIntegerNumber(studentProfile.getIdStudent()+"",ID_LOWEST_LIMIT,ID_HIGHEST_LIMIT,Validator.CheckType.CHECK_ALL);
        if(!v1.isValid()) return v1.getMessage();
        Student student = studentRepository.findStudentByStudentid(studentProfile.getIdStudent());
        if(student == null){
            System.out.println("CREATING NEW STUDENT");

            ValidatorResponse v2 = validator.validateFirstName(studentProfile.getFirstName(),FIRSTNAME_LIMIT,Validator.CheckType.CHECK_ALL);
            if(!v2.isValid()) return v2.getMessage();
            ValidatorResponse v3 = validator.validateLastName(studentProfile.getLastName(),LASTNAME_LIMIT,Validator.CheckType.CHECK_ALL);
            if(!v3.isValid()) return v3.getMessage();

            UserAccount user = new UserAccount(studentProfile.getFirstName()+studentProfile.getLastName(),studentProfile.getIdStudent()+"", UserAccount.ROLES[0]);
            user = userRepository.save(user);
            if(user == null) return DATABASE_ERROR;

            student = new Student(studentProfile.getIdStudent(),user.getUserId());
            studentRepository.save(student);

            PersonalInformation pi = piRepo.findPersonalInformationByIdStudent(student.getStudentid());
            if(pi != null) return PERSONAL_INFO_ERROR;

            pi = new PersonalInformation(student.getStudentid(),studentProfile.getFirstName(),studentProfile.getLastName(),"","");
            piRepo.save(pi);
            return null;
        }
        System.out.println("CREATING NEW STUDENT INFO");
        ValidatorResponse v4 = validator.validatePersonalNumericalCode(studentProfile.getGroup(),GROUP_LIMIT,Validator.CheckType.CHECK_ALL);
        if(!v4.isValid()) return v4.getMessage();
        ValidatorResponse v5 = validator.validateDoubleNumber(studentProfile.getAverage()+"",GRADE_LOWEST_LIMIT,GRADE_HIGHEST_LIMIT,Validator.CheckType.CHECK_ALL);
        if(!v5.isValid()) return v5.getMessage();
        ValidatorResponse v6 = validator.validateScholarShipState(studentProfile.getScholarShipState(),Validator.CheckType.CHECK_ALL);
        if(!v6.isValid()) return v6.getMessage();
        if(!StudentInformation.isValidScholarShip(studentProfile.getScholarShipState())) return "Invalid scholarship status!";

        StudentInformation si = siRepo.findStudentInformationByIdStudent(student.getStudentid());
        if(si != null) return STUDENT_INFO_ERROR;

        si = new StudentInformation(student.getStudentid(),studentProfile.getGroup(),studentProfile.getScholarShipState(), studentProfile.getAverage());
        siRepo.save(si);
        return null;
    }
    public List<StudentProfile> viewStudents(){
        List<StudentProfile> studentProfiles = new ArrayList<>();
        List<Student> students = studentRepository.findAll();

        StudentInformation si;
        PersonalInformation pi;
        for(Student student: students){
            si = siRepo.findStudentInformationByIdStudent(student.getStudentid());
            pi = piRepo.findPersonalInformationByIdStudent(student.getStudentid());
            StudentProfile sp = new StudentProfile();
            sp.setIdStudent(student.getStudentid());
            if(pi != null){
                sp.setFirstName(pi.getFirstName());
                sp.setLastName(pi.getLastName());
                if(si != null){
                    sp.setGroup(si.getGroup());
                    sp.setAverage(si.getGradeAvrg());
                    sp.setScholarShipState(si.getScholarShipState());
                }
            }
            studentProfiles.add(sp);
        }
        return studentProfiles;
    }
    /**observation: neither admin can update student id -> primary key of table*/
    public String updateStudent(StudentProfile student,StudentInformation studentInformation){
        ValidatorResponse v3 = validator.validatePersonalNumericalCode(studentInformation.getGroup(),GROUP_LIMIT,Validator.CheckType.NO_NULL_CHECK);
        if(!v3.isValid()) return v3.getMessage();
        ValidatorResponse v4 = validator.validateDoubleNumber(studentInformation.getGradeAvrg()+"",GRADE_LOWEST_LIMIT,GRADE_HIGHEST_LIMIT,Validator.CheckType.NO_NULL_CHECK);
        if(!v4.isValid()) return v4.getMessage();
        ValidatorResponse v5 = validator.validateScholarShipState(studentInformation.getScholarShipState(),Validator.CheckType.NO_NULL_CHECK);
        if(!v5.isValid()) return v5.getMessage();
        if(!StudentInformation.isValidScholarShip(studentInformation.getScholarShipState())) return "Invalid scholarship status!";

        StudentInformation siOld = siRepo.findStudentInformationByIdStudent(student.getIdStudent());
        if(siOld == null) return "You cannot update something that does not exist ... supped!";

        StudentInformation siNew = siOld.clone();

        if(studentInformation.getGroup() != null && !studentInformation.getGroup().isEmpty()) siNew.setGroup(studentInformation.getGroup());
        if(studentInformation.getScholarShipState() != null && !studentInformation.getScholarShipState().isEmpty()) siNew.setScholarShipState(studentInformation.getScholarShipState());
        if(siOld.getGradeAvrg() != studentInformation.getGradeAvrg()) siNew.setGradeAvrg(studentInformation.getGradeAvrg());

        siRepo.delete(siOld);
        siRepo.save(siNew);
        return null;
    }
    /**observation: delete operation refers only to student information, NOT whole student profile*/
    public String deleteStudent(StudentProfile student){
        StudentInformation si = siRepo.findStudentInformationByIdStudent(student.getIdStudent());
        if(si == null) return "You cannot delete something that does not exist...supped!";
        siRepo.delete(si);
        return null;
    }


    /**generate reports based on student's activity for a time period*/
    public StudentProfile findStudent(String idStudent){
        ValidatorResponse vr = validator.validateIntegerNumber(idStudent,ID_LOWEST_LIMIT,ID_HIGHEST_LIMIT,Validator.CheckType.NO_NULL_CHECK);
        if(!vr.isValid()) return null;
        Student student = studentRepository.findStudentByStudentid(Integer.parseInt(idStudent));
        if(student == null) return null;

        StudentInformation si = siRepo.findStudentInformationByIdStudent(student.getStudentid());
        PersonalInformation pi = piRepo.findPersonalInformationByIdStudent(student.getStudentid());
        if(si == null) return null;
        if(pi == null) return null;

        StudentProfile sp = new StudentProfile(student.getStudentid(),pi.getFirstName(),pi.getLastName(),si.getGroup(),si.getScholarShipState(),si.getGradeAvrg());
        return sp;
    }
    public List<StudentActivity> filterActivities(StudentProfile student, LocalDate startDate, LocalDate endDate){
        if(student == null) return null;
        if(startDate.isAfter(endDate)) return null;

        List<StudentActivity> studentActivities = saRepo.findAllByIdStudent(student.getIdStudent());
        List<StudentActivity> filteredActivities = new ArrayList<>();
        for(StudentActivity itr: studentActivities){
            LocalDate activityDate = itr.getActivityDate().toLocalDate();
            if(activityDate.isAfter(startDate) && activityDate.isBefore(endDate))
                filteredActivities.add(itr);
        }
        return filteredActivities;
    }
    public List<StudentActivity> viewActivities(StudentProfile student){
        if(student == null) {
            List<StudentActivity> studentActivities = saRepo.findAll();
            List<StudentActivity> filteredActivities = new ArrayList<>();
            for(StudentActivity itr: studentActivities){
                filteredActivities.add(itr);
            }
            return filteredActivities;
        }
        List<StudentActivity> studentActivities = saRepo.findAllByIdStudent(student.getIdStudent());
        List<StudentActivity> filteredActivities = new ArrayList<>();
        for(StudentActivity itr: studentActivities){
            filteredActivities.add(itr);
        }
        return filteredActivities;
    }
}
