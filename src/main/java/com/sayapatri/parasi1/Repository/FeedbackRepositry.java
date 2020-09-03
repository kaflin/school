package com.sayapatri.parasi1.Repository;

import com.sayapatri.parasi1.Model.feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepositry extends JpaRepository<feedback,Long>
{
}
