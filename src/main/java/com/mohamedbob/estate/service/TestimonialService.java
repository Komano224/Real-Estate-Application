package com.mohamedbob.estate.service;

import com.mohamedbob.estate.dto.TestimonialDTO;
import com.mohamedbob.estate.mapper.TestimonialMapper;
import com.mohamedbob.estate.model.Testimonial;
import com.mohamedbob.estate.repository.TestimonialRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TestimonialService {

    private final TestimonialRepository testimonialRepository;

    private final Path uploadDir = Paths.get("uploads/testimonials");

    public TestimonialService(TestimonialRepository testimonialRepository) throws IOException {
        this.testimonialRepository = testimonialRepository;

        if(!Files.exists(uploadDir)) {
            Files.createDirectory(uploadDir);
        }
    }

    @Transactional(readOnly = true)
    public List<TestimonialDTO> getAllTestimonials(){
        List<Testimonial> testimonials = testimonialRepository.findAll();

        return  testimonials
                      .stream()
                      .map(TestimonialMapper::toDTO)
                      .collect(Collectors.toList());
    }
   @Transactional(readOnly = true)
   public TestimonialDTO getTestimonialById(Long id){
        return TestimonialMapper.toDTO(testimonialRepository.findById(id).get());
   }

   @Transactional
    public TestimonialDTO createTestimonial(MultipartFile file, TestimonialDTO testimonialDTO) throws IOException {

        // Upload image
         if(file != null && !file.isEmpty()){
             // Generate a valid name
             String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename().replace(" ", "_");

             // save in uploads/testimonials/
             Path filePath = uploadDir.resolve(fileName);
             Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

             // save in DB
             testimonialDTO.setImageUrl("testimonials/" + fileName);
         }

         Testimonial testimonial = TestimonialMapper.toEntity(testimonialDTO);
         Testimonial saved = testimonialRepository.save(testimonial);

        return TestimonialMapper.toDTO(saved);
    }

    @Transactional
    public TestimonialDTO updateTestimonial(Long id, MultipartFile file, TestimonialDTO testimonialDTO) throws IOException {
        Testimonial testimonial = testimonialRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Testimonial not found"));

        if(file != null && !file.isEmpty()){
            if(testimonial.getImageUrl() != null){
                Files.deleteIfExists(Paths.get("uploads").resolve(testimonial.getImageUrl()));
            }
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Files.copy(file.getInputStream(), uploadDir.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            testimonial.setImageUrl("testimonials/" + fileName);
        }

        testimonial.setFullName(testimonialDTO.getFullName());
        testimonial.setMessage(testimonialDTO.getMessage());
        testimonial.setRating(testimonialDTO.getRating());
        testimonial.setApproved(testimonialDTO.isApproved());

        Testimonial saved = testimonialRepository.save(testimonial);
        return TestimonialMapper.toDTO(saved);
    }

    @Transactional
    public void deleteTestimonial(Long id) throws IOException {
        Testimonial testimonial = testimonialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No testimonial found with id: " + id));

        if (testimonial.getImageUrl() != null) {
            Files.deleteIfExists(Paths.get("uploads").resolve(testimonial.getImageUrl()));
        }

        testimonialRepository.delete(testimonial);
    }

    public TestimonialDTO approveTestimonial(Long id) {
        Testimonial t = testimonialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Testimonial not found"));

        t.setApproved(true);
        Testimonial saved = testimonialRepository.save(t);
        return TestimonialMapper.toDTO(saved);
    }

}
