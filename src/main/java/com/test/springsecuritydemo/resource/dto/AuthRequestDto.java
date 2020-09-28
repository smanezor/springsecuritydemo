package com.test.springsecuritydemo.resource.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthRequestDto {

    private String email;
    private String password;

}
