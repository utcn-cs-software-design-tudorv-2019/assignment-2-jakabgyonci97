package ass2.ass2.controller;

import ass2.ass2.business.StudentService;
import ass2.ass2.business.model.EnrolledCourse;
import ass2.ass2.persistence.entity.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping("/studentProfile")
public class StudentController {

    @Inject
    private StudentService studentService;
    private int studentId = 98765;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView viewStudentProfile(@RequestParam(value = "student") Student student) {
        ModelAndView mav = new ModelAndView("studentProfile");
        mav.addObject("student", student);
        return mav;
    }


    /**create/view/update and delete student's student information*/
    @RequestMapping(value = "/studentInformation", method = RequestMethod.GET)
    public ModelAndView viewStudentInformation() {
        Student student = new Student(studentId,1);
        StudentInformation si = studentService.viewStudentInfo(student);
        if(si == null) {si = new StudentInformation();si.setIdStudent(student.getStudentid());}

        ModelAndView mav = new ModelAndView("studentView");
        mav.addObject("studentInfo", si);
        return mav;
    }

    @RequestMapping(value = "/studentInformation", method = RequestMethod.POST)
    public ModelAndView studentInformationOperations(@RequestParam(value = "action") String action,
                                                     @ModelAttribute(value = "studentInfo") StudentInformation newSi) {
        ModelAndView mav;
        Student student = new Student(newSi.getIdStudent(),1);
        if(action.equals("Create")) mav = createStudentInformation(student,newSi);
        else if(action.equals("Update")) mav = updateStudentInformation(student,newSi);
        else mav = deleteStudentInformation(student);
        return mav;
    }

    private ModelAndView createStudentInformation(Student student,StudentInformation newSi){
        StudentInformation si = studentService.viewStudentInfo(student);
        if(si == null){
            studentService.createStudentInfo(student,newSi);
            si = studentService.viewStudentInfo(student);
        }

        ModelAndView mav = new ModelAndView("studentView");
        mav.addObject("studentInfo", si);
        return mav;
    }

    private ModelAndView updateStudentInformation(Student student,StudentInformation newSi){
        StudentInformation si = studentService.viewStudentInfo(student);
        if(si == null){
            ModelAndView mav = new ModelAndView("studentView");
            si = new StudentInformation();
            si.setStudentId(student.getStudentid());
            mav.addObject("studentInfo", si);
            return mav;
        }

        studentService.updateStudentInfo(si,newSi);
        si = studentService.viewStudentInfo(student);

        ModelAndView mav = new ModelAndView("studentView");
        mav.addObject("studentInfo", si);
        return mav;
    }

    private ModelAndView deleteStudentInformation(Student student){
        StudentInformation si = studentService.viewStudentInfo(student);
        if(si != null){
            studentService.deleteStudentInfo(si);
        }

        ModelAndView mav = new ModelAndView("studentView");
        si = new StudentInformation();
        si.setIdStudent(student.getStudentid());
        mav.addObject("studentInfo", si);
        return mav;
    }


    /**create/view and update student's personal information*/
    @RequestMapping(value = "/personalInformation", method = RequestMethod.GET)
    public ModelAndView viewPersonalInformation() {
        Student student = new Student(studentId,1);
        PersonalInformation pi = studentService.viewPersonalInfo(student);
        if(pi == null) {pi = new PersonalInformation();pi.setIdStudent(student.getStudentid());}

        ModelAndView mav = new ModelAndView("personalView");
        mav.addObject("personalInfo", pi);
        return mav;
    }

    @RequestMapping(value = "/personalInformation", method = RequestMethod.POST)
    public ModelAndView personalInformationOperations(@RequestParam(value = "action") String action,
                                                      @ModelAttribute(value = "personalInfo") PersonalInformation newPi) {
        ModelAndView mav;
        Student student = new Student(newPi.getIdStudent(),1);
        if(action.equals("Create")) mav = createPersonalInformation(student,newPi);
        else mav = updatePersonalInformation(student,newPi);
        return mav;
    }

    private ModelAndView createPersonalInformation(Student student, PersonalInformation newPi){
        PersonalInformation pi = studentService.viewPersonalInfo(student);
        if(pi != null){
            ModelAndView mav = new ModelAndView("personalView");
            mav.addObject("personalInfo", pi);
            return mav;
        }
        studentService.createPersonalInfo(student,newPi);
        pi = studentService.viewPersonalInfo(student);

        if(pi == null) pi = new PersonalInformation();
        ModelAndView mav = new ModelAndView("personalView");
        mav.addObject("personalInfo", pi);
        return mav;

    }

