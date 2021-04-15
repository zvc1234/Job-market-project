package com.proiect.lamunca.controller;

import com.proiect.lamunca.entity.db1.Review;
import com.proiect.lamunca.exception.ApplicationException;
import com.proiect.lamunca.mappers.Mapper;
import com.proiect.lamunca.models.ReviewModel;
import com.proiect.lamunca.service.ReviewService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/Review")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @RequestMapping(value = "/getAllReviews", method = RequestMethod.GET, produces = "application/json")
    public List<Review> getAllReviews(){
        return reviewService.getAll();
    }

    @RequestMapping(value = "/getReviewsByUsernameReceiver", method = RequestMethod.GET, produces = "application/json")
    public List<Review> getReviewsByUsernameReceiver(@RequestParam(value = "usernameReceiver") String usernameReceiver) throws ApplicationException {
        return reviewService.getReviewsByUsernameReceiver(usernameReceiver);

    }
    @RequestMapping(value = "/getReviewsByUsernameWriter", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getReviewsByUsernameWriter(@RequestParam(value = "usernameWriter", required = true) String usernameWriter) throws ApplicationException {
        try {
            if(reviewService.getReviewsByUsernameWriter(usernameWriter)) {
                List<Review> result = new ArrayList<>();
                for (Review review : reviewService.getAll()) {
                    if (review.getWriter_username().equals(usernameWriter)) {
                        result.add(review);
                    }
                }
                //return result;
                return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
            }
            else {
                return new ResponseEntity<>("No reviews from this user",HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            throw new ApplicationException(e.getMessage(), Response.SC_UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/addReview", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> addReview(@RequestBody ReviewModel review) throws ApplicationException {
        reviewService.add(Mapper.mapFromReviewModelToReview(review));
        return new ResponseEntity<>("Successful add review", HttpStatus.OK);
    }

    @RequestMapping(value = "/updateReview", method = RequestMethod.PUT)
    public ResponseEntity<?> updateReview(@RequestBody Review review) throws ApplicationException {
        if(reviewService.getById(review.getId_review()) == false){
            throw new ApplicationException("Review with this idReview doesn't exist", Response.SC_NOT_FOUND);
        }
        else {
            reviewService.update(review);
            return new ResponseEntity<>("Successful update review", HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/deleteReview", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteReview(@RequestParam(value = "idReview", required = true) Integer idReview) throws ApplicationException {
        if(reviewService.getById(idReview) == false){
            throw new ApplicationException("Review with this idReview doesn't exist", Response.SC_NOT_FOUND);
        }
        else {
            reviewService.delete(idReview);
            return new ResponseEntity<>("Successful delete review", HttpStatus.OK);
        }
    }

}
