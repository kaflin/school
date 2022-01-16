package com.sayapatri.parasi1.Controller;

import com.sayapatri.parasi1.Model.Notice;
import com.sayapatri.parasi1.Repository.NoticeRepository;
import com.sayapatri.parasi1.Service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@Controller
//@RequestMapping("/notice/")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private NoticeRepository noticeRepository;

   @GetMapping("/form")
    public String showNoticeForm(Notice notice)
    {
      return "form";
    }


    @PostMapping("/add")
    public String addNotice(@Valid Notice notice, BindingResult result, Model model)
    {
     if(result.hasErrors())
     {
         return "form";
     }
     noticeService.save(notice);
       return "redirect:/adminlist";
    }

  @RolesAllowed("ADMIN")
    @GetMapping("/adminlist")
    public String showAdminNoticeList(Model model)
    {
        model.addAttribute("notices",noticeService.findAll());
        return "message";
    }



    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model)
    {
        Notice notice=noticeRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Invalid notice Id:"+id));
        model.addAttribute("notice",notice);
        return "update-notice";

    }

    @PostMapping("/update/{id}")
    public String updateNotice(@PathVariable("id") long id, @Valid Notice notice, BindingResult result, Model model)
    {
        if(result.hasErrors()){
            notice.setId(id);
            return "update-notice";
        }
        noticeService.save(notice);
        model.addAttribute("notices",noticeService.findAll());
        return "redirect:/adminlist";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") long id, Model model) {
        Notice notice=noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        noticeRepository.delete(notice);
        model.addAttribute("students", noticeRepository.findAll());
        return "redirect:/adminlist";
    }
}
