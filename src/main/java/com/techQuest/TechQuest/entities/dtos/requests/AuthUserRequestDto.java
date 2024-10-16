package com.techQuest.TechQuest.entities.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthUserRequestDto {
    @NotBlank(message = "Identifier required")
    @Email(message = "Email should be valid")
    private String identifier;

    @NotBlank(message = "Password cannot be blank")
    private String password;
}
