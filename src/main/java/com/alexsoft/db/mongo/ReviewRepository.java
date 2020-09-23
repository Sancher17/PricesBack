package com.alexsoft.db.mongo;

import com.alexsoft.entyties.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ReviewRepository extends MongoRepository<Review, String> {

    @Query("{ 'modelName' : ?0 }")
    List<Review> findYouTubeByModelName(String modelName);

    List<Review> findAllByModelNameContaining(String modelName);
}
