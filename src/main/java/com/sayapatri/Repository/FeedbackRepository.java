package com.sayapatri.Repository;

import com.sayapatri.Model.feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<feedback,Long>
{
}
