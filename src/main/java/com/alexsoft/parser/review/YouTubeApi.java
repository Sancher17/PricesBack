package com.alexsoft.parser.review;

import org.springframework.stereotype.Component;
import com.alexsoft.entyties.Review;
import com.alexsoft.services.AbstrWebClient;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.jayway.jsonpath.JsonPath;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class YouTubeApi extends AbstrWebClient {

    public static String YOUTUBE = "YouTube";

    public Review getYouTubeData(String model, String category) throws IOException {
        WebClient webClient = getWebClient();
        String baseUrl = "https://www.googleapis.com/youtube/v3/search?";
        String part = "part=snippet&";
        String search = "q=";
        String type = "&type=video";
        String key = "&key=AIzaSyCJ_LGAZkgUKhFysDhputm-GacxtA--7xk";
        String maxResult = "&maxResults=10";

        String complexUrl = baseUrl+part+search+model+type+key+maxResult;
        Page page = webClient.getPage(complexUrl);
        String content = page.getWebResponse().getContentAsString();

        List<String> titles = JsonPath.read(content, "$.items[*].snippet.title");
        List<String> dates = JsonPath.read(content, "$.items[*].snippet.publishedAt");
        List<String> links = JsonPath.read(content, "$.items[*].id.videoId");
        List<Review> reviews = new ArrayList<>();

        for (int i = 0; i < titles.size(); i++) {
            Review review = new Review();
            review.setTitle(titles.get(i));
            review.setSource(YOUTUBE);
            review.setDate(dates.get(i));
            String videoLinkBase = "https://www.youtube.com/watch?v=";
            String sourceVideo = links.get(i);
            review.setLink(videoLinkBase+sourceVideo);
            review.setModelName(model);
            reviews.add(review);
        }

        Review review = new Review();
        review.setModelName(model);
        review.setReviews(reviews);
        review.setCategory(category);
        return review;
    }

}
