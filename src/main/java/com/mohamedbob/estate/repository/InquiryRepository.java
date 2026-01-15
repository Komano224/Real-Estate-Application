package com.mohamedbob.estate.repository;

import com.mohamedbob.estate.model.Inquiry;
import com.mohamedbob.estate.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
    List<Inquiry> findByUserId(Long userId);

    List<Inquiry> findByPropertyId(Long propertyId);

    List<Inquiry> findByUser(User user);
}
