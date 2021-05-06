package com.angular.angular.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserResponse {
    private String uuid;
    private String fullName;
    private String username;
}
