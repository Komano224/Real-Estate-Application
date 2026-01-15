package com.mohamedbob.estate.mapper;

import com.mohamedbob.estate.dto.RoleDTO;
import com.mohamedbob.estate.dto.UserResponseDTO;
import com.mohamedbob.estate.dto.UserDTO;
import com.mohamedbob.estate.model.Favorite;
import com.mohamedbob.estate.model.Inquiry;
import com.mohamedbob.estate.model.Property;
import com.mohamedbob.estate.model.User;

import java.util.stream.Collectors;

public class UserMapper {
    // To UserDTO to user for creation and others things
    public static UserDTO toUserDTO(User user) {
        if (user == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setPhoneNumber(user.getPhoneNumber());
       userDTO.setProperties(user.getProperties() != null ? user.getProperties()
                       .stream().map(p -> {
                         Property property = new Property();
                         property.setId(p.getId());
                         return property;
                       }).collect(Collectors.toList()):null
               );

       userDTO.setFavorites(user.getFavorites() != null ? user.getFavorites()
               .stream().map(f -> {
                   Favorite favorite = new Favorite();
                   favorite.setId(f.getId());
                   return favorite;
               }).collect(Collectors.toList()):null);

       userDTO.setInquiries(user.getInquiries() != null ? user.getInquiries()
               .stream().map(in -> {
                   Inquiry inquiry = new Inquiry();
                   inquiry.setId(in.getId());
                   return inquiry;
               }).collect(Collectors.toList()):null);

        if (user.getRoles() != null) {
            userDTO.setRoles(user.getRoles()
                    .stream()
                    .map(role -> role.getName())
                    .collect(Collectors.toSet()));
        }

       return userDTO;
    }

    // This dto is here only to display the essentials data without password
    public static UserResponseDTO toUserResponseDTO(User user) {
        if(user == null) return null;

        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        if (user.getRoles() != null) {
            dto.setRoles(user.getRoles()
                    .stream()
                    .map(r -> new RoleDTO(r.getId(), r.getName()))
                    .collect(Collectors.toSet()));
        }
        return dto;
    }
 // It's end here ain't a normal dto  for me

    public static User toUser(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setPhoneNumber(userDTO.getPhoneNumber());

        return user;

    }
}
