package com.abdulrahman.final_Project.Review;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3/admin")
@RequiredArgsConstructor
public class ReviewController {

    // ServiceInjection
    private final ReviewService reviewService;

    // getter
    @GetMapping("/get_review")
    public List<Review> getReviews(){
        return reviewService.getReviews();
    }
    @PostMapping("/add_review")

    public ResponseEntity addReviews( @RequestBody Review review){
                reviewService.addReview(review);
                return ResponseEntity.status(200).body("Success");

    }
    @PutMapping("/edit_review")
    public ResponseEntity updateReviews(@PathVariable Integer id, @RequestBody Review review){
        reviewService.editReview(id, review);
        return ResponseEntity.status(200).body("Updated");

    }
    @DeleteMapping("/delete_review")
    public ResponseEntity updateReviews(@PathVariable Integer id){
        reviewService.deleteReview(id);
        return ResponseEntity.status(200).body("Updated");

    }

    // Create endpoint that takes a _review name and return all _review information
//    @GetMapping("/get__review_info/{name}")
//    public ResponseEntity updateReviews(@PathVariable String name){
//       Review review = reviewService.returnReviewInfoByName(name);
//        return ResponseEntity.status(200).body(review);
//
//    }
//    // Create endpoint that takes _reviewId and return number of _reviews available
//    @GetMapping("/get__review_count/{id}")
//    public ResponseEntity getReviewsCount(@PathVariable Integer id){
//        Integer _review_count = reviewService.returnCount(id);
//        return ResponseEntity.status(200).body(_review_count);
//
//    }
//    // Create endpoint that return all _reviews under a specific genre
//    @GetMapping("/get__review_by_genre/{genre}")
//    public ResponseEntity getReviewsByGenre(@PathVariable String genre){
//        List<Review> reviews = reviewService.findByGenre(genre);
//        return ResponseEntity.status(200).body(reviews);
//
//    }
}