    private ModelAndView updatePersonalInformation(Student student, PersonalInformation newPi){
        PersonalInformation pi = studentService.viewPersonalInfo(student);
        if(pi == null){
            ModelAndView mav = new ModelAndView("personalView");
            mav.addObject("personalInfo", new PersonalInformation());
            return mav;
        }
        studentService.updatePersonalInfo(pi,newPi);
        pi = studentService.viewPersonalInfo(student);

        if(pi == null) pi = new PersonalInformation();
        ModelAndView mav = new ModelAndView("personalView");
        mav.addObject("personalInfo", pi);
        return mav;
    }



    /**create/view/update and delete student's contact information*/
    @RequestMapping(value = "/contactInformation", method = RequestMethod.GET)
    public ModelAndView viewContactInformation() {
        Student student = new Student(studentId,1);
        ContactInformation ci = studentService.viewContactInfo(student);
        if(ci == null) {
            ci = new ContactInformation();
            ci.setIdStudent(student.getStudentid());
        }

        ModelAndView mav = new ModelAndView("contactView");
        mav.addObject("contactInfo", ci);
        return mav;
    }

    @RequestMapping(value = "/contactInformation", method = RequestMethod.POST)
    public ModelAndView contactInformationOperations(@RequestParam(value = "action") String action,
                                                     @ModelAttribute(value = "contactInfo") ContactInformation newCi) {
        ModelAndView mav;
        Student student = new Student(newCi.getIdStudent(),1);
        if(action.equals("Create")) mav = createContactInformation(student,newCi);
        else if(action.equals("Update")) mav = updateContactInformation(student,newCi);
        else mav = deleteContactInformation(newCi);
        return mav;
    }

    private ModelAndView createContactInformation(Student student, ContactInformation newCi){
        ContactInformation ci = studentService.viewContactInfo(student);
        ModelAndView mav = new ModelAndView("contactView");
        if(ci != null){
            mav.addObject("contactInfo", ci);
            return mav;
        }

        studentService.createContactInfo(student,newCi);
        ci = studentService.viewContactInfo(student);

        if(ci == null){
            ci = new ContactInformation();
            ci.setIdStudent(student.getStudentid());
        }
        mav.addObject("contactInfo", ci);
        return mav;
    }

    private ModelAndView updateContactInformation(Student student, ContactInformation newCi){
        ContactInformation ci = studentService.viewContactInfo(student);
        if(ci == null){
            ModelAndView mav = new ModelAndView("contactView");
            mav.addObject("contactInfo", new ContactInformation());
            return mav;
        }
        studentService.updateContactInfo(ci,newCi);
        ci = studentService.viewContactInfo(student);

        ModelAndView mav = new ModelAndView("contactView");
        mav.addObject("contactInfo", ci);
        return mav;
    }

    private ModelAndView deleteContactInformation(ContactInformation ci){
        if(ci != null) studentService.deleteContactInfo(ci);

        ModelAndView mav = new ModelAndView("contactView");
        ci = new ContactInformation();
        ci.setIdStudent(ci.getIdStudent());
        mav.addObject("contactInfo", ci);
        return mav;
    }


    /**process enrollments for student*/
    @RequestMapping(value = "/enrollmentInformation", method = RequestMethod.GET)
    public ModelAndView viewEnrollmentInformation() {
        ModelAndView mav  = new ModelAndView("enrollmentView");
        Student student = new Student(studentId,1);
        List<EnrolledCourse> enrolledCourseList = studentService.findEnrolledCourses(student);
        mav.addObject("enrolledCourseList", enrolledCourseList);

        Course course = new Course();
        mav.addObject("course",course);

        mav.addObject("student",student);
        return mav;
    }

    @RequestMapping(value = "/enrollmentInformation", method = RequestMethod.POST)
    public ModelAndView processEnrollmentOperations(@RequestParam(value = "courseInfo") String courseNameSession,//
    @RequestParam(value = "action")String action,//
    @ModelAttribute(value = "student") Student student,@ModelAttribute(value = "course") Course course){

        ModelAndView mav = new ModelAndView("enrollmentView");
        mav.addObject("student",student);

        if(action.equals("Search")) searchForCourse(mav,courseNameSession);
        else processEnrollment(mav,student,course);

        List<EnrolledCourse> enrolledCourseList = studentService.findEnrolledCourses(student);
        mav.addObject("enrolledCourseList", enrolledCourseList);
        return mav;
    }

    private void searchForCourse(ModelAndView mav,String courseNameSession){
        System.out.println("SEARCH COURSE");

        Course course = studentService.searchForCourse(courseNameSession);
        if(course == null) course = new Course();
        course = course.clone();
        course.setEnrollmentKey("");
        mav.addObject("course",course);
    }

    private void processEnrollment(ModelAndView mav,Student student,Course course){
        System.out.println("ENROLLMENT");

        Course courseDB = studentService.searchForCourse(course.getName()+"-"+course.getSession());
        if(courseDB == null) return;

        studentService.processEnrollment(student,courseDB,course.getEnrollmentKey());
        mav.addObject("course",new Course());
    }

}
