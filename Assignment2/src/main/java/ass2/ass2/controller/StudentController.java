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

    private Student student;
    private PersonalInformation pi;
    private StudentInformation si;
    private ContactInformation ci;
    private List<EnrolledCourse> enrolledCourseList;
    private Course course;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView viewStudentProfile(@RequestParam(value = "student") Student foundStudent) {
        student = foundStudent;
        ModelAndView mav = new ModelAndView("studentProfile");
        mav.addObject("student", student);
        return mav;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelAndView actionHandler(@RequestParam String action) {
        System.out.println(action);
        return null;
    }

    /**
     * create/view/update student's personal information
     */
    @RequestMapping(value = "/personalInfo", method = RequestMethod.GET)
    public ModelAndView viewPersonalInformation(@RequestParam(value = "student") Student student) {
        pi = studentService.viewPersonalInfo(student);

        ModelAndView mav = new ModelAndView("personalView");
        mav.addObject("personalInfo", pi);
        return mav;
    }

    @RequestMapping(value = "/personalInfo", method = RequestMethod.POST)
    public ModelAndView createPersonalInformation(@RequestParam String action, @ModelAttribute(value = "newPersonalInfo") PersonalInformation newPi) {
        if (action.compareTo("Create") != 0) return new ModelAndView("redirect:/studentProfile/personalInfo");
        studentService.createPersonalInfo(student, newPi);

        pi = studentService.viewPersonalInfo(student);
        ModelAndView mav = new ModelAndView("personalView");
        mav.addObject("personalInfo", pi);
        return mav;
    }

    @RequestMapping(value = "/personalInfo", method = RequestMethod.PUT)
    public ModelAndView updatePersonalInformation(@RequestParam String action, @ModelAttribute(value = "newPersonalInfo") PersonalInformation newPi) {
        if (action.compareTo("Update") != 0) return new ModelAndView("redirect:/studentProfile/personalInfo");
        studentService.updatePersonalInfo(pi, newPi);

        pi = studentService.viewPersonalInfo(student);
        ModelAndView mav = new ModelAndView("personalView");
        mav.addObject("personalInfo", pi);
        return mav;
    }


    /**
     * create/view/update/delete student's student information
     */
    @RequestMapping(value = "/studentInfo", method = RequestMethod.GET)
    public ModelAndView viewStudentInformation() {
        si = studentService.viewStudentInfo(student);

        ModelAndView mav = new ModelAndView("studentView");
        mav.addObject("studentInfo", si);
        return mav;
    }

    @RequestMapping(value = "/studentInfo", method = RequestMethod.POST)
    public ModelAndView createStudentInformation(@RequestParam String action, @ModelAttribute(value = "newStudentInfo") StudentInformation newSi) {
        if (action.compareTo("Create") != 0) return new ModelAndView("redirect:/studentProfile/studentInfo");

        studentService.createStudentInfo(student, newSi);

        si = studentService.viewStudentInfo(student);
        ModelAndView mav = new ModelAndView("studentView");
        mav.addObject("studentInfo", si);
        return mav;
    }

    @RequestMapping(value = "/studentInfo", method = RequestMethod.PUT)
    public ModelAndView updateStudentInformation(@RequestParam String action, @ModelAttribute(value = "newStudentInfo") StudentInformation newSi) {
        if (action.compareTo("Update") != 0) return new ModelAndView("redirect:/studentProfile/studentInfo");

        studentService.updateStudentInfo(si, newSi);

        si = studentService.viewStudentInfo(student);
        ModelAndView mav = new ModelAndView("studentView");
        mav.addObject("studentInfo", si);
        return mav;
    }

    @RequestMapping(value = "/studentInfo", method = RequestMethod.DELETE)
    public ModelAndView deleteStudentInformation(@RequestParam String action) {
        if (action.compareTo("Delete") != 0) return new ModelAndView("redirect:/studentProfile/studentInfo");
        studentService.deleteStudentInfo(si);

        si = studentService.viewStudentInfo(student);
        ModelAndView mav = new ModelAndView("studentView");
        mav.addObject("studentInfo", si);
        return mav;
    }


    /**
     * create/view/update/delete student's contact information
     */
    @RequestMapping(value = "/contactInfo", method = RequestMethod.GET)
    public ModelAndView viewContactInformation() {
        ci = studentService.viewContactInfo(student);

        ModelAndView mav = new ModelAndView("contactView");
        mav.addObject("contactInfo", ci);
        return mav;
    }

    @RequestMapping(value = "/contactInfo", method = RequestMethod.POST)
    public ModelAndView createContactInformation(@RequestParam String action, @ModelAttribute(value = "newContactInfo") ContactInformation newCi) {
        if (action.compareTo("Create") != 0) return new ModelAndView("redirect:/studentProfile/studentInfo");

        studentService.createContactInfo(student, newCi);
        ci = studentService.viewContactInfo(student);

        ModelAndView mav = new ModelAndView("contactView");
        mav.addObject("contactInfo", ci);
        return mav;
    }

    @RequestMapping(value = "/contactInfo", method = RequestMethod.PUT)
    public ModelAndView updateContactInformation(@RequestParam String action, @ModelAttribute(value = "newContactInfo") ContactInformation newCi) {
        if (action.compareTo("Update") != 0) return new ModelAndView("redirect:/studentProfile/studentInfo");

        studentService.updateContactInfo(ci, newCi);
        ci = studentService.viewContactInfo(student);
        ModelAndView mav = new ModelAndView("contactView");
        mav.addObject("contactInfo", ci);
        return mav;
    }

    @RequestMapping(value = "/contactInfo", method = RequestMethod.DELETE)
    public ModelAndView deleteContactInformation(@RequestParam String action) {
        if (action.compareTo("Delete") != 0) return new ModelAndView("redirect:/studentProfile/studentInfo");

        studentService.deleteContactInfo(ci);
        ci = studentService.viewContactInfo(student);
        ModelAndView mav = new ModelAndView("contactView");
        mav.addObject("contactInfo", ci);
        return mav;
    }


    /**
     * process student enrollment - search for course, enroll student for course
     */
    @RequestMapping(value = "/enrollments", method = RequestMethod.GET)
    public ModelAndView viewEnrolledCourses() {
        enrolledCourseList = studentService.findEnrolledCourses(student);

        ModelAndView mav = new ModelAndView("enrollmentView");
        mav.addObject("courseList", enrolledCourseList);
        return mav;
    }

    @RequestMapping(value = "/enrollments", method = RequestMethod.POST)
    public ModelAndView searchForCourse(@RequestParam String action, @RequestParam String courseSession) {
        if (action.compareTo("Search") != 0) return new ModelAndView("redirect:/studentProfile/enrollments");
        course = studentService.searchForCourse(courseSession);
        ModelAndView mav = new ModelAndView("enrollmentView");
        mav.addObject("courseInfo", course);
        return mav;
    }

    @RequestMapping(value = "/enrollments", method = RequestMethod.PUT)
    public ModelAndView processEnrollment(@RequestParam String action, @RequestParam String enrollmentKey) {
        if (action.compareTo("Enroll Me") != 0) return new ModelAndView("redirect:/studentProfile/enrollments");
        studentService.processEnrollment(student, course, enrollmentKey);

        enrolledCourseList = studentService.findEnrolledCourses(student);

        ModelAndView mav = new ModelAndView("enrollmentView");
        mav.addObject("courseList", enrolledCourseList);
        return mav;
    }
}
