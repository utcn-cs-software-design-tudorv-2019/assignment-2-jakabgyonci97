package ass2.ass2;

import ass2.ass2.business.AdminService;
import ass2.ass2.business.LoginService;
import ass2.ass2.business.StudentService;
import ass2.ass2.persistence.entity.PersonalInformation;
import ass2.ass2.persistence.entity.Student;
import ass2.ass2.persistence.entity.UserAccount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Ass2ApplicationTests {
    @Inject
    LoginService loginService;

    @Inject
    StudentService studentService;

    @Inject
    AdminService adminService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testLoginOperation(){

        UserAccount userAccount = new UserAccount("jakabgyonci97","1997jakab","");
        UserAccount validUser = loginService.loginUser(userAccount);
        assert validUser != null : "There was found to user for this username and password";
        System.out.println(validUser);


        Student student;
        if(validUser.getUserType().equals("STUDENT")) {
            student = loginService.loginAsStudent(validUser);
            System.out.println("STUDENT");
            System.out.println(student);
        }
        if(validUser.getUserType().equals("ADMIN")){
            System.out.println("ADMIN");
        }

    }

    @Test
    public void testPersonalInfoView(){
        Student student = new Student(98765,1);
        PersonalInformation pi = studentService.viewPersonalInfo(student);
        assert pi != null : "Personal info section is empty";
        System.out.println(pi);
    }

    @Test
    public void testPersonalInfoCreate(){
        Student student = new Student(78734,2);
        PersonalInformation pi = studentService.viewPersonalInfo(student);
        if(pi == null){
            pi = new PersonalInformation(student.getStudentid(),"Imola","Dobner","CJ231245","297102312");
            studentService.createPersonalInfo(student,pi);
            pi = studentService.viewPersonalInfo(student);

            assert pi!=null:"Personal info section is empty";
        }
        else System.out.println("Student's personal info section already exists");
    }

    @Test
    public void testStudentInfoOperations(){

    }

    @Test
    public void testContactInfoOperations(){

    }

}
