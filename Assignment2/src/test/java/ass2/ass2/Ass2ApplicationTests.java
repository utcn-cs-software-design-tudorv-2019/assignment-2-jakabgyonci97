package ass2.ass2;

import ass2.ass2.business.AdminService;
import ass2.ass2.business.LoginService;
import ass2.ass2.business.StudentService;
import ass2.ass2.persistence.entity.*;
import ass2.ass2.persistence.repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Ass2ApplicationTests {
    @Inject
    UserRepository userRepository;
    @Inject
    StudentRepository studentRepository;
    @Inject
    StudentInfoRepository studentInfoRepository;
    @Inject
    StudentActivityRepository studentActivityRepository;
    @Inject
    ContactInfoRepository contactInfoRepository;
    @Inject
    PersonalInfoRepository personalInfoRepository;
    @Inject
    CourseRepository courseRepository;
    @Inject
    EnrollmentRepository enrollmentRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testUserRepository(){
        UserAccount userAccount = new UserAccount("mariusHei98","12345","STUDENT");
        System.out.println(userAccount);
        userRepository.save(userAccount);
        userAccount = userRepository.findByUserNameAndUserPassword(userAccount.getUserName(),userAccount.getUserPassword());
        System.out.println(userAccount);
        assert userAccount != null : "NullPointerException";
        userRepository.delete(userAccount);
        userAccount = userRepository.findByUserId(userAccount.getUserId());
        System.out.println(userAccount);
        assert userAccount == null : "Should be NullPointerException";
    }

    @Test
    public void testStudentRepository(){
        UserAccount userAccount = new UserAccount("mariusHei98","12345","STUDENT");
        userRepository.save(userAccount);
        userAccount = userRepository.findByUserNameAndUserPassword(userAccount.getUserName(),userAccount.getUserPassword());

        Student student = new Student(93466,userAccount.getUserId());
        studentRepository.save(student);
        student = studentRepository.findStudentByStudentid(student.getStudentid());
        assert student != null : "NullPointerException";
        studentRepository.delete(student);
        student = studentRepository.findStudentByStudentid(student.getStudentid());
        assert student == null : "Should be NullPointerException";
    }

    @Test
    public void testStudentInfoRepository(){
        StudentInformation si = studentInfoRepository.findStudentInformationByIdStudent(98765);
        System.out.println(si);
        si = new StudentInformation(98765,"30233","ACADEMIC",9.46);
        studentInfoRepository.save(si);
        si = studentInfoRepository.findStudentInformationByIdStudent(si.getIdStudent());
        assert si != null : "NullPointerException";
        studentInfoRepository.delete(si);
        si = studentInfoRepository.findStudentInformationByIdStudent(si.getIdStudent());
        assert si == null : "Should be NullPointerException";
    }

    @Test
    public void testStudentActivityRepository(){
        List<StudentActivity> studentActivities = studentActivityRepository.findAllByIdStudent(98765);

        StudentActivity studentActivity = new StudentActivity(98765, Date.valueOf(LocalDate.now()),"CREATE","CREATED SOMETHING");
        studentActivityRepository.save(studentActivity);

        studentActivities = studentActivityRepository.findAllByIdStudent(98765);
        assert !studentActivities.isEmpty() : "Empty list";

        studentActivity = studentActivities.get(0);
        studentActivityRepository.delete(studentActivity);

        studentActivities = studentActivityRepository.findAllByIdStudent(98765);
        assert studentActivities.isEmpty() : "Should be empty list";
    }

    @Test
    public void testContactInfoRepository(){
        ContactInformation ci = contactInfoRepository.findByIdStudent(98765);
        System.out.println(ci);

        ci = new ContactInformation(98765,"Str. Dorului Nr.32", "0740697070","jakabgyi97@yahoo.com");
        contactInfoRepository.save(ci);

        ci = contactInfoRepository.findByIdStudent(98765);
        assert ci != null : "NullPointerException";

        contactInfoRepository.delete(ci);
        ci = contactInfoRepository.findByIdStudent(98765);
        assert ci == null : "Should be NullPointerException";
    }

    @Test
    public void testPersonalInfoRepository(){
        PersonalInformation pi = personalInfoRepository.findByIdStudent(90712);
        System.out.println(pi);

        pi = new PersonalInformation(90712,"Marton","Belasi","CJ121312","1971023129090");
        personalInfoRepository.save(pi);

        pi = personalInfoRepository.findByIdStudent(90712);
        assert pi != null : "NullPointerException";

        personalInfoRepository.delete(pi);
        pi = personalInfoRepository.findByIdStudent(90712);
        assert pi == null : "Should be NullPointerException";
    }

    @Test
    public void testEnrollmentRepository(){
        List<Enrollment> enrollment = enrollmentRepository.findAllByIdStudent(98765);
    }

    @Test
    public void testCourseRepository(){
        Course course = courseRepository.findByNameAndSession("LFT","2018/2019");
        System.out.println(course);
    }



}
