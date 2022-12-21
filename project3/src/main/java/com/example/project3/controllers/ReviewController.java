package com.example.project3.controllers;

import com.example.project3.models.Review;
import com.example.project3.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    //добавление отзыва
    @PostMapping("/add/{carServiceTypeId}/{carServiceId}")
    public String createReview(@PathVariable("carServiceTypeId") Integer carServiceTypeId,
                               @PathVariable("carServiceId") Integer carServiceId,
                               @ModelAttribute("review") Review review, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "service";
        }
        reviewService.saveReviewForCarService(carServiceId, review);
        return "redirect:/car-service/" + carServiceTypeId + '/' + carServiceId;
    }
}
