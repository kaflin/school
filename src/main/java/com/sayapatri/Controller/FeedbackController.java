package com.sayapatri.Controller;

import com.sayapatri.Model.feedback;
import com.sayapatri.Repository.FeedbackRepository;
import com.sayapatri.Service.NoticeService;
import com.sayapatri.Service.feedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@Controller
public class FeedbackController {

    String message;

    @Autowired
    private feedbackService fbs;

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private NoticeService noticeService;


    @GetMapping("/")
    public String showFeedback(Model model) {
        message = null;
        model.addAttribute("notices", noticeService.findAll());
        return "index";
    }

    @PostMapping("/addFeedback")
    public String addFeedBack(@Valid feedback feedback, BindingResult result) {
        if (result.hasErrors()) {
            return "index";
        }
        feedbackRepository.save(feedback);
        return "redirect:/";
    }


    @RolesAllowed("ADMIN")
    @RequestMapping("/feedBackList")
    public String showAdminFeedBackList(Model model) {
        model.addAttribute("feedBack", fbs.findAll());
        return "feedback";
    }
}


