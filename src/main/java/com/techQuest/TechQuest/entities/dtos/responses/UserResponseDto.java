package com.techQuest.TechQuest.entities.dtos.responses;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserResponseDto {
    private Long id;
    private String username;
    private String email;
    private String role;
}
