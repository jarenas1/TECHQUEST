package com.techQuest.TechQuest.entities.dtos.responses;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class AuthUserResponseDto extends UserResponseDto {
    private String message;
    private String token;
}
