package com.abdulrahman.final_Project.Feedback;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepo extends JpaRepository<Feedback,Integer> {
    Feedback findFeedbackById(Integer id);
    Feedback findFeedbackByName(String name);



}
