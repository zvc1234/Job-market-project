package com.proiect.lamunca.service;


import com.proiect.lamunca.entity.db1.Review;
import com.proiect.lamunca.repository.db1.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService implements IService<Review,Integer> {
    @Autowired
    ReviewRepository reviewRepository;

    @Override
    public boolean add(Review element) {
        try {
            reviewRepository.save(element);
            reviewRepository.updateUserStars(element.getUsername().getId(), element.getUsername().getUsername());
            return true;
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean update(Review element) {
        try {
            reviewRepository.save(element);
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }

    public boolean delete(Integer idReview){
        try{
            reviewRepository.deleteById(idReview);
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }


    @Override
    public List<Review> getAll() {
        try {
            return reviewRepository.findAll();
        }
        catch (Exception ex){
            return null;
        }
    }

    @Override
    public boolean getById(Integer idReview){
        return reviewRepository.existsById(idReview);
    }

    public List<Review> getReviewsByUsernameReceiver(String usernameReceiver){
        return reviewRepository.getReviewsByUsernameReceiver(usernameReceiver);
    }
    public boolean getReviewsByUsernameWriter(String usernameWriter){
        if(reviewRepository.getReviewsByUsernameWriter(usernameWriter) > 0){
            return true;
        }
        return false;
    }

}
