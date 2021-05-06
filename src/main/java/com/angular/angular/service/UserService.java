package com.angular.angular.service;

import com.angular.angular.dto.UserDto;

public interface UserService  {
    /**
     * Create new user
     * @param userDto Object
     * @return userDto
     */
    UserDto createUser(UserDto userDto);
}
