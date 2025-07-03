package com.sayapatri.parasi1.Service;

import com.sayapatri.parasi1.Model.Notice;
import com.sayapatri.parasi1.Repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    public Notice save(Notice notice) {
        noticeRepository.save(notice);
        return notice;
    }

    public List<Notice> findAll() {
        return noticeRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

    }

    public Optional<Notice> findById(Long id) {
        return noticeRepository.findById(id);
    }

}
