package com.techQuest.TechQuest.entities.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AbilityRequestDto {
    @NotBlank(message = "Skill name cannot be blank")
    private String skill;
}
