package com.angular.angular.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserSignUpRequest {
    private String fullName;
    private String username;
    private String password;
}
