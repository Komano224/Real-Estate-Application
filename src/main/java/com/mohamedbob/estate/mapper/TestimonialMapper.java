package com.mohamedbob.estate.mapper;

import com.mohamedbob.estate.dto.TestimonialDTO;
import com.mohamedbob.estate.model.Testimonial;

public class TestimonialMapper {

    public static TestimonialDTO toDTO(Testimonial testimonial) {
        if (testimonial == null) {
            return null;
        }
        TestimonialDTO testimonialDTO = new TestimonialDTO();
        testimonialDTO.setId(testimonial.getId());
        testimonialDTO.setFullName(testimonial.getFullName());
        testimonialDTO.setMessage(testimonial.getMessage());
        testimonialDTO.setRating(testimonial.getRating());
        testimonialDTO.setImageUrl(testimonial.getImageUrl());
        testimonialDTO.setApproved(testimonial.isApproved());
        testimonialDTO.setCreatedAt(testimonial.getCreatedAt());
        return testimonialDTO;
    }

    public static Testimonial toEntity(TestimonialDTO testimonialDTO) {
        if (testimonialDTO == null) {
            return null;
        }
        Testimonial testimonial = new Testimonial();
        testimonial.setId(testimonialDTO.getId());
        testimonial.setFullName(testimonialDTO.getFullName());
        testimonial.setMessage(testimonialDTO.getMessage());
        testimonial.setRating(testimonialDTO.getRating());
        testimonial.setImageUrl(testimonialDTO.getImageUrl());
        testimonial.setApproved(testimonialDTO.isApproved());
        testimonial.setCreatedAt(testimonialDTO.getCreatedAt());
        return testimonial;
    }
}
