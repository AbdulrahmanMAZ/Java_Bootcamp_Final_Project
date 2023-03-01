package com.abdulrahman.final_Project.Feedback;

import com.abdulrahman.final_Project.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    final private FeedbackRepo feedbackRepo;

    public List<Feedback> getFeedbacks(){
        return feedbackRepo.findAll();
    }
    public void addFeedback(Feedback feedback){
        feedbackRepo.save(feedback);

    }
    public void editFeedback(Integer id, Feedback feedback){
        Feedback temp = feedbackRepo.findFeedbackById(id);
        if (temp == null) {
            throw new ApiException("Not found");
        }
        temp.setName(feedback.getName());

        feedbackRepo.save(temp);

    }
    public void deleteFeedback(Integer id){
        Feedback temp = feedbackRepo.findFeedbackById(id);
        if (temp == null) {
            throw new ApiException("Not found");
        }
        feedbackRepo.delete(temp);

    }

//    public Integer returnCount(Integer id){
//        Feedback temp = feedbackRepo.findFeedbackById(id);
//        if (temp == null) {
//            throw new ApiException("Not found");
//        }
//        return temp.getFeedbackCount();
//
//    }
//    public Feedback returnFeedbackInfoByName(String name){
//        Feedback temp = feedbackRepo.findFeedbackByName(name);
//        if (temp == null) {
//            throw new ApiException("Not found");
//        }
//        return temp;
//
//    }
//    public List<Feedback> findByGenre(String genre){
//        List<Feedback> feedbacks = feedbackRepo.findAllByGenre(genre);
//        if (feedbacks.isEmpty()) {
//            throw new ApiException("Not found");
//        }
//        return feedbacks;
//
//    }
}
