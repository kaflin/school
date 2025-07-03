package com.sayapatri.parasi1.Repository;

import com.sayapatri.parasi1.Model.Attachments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttachmentsRepository extends JpaRepository<Attachments, Long> {
    List<Attachments> findByNoticeId(long id);
}
