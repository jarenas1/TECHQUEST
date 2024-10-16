package com.techQuest.TechQuest.entities.dtos.requests;


import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentRequestDto {
    private Long userId;
    private Set<Long> skillIds;
}
