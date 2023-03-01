package com.abdulrahman.final_Project.Review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepo extends JpaRepository<Review,Integer> {
    Review findReviewById(Integer id);
//    Review findReviewByName(String name);
//    List<Review> findAllByGenre(String genre);
    List<Review> findAllByAdvisor_Id(Integer advisor_id);


}
