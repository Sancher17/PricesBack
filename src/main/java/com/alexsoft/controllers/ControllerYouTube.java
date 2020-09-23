package com.alexsoft.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alexsoft.db.BaseEntityRepository;
import com.alexsoft.db.mongo.ReviewRepository;
import com.alexsoft.entyties.Review;
import com.alexsoft.entyties.categories.BaseEntity;
import com.alexsoft.services.ServiceYouTube;

import java.io.IOException;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController()
@RequestMapping("/youTube")
public class ControllerYouTube {

    @Autowired
    private ServiceYouTube serviceYouTube;
    @Autowired
    private BaseEntityRepository baseEntityRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/saveToDb")
    public Review saveToDb(@RequestParam String model, @RequestParam String category) throws IOException {
        Review review = serviceYouTube.findReviewByModel(model, category);
        reviewRepository.save(review);
        log.info("Review saved to DB " + review);
        return review;
    }

    @GetMapping("/getNotebookWithYouTube")
    public List<BaseEntity> getBaseEntityWithYouTube(@RequestParam String model, @RequestParam String category) {
        List<BaseEntity> notebooks = null;
//        List<BaseEntity> notebooks = baseEntityRepository.findByCategoryAndModel(category, model);
//        List<Review> reviews = serviceYouTube.getByModelName(model);
//        for (Notebook notebook : notebooks) {
//            notebook.setReviewList(reviews);
//        }
        return notebooks;
    }

    @GetMapping("/getFromDB")
    public Review getFromDB(@RequestParam String model) throws IOException {
        Review review = serviceYouTube.findByModel(model);
        log.info("Found review into DB " + review);
        return review;
    }


}
