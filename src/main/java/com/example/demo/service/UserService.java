package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface UserService {

    List<UserDTO> getAllUsers();

    UserDTO getUserById(Integer id);

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO userDTO);

    void deleteUser(Integer id);


}
