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
//import org.springframework.web.client.RestTemplate;

//import net.tanesha.recaptcha.ReCaptchaResponse;

@Controller
public class FeedbackController {

    private String message1;

    @Autowired
    private feedbackService fbs;

    @Autowired
    private FeedbackRepository feedbackRepositry;

    @Autowired
    private NoticeService noticeService;

//    @Autowired
//    private RestTemplate restTemplate;


    @GetMapping("/")
    public String showFeedback(Model model)
    {
        message1=null;
        model.addAttribute("notices",noticeService.findAll());
        return "index";
    }

//    @PostMapping("/addFeedback")
//    public String addFeedback(feedback fb,  Model model, @RequestParam(name="g-recaptcha-response") String captchaResponse) {
//
//        model.addAttribute("feedback", fb);
//        String url = "https://www.google.com/recaptcha/api/siteverify";
//        String params = "?secret=6Lc4C64ZAAAAAJTZOPbVbXmyXcs9dpl9tcsxW-wI&response=" + captchaResponse;
//
//        ReCaptchaResponse reCaptchaResponse = restTemplate.exchange(url + params, HttpMethod.POST, null, ReCaptchaResponse.class).getBody();
////        model.addAttribute("feedback", fb);
//
//        if (reCaptchaResponse.isSuccess()) {
//            feedbackRepositry.save(fb);
//            return "redirect:/";
//        } else {
//            message1 = "Please verify captcha";
//            return "redirect:/";
//        }
//    }
@PostMapping("/addFeedback")
public String addFeedBack(@Valid feedback feedback, BindingResult result, Model model)
{
    if(result.hasErrors())
    {
        return "index";
    }
  feedbackRepositry.save(feedback);
    return "redirect:/";
}


    @RolesAllowed("ADMIN")
    @RequestMapping("/feedBackList")
    public String showAdminFeedBackList(Model model)
    {
        model.addAttribute("feedBack",fbs.findAll());
        return "feedback";
//        return "security/login";
    }
}


