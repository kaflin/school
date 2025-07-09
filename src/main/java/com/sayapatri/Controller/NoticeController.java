package com.sayapatri.Controller;

import com.sayapatri.Model.Attachments;
import com.sayapatri.Model.Notice;
import com.sayapatri.Repository.AttachmentsRepository;
import com.sayapatri.Repository.NoticeRepository;
import com.sayapatri.Service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private AttachmentsRepository attachmentsRepository;

   @GetMapping("/form")
    public String showNoticeForm(Notice notice)
    {
      return "form";
    }


    @PostMapping("/add")
    public String addNotice(@Valid Notice notice,
                            BindingResult result,
                            @RequestParam("files") MultipartFile[] files,
                            Model model) {
        if (result.hasErrors()) {
            return "/form";
        }

        // Save the notice first
        Notice savedNotice = noticeService.save(notice);

        // Save each attachment
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                try {
                    Attachments attachment = new Attachments();
                    attachment.setFileName(file.getOriginalFilename());
                    attachment.setFileType(file.getContentType());
                    attachment.setData(file.getBytes());
                    attachment.setNotice(savedNotice); // set the relation
                    attachmentsRepository.save(attachment); // or use repository directly
                } catch (IOException e) {
                    e.printStackTrace(); // handle or log as needed
                }
            }
        }

        return "redirect:/adminList";
    }


    @RolesAllowed("ADMIN")
    @GetMapping("/adminList")
    public String showAdminNoticeList(Model model) {
        model.addAttribute("notices",noticeService.findAll());
        return "message";
    }



    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Notice notice=noticeRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Invalid notice Id:"+id));
        List<Attachments> attachments = attachmentsRepository.findByNoticeId(id); // You need this method

        model.addAttribute("notice",notice);
        model.addAttribute("attachments", attachments);
        return "update-notice";

    }

    @PostMapping("/update/{id}")
    public String updateNotice(@PathVariable("id") long id,
                               @Valid Notice notice,
                               BindingResult result,
                               @RequestParam("files") MultipartFile[] files,
                               Model model) {
        if (result.hasErrors()) {
            notice.setId(id);
            model.addAttribute("attachments", attachmentsRepository.findByNoticeId(id)); // in case of validation error
            return "update-notice";
        }

        // 1. Fetch existing notice
        Notice existingNotice = noticeService.findById(id).orElseThrow(() -> new RuntimeException("Notice not found"));
        notice.setId(id); // Ensure the ID is retained

        // 2. Remove old attachments
        List<Attachments> oldAttachments = attachmentsRepository.findByNoticeId(id);
        attachmentsRepository.deleteAll(oldAttachments);

        // 3. Save the updated notice
        Notice updatedNotice = noticeService.save(notice);

        // 4. Save new attachments
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                try {
                    Attachments attachment = new Attachments();
                    attachment.setFileName(file.getOriginalFilename());
                    attachment.setFileType(file.getContentType());
                    attachment.setData(file.getBytes());
                    attachment.setNotice(updatedNotice); // associate with updated notice
                    attachmentsRepository.save(attachment);
                } catch (IOException e) {
                    e.printStackTrace(); // or log error
                }
            }
        }

        return "redirect:/adminList";
    }


    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") long id, Model model) {
        Notice notice=noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        noticeRepository.delete(notice);
        model.addAttribute("students", noticeRepository.findAll());
        return "redirect:/adminList";
    }
}
