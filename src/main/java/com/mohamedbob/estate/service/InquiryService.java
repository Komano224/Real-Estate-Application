package com.mohamedbob.estate.service;

import com.mohamedbob.estate.dto.ChatMessageDTO;
import com.mohamedbob.estate.dto.InquiryDTO;
import com.mohamedbob.estate.mapper.InquiryMapper;
import com.mohamedbob.estate.model.Inquiry;
import com.mohamedbob.estate.model.Property;
import com.mohamedbob.estate.model.User;
import com.mohamedbob.estate.repository.InquiryRepository;
import com.mohamedbob.estate.repository.PropertyRepository;
import com.mohamedbob.estate.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class InquiryService {

    private final InquiryRepository inquiryRepository;
    private final UserRepository userRepository;
    private final PropertyRepository propertyRepository;
    private final InquiryMapper inquiryMapper;
    private final EmailService emailService;

    public InquiryService(InquiryRepository inquiryRepository,
                          UserRepository userRepository,
                          PropertyRepository propertyRepository,
                          InquiryMapper inquiryMapper,
                          EmailService emailService) {
        this.inquiryRepository = inquiryRepository;
        this.userRepository = userRepository;
        this.propertyRepository = propertyRepository;
        this.inquiryMapper = inquiryMapper;
        this.emailService = emailService;
    }

    @Transactional(readOnly = true)
    public List<InquiryDTO> getAllInquiries() {
        return inquiryRepository.findAll()
                .stream()
                .map(inquiryMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Create an inquiry
    public InquiryDTO createInquiry(Long userId, Long propertyId, String message) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));

        Inquiry inquiry = new Inquiry(user, property, message);
        Inquiry saved = inquiryRepository.save(inquiry);

        // Send email to admin
        try {
            emailService.sendEmail(
                    " mohamedbobkomano@gmail.com",
                    user.getEmail(),
                    user.getName(),
                    property.getTitle(),
                    message
            );
        } catch (Exception e) {
            System.out.println("Error sending email: " + e.getMessage());
        }

        return inquiryMapper.toDTO(saved);
    }

    // Reply to an inquiry
    public InquiryDTO replyToInquiry(Long inquiryId, String response) {
        Inquiry inquiry = inquiryRepository.findById(inquiryId)
                .orElseThrow(() -> new RuntimeException("Inquiry not found"));

        inquiry.setResponse(response);
        inquiry.setAnswered(true);
        inquiryRepository.save(inquiry);

        try {
            emailService.sendEmail(
                    inquiry.getUser().getEmail(),
                    "mohamedbobkomano@gmail.com",
                    "Admin",
                    "Answer to your message on: " + inquiry.getProperty().getTitle(),
                    response
            );
        }catch (Exception e){
            System.out.println("Error sending email: " + e.getMessage());
        }
        return inquiryMapper.toDTO(inquiry);
    }

    // Delete inquiry
    public void deleteInquiry(Long id) {
        Inquiry inquiry = inquiryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inquiry not found"));
        inquiryRepository.delete(inquiry);
    }

    // Get inquiries by user
    public List<InquiryDTO> getInquiriesByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<Inquiry> inquiries = inquiryRepository.findByUser(user);

        return inquiries.stream()
                .map(inquiry -> {
                    InquiryDTO dto = inquiryMapper.toDTO(inquiry);
                    return dto;
                }).collect(Collectors.toList());
    }
}
