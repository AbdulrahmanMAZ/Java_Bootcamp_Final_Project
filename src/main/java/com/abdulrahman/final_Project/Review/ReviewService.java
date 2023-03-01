package com.abdulrahman.final_Project.Review;

import com.abdulrahman.final_Project.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    final private ReviewRepo reviewRepo;

    public List<Review> getReviews(){
        return reviewRepo.findAll();
    }
    public void addReview(Review review){
        reviewRepo.save(review);

    }
    public void editReview(Integer id, Review review){
        Review temp = reviewRepo.findReviewById(id);
        if (temp == null) {
            throw new ApiException("Not found");
        }
        temp.setContent(review.getContent());
        temp.setRating(review.getRating());
        temp.setTitle(review.getTitle());
        reviewRepo.save(temp);

    }
    public void deleteReview(Integer id){
        Review temp = reviewRepo.findReviewById(id);
        if (temp == null) {
            throw new ApiException("Not found");
        }
        reviewRepo.delete(temp);

    }

//    public Integer returnCount(Integer id){
//        Review temp = reviewRepo.findReviewById(id);
//        if (temp == null) {
//            throw new ApiException("Not found");
//        }
//        return temp.getReviewCount();
//
//    }
//    public Review returnReviewInfoByName(String name){
//        Review temp = reviewRepo.findReviewByName(name);
//        if (temp == null) {
//            throw new ApiException("Not found");
//        }
//        return temp;
//
//    }
//    public List<Review> findByGenre(String genre){
//        List<Review> reviews = reviewRepo.findAllByGenre(genre);
//        if (reviews.isEmpty()) {
//            throw new ApiException("Not found");
//        }
//        return reviews;
//
//    }
}
