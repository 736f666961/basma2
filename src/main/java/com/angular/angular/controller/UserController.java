package com.angular.angular.controller;


import com.angular.angular.request.UserSignUpRequest;
import com.angular.angular.response.UserResponse;
import com.angular.angular.service.UserService;
import com.angular.angular.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "account")
public class UserController {

    @Autowired
    private UserService userService;

    private ModelMapper modelMapper = new ModelMapper();

    @PostMapping(value = "/register")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserSignUpRequest userSignUpRequest) throws Exception {

        UserDto userDto = modelMapper.map(userSignUpRequest, UserDto.class);

        UserDto userDto1 = userService.createUser(userDto);

        UserResponse userResponse = modelMapper.map(userDto1, UserResponse.class);

        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

//    @GetMapping
//    public String ko() {
//        return "tested";
//    }
}
