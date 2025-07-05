package com.sayapatri.Service;

import com.sayapatri.Model.feedback;
import com.sayapatri.Repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class feedbackService {
    @Autowired
    private FeedbackRepository feedbackRepositry;


    public List<feedback> findAll() {
         return feedbackRepositry.findAll();

    }
}
