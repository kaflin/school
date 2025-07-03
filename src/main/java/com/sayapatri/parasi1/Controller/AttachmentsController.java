package com.sayapatri.parasi1.Controller;

import com.sayapatri.parasi1.Model.Attachments;
import com.sayapatri.parasi1.Model.Notice;
import com.sayapatri.parasi1.Repository.AttachmentsRepository;
import com.sayapatri.parasi1.Repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@RestController
public class AttachmentsController {

    @Autowired
    private AttachmentsRepository attachmentRepository;

    @Autowired
    private NoticeRepository noticeRepository;

    @PostMapping("/upload/{noticeId}")
    public ResponseEntity<String> uploadAttachment(
            @PathVariable Long noticeId,
            @RequestParam("file") MultipartFile file) {

        try {
            Notice notice = noticeRepository.findById(noticeId)
                    .orElseThrow(() -> new RuntimeException("Notice not found"));

            Attachments attachment = new Attachments();
            attachment.setFileName(file.getOriginalFilename());
            attachment.setFileType(file.getContentType());
            attachment.setData(file.getBytes());
            attachment.setNotice(notice);

            attachmentRepository.save(attachment);

            return ResponseEntity.ok("File uploaded successfully");

        } catch (IOException e) {
            return ResponseEntity.badRequest().body("File upload failed: " + e.getMessage());
        }
    }
    @GetMapping("/attachments/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {
        Attachments attachment = attachmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found"));

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"" + attachment.getFileName() + "\"")
                .header("Content-Type", attachment.getFileType())
                .body(attachment.getData());
    }
    @GetMapping("/attachments/delete/{id}")
    public ResponseEntity<String> deleteAttachment(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Attachments attachment = attachmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attachment not found"));

        Long noticeId = attachment.getNotice().getId(); // get the notice ID before deleting
        attachmentRepository.deleteById(id);
        return ResponseEntity.ok("Attachment deleted successfully");

    }

}
