package com.techQuest.TechQuest.entities.dtos.responses;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentResponseDto {
    private Long id;
    private Long userId;
    private Set<Long> skillIds;
}
