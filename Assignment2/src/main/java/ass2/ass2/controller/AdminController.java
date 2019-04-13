package ass2.ass2.controller;

import ass2.ass2.business.AdminService;
import ass2.ass2.business.model.StudentProfile;
import ass2.ass2.persistence.entity.Student;
import ass2.ass2.persistence.entity.StudentActivity;
import ass2.ass2.persistence.entity.StudentInformation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/adminProfile")
public class AdminController {

    @Inject
    private AdminService adminService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public void viewAdminProfile(){}

    @RequestMapping(value = "/studentProfiles", method = RequestMethod.GET)
    public ModelAndView viewStudentProfiles(){
        ModelAndView mav = new ModelAndView("studentProfiles");
        List<StudentProfile> studentProfileList = adminService.viewStudents();
        mav.addObject("studentProfileList",studentProfileList);

        StudentProfile studentProfile = new StudentProfile();
        mav.addObject("studentProfile",studentProfile);
        return mav;
    }

    @RequestMapping(value = "/studentActivities", method = RequestMethod.GET)
    public ModelAndView viewStudentActivities(){
        ModelAndView mav = new ModelAndView("studentActivities");
        List<StudentActivity> studentActivityList = adminService.viewActivities(null);
        mav.addObject("studentActivityList",studentActivityList);

        StudentProfile studentProfile = new StudentProfile();
        mav.addObject("studentProfile",studentProfile);
        return mav;
    }

    @RequestMapping(value = "/studentProfiles", method = RequestMethod.POST)
    public ModelAndView processStudentProfiles(@RequestParam(value = "action")String action,//
    @ModelAttribute(value = "studentProfile") StudentProfile studentProfile){
        ModelAndView mav = new ModelAndView("studentProfiles");

        if(action.equals("Create")) createStudentProfile(studentProfile);
        if(action.equals("Update")) updateStudentProfile(studentProfile);
        if(action.equals("Delete")) deleteStudentProfile(studentProfile);

        List<StudentProfile> studentProfileList = adminService.viewStudents();
        mav.addObject("studentProfileList",studentProfileList);
        mav.addObject("studentProfile",new StudentProfile());
        return mav;
    }

    private void createStudentProfile(StudentProfile studentProfile){
        adminService.createStudent(studentProfile);
    }

    private void updateStudentProfile(StudentProfile studentProfile){
        StudentInformation si = new StudentInformation();
        si.setIdStudent(studentProfile.getIdStudent());
        si.setStudGroup(studentProfile.getGroup());
        si.setGradeAvrg(studentProfile.getAverage());
        si.setScholarShipState(studentProfile.getScholarShipState());
        adminService.updateStudent(studentProfile,si);
    }


    private void deleteStudentProfile(StudentProfile studentProfile){
        adminService.deleteStudent(studentProfile);
    }


    @RequestMapping(value = "/studentActivities", method = RequestMethod.POST)
    public ModelAndView processStudentActivities(@RequestParam(value = "action")String action,
                                                 @RequestParam(value = "studentId") String studentId,
                                                 @RequestParam(value = "startDate")LocalDate startDate,
                                                 @RequestParam(value = "endDate") LocalDate endDate,
                                                 @ModelAttribute(value = "studentProfile") StudentProfile studentProfile){
        ModelAndView mav = new ModelAndView("studentActivities");
        if(action.equals("search")) searchForStudent(mav,studentId);
        if(action.equals("filter")) filterStudentActivities(mav,studentProfile,startDate,endDate);
        return mav;
    }

    private void searchForStudent(ModelAndView mav,String studentId){
        StudentProfile studentProfile = adminService.findStudent(studentId);
        mav.addObject("studentProfile",studentProfile);

        List<StudentActivity> studentActivityList = adminService.viewActivities(studentProfile);
        mav.addObject("studentActivityList",studentActivityList);
    }

    private void filterStudentActivities(ModelAndView mav,StudentProfile studentProfile,LocalDate startDate,LocalDate endDate){
        List<StudentActivity> studentActivityList = adminService.filterActivities(studentProfile,startDate,endDate);
        mav.addObject("studentActivityList",studentActivityList);
        mav.addObject("studentProfile",studentProfile);
    }

}
