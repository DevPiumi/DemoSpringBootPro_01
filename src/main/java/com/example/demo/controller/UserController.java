package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/users") //create base url
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @PostMapping
//    public UserDTO addUser(@RequestBody UserDTO userDTO) {
//        return userService.createUser(userDTO);
//    }
    //return type : return data (UserDTO) and HTTP status code
    //@RequestBody : Takes JSON data from request body and converts it to UserDTO and Spring automatically converts JSON → Java object.
    //@Valid: Validate the incoming data using annotations in UserDTO
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.createUser(userDTO));
    }

    @PutMapping("/updateuser")
    public UserDTO updateUser(@Valid @RequestBody UserDTO userDTO) {
        return userService.updateUser(userDTO);
    }

    @DeleteMapping("/{id}")
//    public String deleteUser(@PathVariable Integer userId) {
//        return userService.deleteUser(userId);
//    }
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }






}
