package com.alexsoft.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexsoft.db.mongo.ReviewRepository;
import com.alexsoft.entyties.Review;
import com.alexsoft.parser.review.YouTubeApi;

import java.io.IOException;
import java.util.List;

@Service
public class ServiceYouTube {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private YouTubeApi youTubeApi;

    public List<Review> getByModelName(String modelName) {
        return reviewRepository.findYouTubeByModelName(modelName);
    }

    public Review findReviewByModel(String model, String category) throws IOException {
        return youTubeApi.getYouTubeData(model, category);
    }

    public Review findByModel(String modelName){
        List<Review> allByModelName = reviewRepository.findAllByModelNameContaining(modelName);
        Review review = new Review();
        review.setReviews(allByModelName);
        return review;
    }
}
