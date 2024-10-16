package com.techQuest.TechQuest.entities.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MissionRequestDto {
    @NotBlank(message = "Title cannot be blank")
    private String name;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @NotBlank(message = "Difficulty cannot be blank")
    private String difficulty;

    private Long evaluate;

    private Set<Long> skillIds;
}
