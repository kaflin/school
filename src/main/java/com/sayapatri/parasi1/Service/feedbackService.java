package com.sayapatri.parasi1.Service;

import com.sayapatri.parasi1.Model.feedback;
import com.sayapatri.parasi1.Repository.FeedbackRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class feedbackService {
    @Autowired
    private FeedbackRepositry feedbackRepositry;


    public List<feedback> findAll() {
         return feedbackRepositry.findAll();

    }
}
