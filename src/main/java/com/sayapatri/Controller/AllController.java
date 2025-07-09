package com.sayapatri.Controller;

import com.sayapatri.Model.Notice;
import com.sayapatri.Repository.NoticeRepository;
import com.sayapatri.Service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AllController {
    @Autowired
    private NoticeRepository noticeRepository;
    @Autowired
    private NoticeService noticeService;

    @GetMapping("/principalDesk")
    public String showPrincipal(Notice notice) {
        return "principal";
    }

   @GetMapping("/faculty")
    public String showFaculty(Notice notice) {

        return "faculty";
    }

    @GetMapping("/admin")
    public String showAdmin(Notice notice) {
        return "admission";
    }

    @GetMapping("/infra")
    public String showInfra(Notice notice) {
        return "infrastructures";
    }

    @GetMapping("/affil")
    public String showAffiliation(Notice notice) {
        return "affiliation";
    }

    @GetMapping("/home")
    public String showHome(Model model) {
        model.addAttribute("notices",noticeService.findAll());
        return "index";
    }
    @GetMapping("/houseSystem")
    public String showHouseSystem(Notice notice) {
        return "HouseSystem";
    }
    @GetMapping("/schoolTransport")
    public String showSchoolTransport(Notice notice)
    {
        return "SchoolTransport";
    }

    @GetMapping("/attendance")
    public String showAttendance(Notice notice) {
        return "Attendance";
    }

    @GetMapping("/sports")
    public String showSports(Notice notice) {
        return "Sports";
    }


    @GetMapping("/project")
    public String showProjectWork(Notice notice)

    {
        return "ProjectWork";
    }

    @GetMapping("/curriculum")
    public String showCurriculum(Notice notice) {
        return "Curriculum";
    }

    @GetMapping("/studentcouncil")
    public String showStudentCouncil(Notice notice) {
        return "StudentCouncil";
    }

    @GetMapping("/cocurricular")
    public String showCoCurricular(Notice notice) {
        return "cocurricular";
    }

    @GetMapping("/gallery")
    public String showGallery(Notice notice) {
        return "Gallery";
    }


    @GetMapping("/readMore")
    public String showReadMore(Notice notice)

    {
        return "ReadMore";
    }




    @GetMapping("/fullNotice/{id}")
    public String showFullNotice(@PathVariable("id") long id, Model model) {

        Notice notice=noticeRepository.findById(id)
        .orElseThrow(()->new IllegalArgumentException("Invalid notice Id:"+id));
        System.out.println(noticeRepository.findById(id));

        model.addAttribute("notices",notice);
        return "fullNotice";
    }



}
