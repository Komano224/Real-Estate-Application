package com.mohamedbob.estate.mapper;

import com.mohamedbob.estate.dto.InquiryDTO;
import com.mohamedbob.estate.dto.ChatMessageDTO;
import com.mohamedbob.estate.model.Inquiry;
import com.mohamedbob.estate.repository.PropertyRepository;
import com.mohamedbob.estate.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class InquiryMapper {

    private final UserRepository userRepository;
    private final PropertyRepository propertyRepository;

    public InquiryMapper(UserRepository userRepository, PropertyRepository propertyRepository) {
        this.userRepository = userRepository;
        this.propertyRepository = propertyRepository;
    }

    // Mapper Inquiry -> InquiryDTO
    public InquiryDTO toDTO(Inquiry inquiry) {
        if (inquiry == null) return null;



        return new InquiryDTO(
                inquiry.getId(),
                inquiry.getUser() != null ? inquiry.getUser().getId() : null,
                inquiry.getUser() != null ? inquiry.getUser().getName() : "",
                inquiry.getUser() != null ? inquiry.getUser().getPhoneNumber(): "",
                inquiry.getProperty() != null ? inquiry.getProperty().getId() : null,
                inquiry.getProperty() != null ? inquiry.getProperty().getTitle() : "",
                inquiry.getMessage() != null ? inquiry.getMessage() : "",
                inquiry.getResponse(),
                inquiry.isAnswered(),
                inquiry.getCreated()
        );
    }

    // Mapper InquiryDTO -> Inquiry
    public Inquiry toEntity(InquiryDTO dto) {
        if (dto == null) return null;

        Inquiry inquiry = new Inquiry();
        inquiry.setId(dto.getId());
        inquiry.setMessage(dto.getMessage());
        inquiry.setResponse(dto.getResponse());

        if (dto.getUserId() != null) {
            userRepository.findById(dto.getUserId()).ifPresent(inquiry::setUser);
        }

        if (dto.getPropertyId() != null) {
            propertyRepository.findById(dto.getPropertyId()).ifPresent(inquiry::setProperty);
        }

        return inquiry;
    }
}
