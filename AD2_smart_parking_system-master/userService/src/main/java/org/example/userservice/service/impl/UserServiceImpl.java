package org.example.userservice.service.impl;


import org.example.userservice.dto.UserDTO;
import org.example.userservice.entity.User;
import org.example.userservice.repo.UserRepo;
import org.example.userservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    public boolean addUser(UserDTO userDTO) {
        if (userRepo.existsById(userDTO.getU_id())) {
            return false;
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        User user = modelMapper.map(userDTO, User.class);
        userRepo.save(user);
        return true;
    }
    public boolean updatePassword(int u_id, String newPassword) {
        User user = userRepo.findById(u_id).orElse(null);

        if (user != null) {

            user.setPassword(newPassword);
            userRepo.save(user);
            return true;
        }

        return false;
    }


//    public int getNextUserId() {
//        List<Integer> allIds = userRepo.findAllIds();
//
//        if (allIds.isEmpty()) {
//            return 1;
//        }
//        for (int i = 1; i <= allIds.size(); i++) {
//            if (!allIds.contains(i)) {
//                return i;
//            }
//        }
//
//        return allIds.size() + 1;
//    }

    public int getNextUserId() {
        Integer maxId = userRepo.findMaxId();
        return (maxId == null) ? 1 : maxId + 1;
    }

    public List<Integer> getUserIdsByRole() {
        return userRepo.findAllIdsByRole();
    }


    public List<UserDTO> getAllUsers(){
        return modelMapper.map(userRepo.findAll(),new TypeToken<List<UserDTO>>() {}.getType());
    }

    public boolean updateUsers(UserDTO userDTO) {
        if (userDTO.getU_id() == 0) {
            throw new RuntimeException("Invalid User ID: " + userDTO.getU_id());
        }

        Optional<User> existingUser = userRepo.findById(userDTO.getU_id());
        if (!existingUser.isPresent()) {
            throw new RuntimeException("No user found with ID: " + userDTO.getU_id());
        }

        User user = existingUser.get();
        user.setName(userDTO.getName());
        user.setContact(userDTO.getContact());
        user.setAddress(userDTO.getAddress());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());

        // Encrypt password before setting it on the user entity
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }

        userRepo.save(user);
        return true;
    }


    public boolean deleteUser(int u_id) {
        if (userRepo.existsById(u_id)){
            userRepo.deleteById(u_id);
            return true;
        }
        return false;
    }

    //jwt

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user));
    }

    public UserDTO loadUserDetailsByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username);
        return modelMapper.map(user,UserDTO.class);
    }



    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return authorities;
    }

    @Override
    public UserDTO searchUser(String username) {
        if (userRepo.existsByEmail(username)) {
            User user=userRepo.findByEmail(username);
            return modelMapper.map(user,UserDTO.class);
        } else {
            return null;
        }
    }


    public boolean saveUser(UserDTO userDTO) {
        if (userRepo.existsById(userDTO.getU_id())) {
            return false;
        }

        // Create PasswordEncoder manually (Not recommended)
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Encrypt the password before saving it
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        // Map the DTO to the User entity
        User user = modelMapper.map(userDTO, User.class);

        // Save the user
        userRepo.save(user);

        return true;
    }

    @Override
    public UserDTO getUserById(int u_id) {
        Optional<User> userOpt = userRepo.findById(u_id); // Assuming you have userRepository injected
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            UserDTO dto = new UserDTO();
            dto.setU_id(user.getU_id());
            dto.setName(user.getName());
            dto.setEmail(user.getEmail());
            dto.setRole(user.getRole());
            return dto;
        }
        return null;
    }

}
