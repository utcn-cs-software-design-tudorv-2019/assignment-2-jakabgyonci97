package ass2.ass2.controller;

import ass2.ass2.business.AdminService;
import ass2.ass2.business.model.StudentProfile;
import ass2.ass2.persistence.entity.StudentActivity;
import ass2.ass2.persistence.entity.StudentInformation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/adminProfile")
public class AdminController {

    @Inject
    private AdminService adminService;

    private List<StudentProfile> studentList;
    private List<StudentActivity> studentActivityList;
    private StudentProfile studentProfile;


    /**
     * create/view/update/delete student profiles
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView viewStudentProfiles() {
        studentList = adminService.viewStudents();
        System.out.println("ADMIN HERE");
        ModelAndView mav = new ModelAndView("adminProfile");
        mav.addObject("studentProfiles", studentList);
        return mav;
    }

    @RequestMapping(value = "/studentProfiles", method = RequestMethod.POST)
    public ModelAndView createStudentProfile(@RequestParam String action, @ModelAttribute(value = "newStudentProfile") StudentProfile newStudent) {
        if (action.compareTo("Create") != 0) return new ModelAndView("redirect:/adminProfile/studentProfile");

        adminService.createStudent(newStudent);
        studentList = adminService.viewStudents();
        ModelAndView mav = new ModelAndView("student_profile_view");
        mav.addObject("studentProfiles", studentList);
        return mav;
    }

    @RequestMapping(value = "/studentProfiles", method = RequestMethod.PUT)
    public ModelAndView updateStudentProfile(@RequestParam String action, @ModelAttribute(value = "oldStudentProfile") StudentProfile oldStudent,//
                                             @ModelAttribute(value = "newStudentProfile") StudentProfile newStudent) {
        if (action.compareTo("Update") != 0) return new ModelAndView("redirect:/adminProfile/studentProfile");
        StudentInformation si = new StudentInformation();
        si.setIdStudent(oldStudent.getIdStudent());
        si.setGroup(newStudent.getGroup());
        si.setGradeAvrg(newStudent.getAverage());
        si.setScholarShipState(newStudent.getScholarShipState());

        adminService.updateStudent(oldStudent, si);

        studentList = adminService.viewStudents();
        ModelAndView mav = new ModelAndView("student_profile_view");
        mav.addObject("studentProfiles", studentList);
        return mav;
    }

    @RequestMapping(value = "/studentProfiles", method = RequestMethod.DELETE)
    public ModelAndView deleteStudentProfile(@RequestParam String action, @ModelAttribute(value = "oldStudentProfile") StudentProfile oldStudent) {
        if (action.compareTo("Delete") != 0) return new ModelAndView("redirect:/adminProfile/studentProfile");
        adminService.deleteStudent(oldStudent);

        studentList = adminService.viewStudents();
        ModelAndView mav = new ModelAndView("student_profile_view");
        mav.addObject("studentProfiles", studentList);
        return mav;
    }

    /**
     * generate reports based on student's activity for a time period
     */
    @RequestMapping(value = "/studentActivity", method = RequestMethod.GET)
    public ModelAndView viewStudentActivities() {
        studentActivityList = adminService.viewActivities(null);

        ModelAndView mav = new ModelAndView("studentActivities");
        mav.addObject("studentActivityList", studentActivityList);
        return mav;
    }

    @RequestMapping(value = "/studentActivity", method = RequestMethod.POST)
    public ModelAndView findStudent(@RequestParam String action, @RequestParam String idStudent) {
        if (action.compareTo("Search") != 0) return new ModelAndView("redirect:/adminProfile/studentActivity");
        studentProfile = adminService.findStudent(idStudent);

        studentActivityList = adminService.viewActivities(studentProfile);
        ModelAndView mav = new ModelAndView("studentActivities");
        mav.addObject("studentActivityList", studentActivityList);
        return mav;
    }

    @RequestMapping(value = "/studentActivity", method = RequestMethod.PUT)
    public ModelAndView filterStudentActivities(@RequestParam String action, @RequestParam String startDate, @RequestParam String endDate) {
        if (action.compareTo("Filter") != 0) return new ModelAndView("redirect:/adminProfile/studentActivity");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);

        studentActivityList = adminService.filterActivities(studentProfile, start, end);
        ModelAndView mav = new ModelAndView("studentActivities");
        mav.addObject("studentActivityList", studentActivityList);
        return mav;
    }
}
