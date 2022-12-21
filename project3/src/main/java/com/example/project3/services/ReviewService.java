package com.example.project3.services;

import com.example.project3.models.CarService;
import com.example.project3.models.Review;
import com.example.project3.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional(readOnly=true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ServiceCarService serviceCarService;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, ServiceCarService serviceCarService) {
        this.reviewRepository = reviewRepository;
        this.serviceCarService = serviceCarService;
    }

    @Transactional
    public void saveReviewForCarService(Integer id, Review review) {

        CarService carServiceForReview = serviceCarService.getServiceById(id).get();
        review.setCarService(carServiceForReview);
        review.setDate(LocalDate.now());

        reviewRepository.save(review);
    }


}
