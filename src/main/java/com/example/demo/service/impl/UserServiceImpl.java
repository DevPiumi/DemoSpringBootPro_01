package com.example.demo.service.impl;

import com.example.demo.dto.UserDTO;
import com.example.demo.config.ModelMapperConfig;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private  final UserRepo userRepo;
    private final ModelMapper modelMapper;



    //Get all users from the database, convert each User entity into a UserDTO, and return them as a list.
    //Returns a list of UserDTO Not returning User entity directly (GOOD practice)
    //Calls JPA repository and fetches List<User>: This is entity data from DB
    //Convert list to stream : Think of a stream like a pipeline, It lets you process items one by one.
    //Map Entity → DTO : For each user, take User -> Convert it to UserDTO -> Using Model mapper
    //Collect result into List : Converts the stream back into a List<UserDTO>  return type matches method signature
    @Override
    public List<UserDTO> getAllUsers() {
        //List<User>userList = userRepo.findAll();
        //return modelMapper.map(userList, new TypeToken<List<UserDTO>>(){}.getType());
        return userRepo.findAll()
                .stream()
                .map(user -> modelMapper.map(user,UserDTO.class))
                .toList();
    }

    //Get a user by ID from the database. If the user does not exist, throw an error. If it exists, convert it to UserDTO and return it.
    @Override
    public UserDTO getUserById(Integer id) {
        //User user = userRepo.getUserById(userId);
        //return modelMapper.map(user, UserDTO.class);
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return modelMapper.map(user, UserDTO.class);

    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        //userRepo.save(modelMapper.map(userDTO, User.class)); //User dto eken ena eka user class ekata map krganna
        //return userDTO;
        User user = modelMapper.map(userDTO, User.class);
        return modelMapper.map(userRepo.save(user), UserDTO.class);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        //userRepo.save(modelMapper.map(userDTO, User.class));
        //return userDTO;
        userRepo.findById(userDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        User user = modelMapper.map(userDTO, User.class);
        return modelMapper.map(userRepo.save(user), UserDTO.class);

    }

    @Override
    public void deleteUser(Integer id) {
        //userRepo.delete(modelMapper.map(userDTO, User.class));
        if(!userRepo.existsById(id)){
            throw new RuntimeException("User not found");
        }
        userRepo.deleteById(id);
    }


}
