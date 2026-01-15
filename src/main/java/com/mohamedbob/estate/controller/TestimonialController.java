package com.mohamedbob.estate.controller;

import com.mohamedbob.estate.dto.TestimonialDTO;
import com.mohamedbob.estate.service.TestimonialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/testimonials")
public class TestimonialController {

    private final TestimonialService testimonialService;

    public TestimonialController(TestimonialService testimonialService) {
        this.testimonialService = testimonialService;
    }

    @GetMapping
    public ResponseEntity<List<TestimonialDTO>> getAllTestimonials() {
        List<TestimonialDTO> testimonials = testimonialService.getAllTestimonials();
        return ResponseEntity.ok(testimonials);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestimonialDTO> getTestimonialById(@PathVariable Long id) {
        TestimonialDTO testimonial = testimonialService.getTestimonialById(id);
        return ResponseEntity.ok(testimonial);
    }

    @PostMapping
    public ResponseEntity<TestimonialDTO> createTestimonial(
            @RequestParam("fullName") String fullName,
            @RequestParam("message") String message,
            @RequestParam("rating") int rating,
            @RequestParam(value = "approved", required = false, defaultValue = "false") boolean approved,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) throws IOException {
        TestimonialDTO dto = new TestimonialDTO();
        dto.setFullName(fullName);
        dto.setMessage(message);
        dto.setRating(rating);
        dto.setApproved(approved);

        TestimonialDTO created = testimonialService.createTestimonial(file, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TestimonialDTO> updateTestimonial(
            @PathVariable Long id,
            @RequestParam("fullName") String fullName,
            @RequestParam("message") String message,
            @RequestParam("rating") int rating,
            @RequestParam(value = "approved", required = false, defaultValue = "false") boolean approved,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) throws IOException {
        TestimonialDTO dto = new TestimonialDTO();
        dto.setFullName(fullName);
        dto.setMessage(message);
        dto.setRating(rating);
        dto.setApproved(approved);

        TestimonialDTO updated = testimonialService.updateTestimonial(id, file, dto);
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTestimonial(@PathVariable Long id) throws IOException {
        testimonialService.deleteTestimonial(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/approve")
    public ResponseEntity<TestimonialDTO> approveTestimonial(@PathVariable Long id) {
        TestimonialDTO approved = testimonialService.approveTestimonial(id);
        return ResponseEntity.ok(approved);
    }
}
