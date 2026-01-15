package com.mohamedbob.estate.service;

import com.mohamedbob.estate.dto.UserResponseDTO;
import com.mohamedbob.estate.dto.UserDTO;
import com.mohamedbob.estate.exception.EmailAlreadyExistException;
import com.mohamedbob.estate.mapper.UserMapper;
import com.mohamedbob.estate.model.Role;
import com.mohamedbob.estate.model.User;
import com.mohamedbob.estate.repository.RoleRepository;
import com.mohamedbob.estate.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional(readOnly = true)
    public List<UserResponseDTO> getAllUser(){
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::toUserResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserDTO getUserById(Long id){
        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found with id: " + id)
        );
        return UserMapper.toUserDTO(user);
    }
    @Transactional
    public UserResponseDTO createUser(UserDTO userDTO){
        if(userRepository.existsByEmail(userDTO.getEmail())){
            throw new EmailAlreadyExistException("User with email: " + userDTO.getEmail() + " already exists");
        }
        User user = UserMapper.toUser(userDTO);
        Role defaultRole  = roleRepository.findByName("ROLE_USER").orElseThrow(
                () -> new RuntimeException("Role not found")
        );
        user.getRoles().add(defaultRole);
        return UserMapper.toUserResponseDTO(userRepository.save(user));
    }

    @Transactional
    public UserResponseDTO updateUser(Long id, UserDTO userDTO){

       User user = userRepository.findById(id).orElseThrow(
               () -> new RuntimeException("User not found with id: " + id)
       );
       // check if email exist or not
        if(!user.getEmail().equals(userDTO.getEmail())){
           if(!userRepository.existsByEmail(userDTO.getEmail())){
               user.setEmail(userDTO.getEmail());
           }
        }

       user.setName(userDTO.getName());

        // Check phone number
       if(!user.getPhoneNumber().equals(userDTO.getPhoneNumber())){
           if(!userRepository.existsByPhoneNumber(userDTO.getPhoneNumber())){
               user.setPhoneNumber(userDTO.getPhoneNumber());
           }
       }

       // Check first  if the password is not and blank
       if(userDTO.getPassword() != null && ! userDTO.getPassword().isBlank()){
           user.setPassword(userDTO.getPassword());
       }

       // Update the role
        if(userDTO.getRoles() != null && ! userDTO.getRoles().isEmpty()){
            Set<Role> roles = userDTO.getRoles().stream()
                    .map(roleName ->
                         roleRepository.findByName(roleName).orElseThrow(() -> new RuntimeException("Role not found"))).collect(Collectors.toSet());
            user.setRoles(roles);
        }

       return UserMapper.toUserResponseDTO(userRepository.save(user));
    }

    // Delete the user by id
    @Transactional
    public void deleteUser(Long id){
        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found with id: " + id)
        );
        user.getFavorites().clear();
        user.getInquiries().clear();
        user.getProperties().clear();
        userRepository.delete(user);
    }
}
