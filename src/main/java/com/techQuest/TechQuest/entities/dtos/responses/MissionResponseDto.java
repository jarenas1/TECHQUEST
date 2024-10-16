package com.techQuest.TechQuest.entities.dtos.responses;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MissionResponseDto {
    private Long id;
    private String title;
    private String description;
    private String difficulty;
    private Long evaluate;
    private Set<Long> requiredSkillIds;
}
