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
    public String showPrincipal() {
        return "principal";
    }

    @GetMapping("/faculty")
    public String showFaculty() {return "faculty";}

    @GetMapping("/admin")
    public String showAdmin() {
        return "admission";
    }

    @GetMapping("/infra")
    public String showInfra() {
        return "infrastructures";
    }

    @GetMapping("/affil")
    public String showAffiliation() {
        return "affiliation";
    }

    @GetMapping("/home")
    public String showHome(Model model) {
        model.addAttribute("notices", noticeService.findAll());
        return "index";
    }

    @GetMapping("/houseSystem")
    public String showHouseSystem() {
        return "HouseSystem";
    }

    @GetMapping("/schoolTransport")
    public String showSchoolTransport() {
        return "SchoolTransport";
    }

    @GetMapping("/attendance")
    public String showAttendance() {
        return "Attendance";
    }

    @GetMapping("/sports")
    public String showSports() {
        return "Sports";
    }


    @GetMapping("/project")
    public String showProjectWork() {
        return "ProjectWork";
    }

    @GetMapping("/curriculum")
    public String showCurriculum() {
        return "Curriculum";
    }

    @GetMapping("/studentCouncil")
    public String showStudentCouncil() {
        return "StudentCouncil";
    }

    @GetMapping("/cocurricular")
    public String showCoCurricular() {
        return "cocurricular";
    }

    @GetMapping("/gallery")
    public String showGallery() {
        return "Gallery";
    }


    @GetMapping("/readMore")
    public String showReadMore() {
        return "ReadMore";
    }

    @GetMapping("/fullNotice/{id}")
    public String showFullNotice(@PathVariable("id") long id, Model model) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid notice Id:" + id));
        System.out.println(noticeRepository.findById(id));
        model.addAttribute("notices", notice);
        return "fullNotice";
    }


}
