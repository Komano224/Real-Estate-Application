package com.mohamedbob.estate.controller;

import com.mohamedbob.estate.dto.InquiryDTO;
import com.mohamedbob.estate.dto.InquiryRequest;
import com.mohamedbob.estate.service.InquiryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inquiries")
public class InquiryController {

    private final InquiryService inquiryService;

    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    @GetMapping
    public ResponseEntity<List<InquiryDTO>> getAllInquiries() {
        return ResponseEntity.ok(inquiryService.getAllInquiries());
    }

    @PostMapping("/add")
    public ResponseEntity<InquiryDTO> createInquiry(@RequestBody InquiryRequest request) {
        return ResponseEntity.ok(
                inquiryService.createInquiry(
                        request.getUserId(),
                        request.getPropertyId(),
                        request.getMessage()
                )
        );
    }

    @PostMapping("/{id}/reply")
    public ResponseEntity<InquiryDTO> replyToInquiry(
            @PathVariable Long id,
            @RequestBody String response
    ) {
        return ResponseEntity.ok(inquiryService.replyToInquiry(id, response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInquiry(@PathVariable Long id) {
        inquiryService.deleteInquiry(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<InquiryDTO>> getInquiriesByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(inquiryService.getInquiriesByUser(userId));
    }

}
