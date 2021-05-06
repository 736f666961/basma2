package com.angular.angular.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserDto {
    private String uuid;
    private String fullName;
    private String username;
    private String password;
}
