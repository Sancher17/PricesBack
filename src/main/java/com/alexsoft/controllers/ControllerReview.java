package com.alexsoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alexsoft.db.mongo.ReviewRepository;
import com.alexsoft.entyties.Review;
import com.alexsoft.services.ServiceYouTube;

import java.io.IOException;

@RestController
@RequestMapping("review")
public class ControllerReview {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ServiceYouTube serviceYouTube;

    @GetMapping("/find")
    public Review find(@RequestParam String model, @RequestParam String category) throws IOException {
        Review reviewByModel = serviceYouTube.findReviewByModel(model, category);
        return reviewByModel;
    }

    @GetMapping("/findAndSaveToDb")
    public Review findAndSaveToDb(@RequestParam String model, @RequestParam String category) throws IOException {
        Review reviewByModel = serviceYouTube.findReviewByModel(model, category);
        reviewRepository.save(reviewByModel);
        return reviewByModel;
    }
}
