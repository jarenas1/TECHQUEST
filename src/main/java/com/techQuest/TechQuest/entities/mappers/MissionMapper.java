package com.techQuest.TechQuest.entities.mappers;

import com.techQuest.TechQuest.entities.MissionEntity;
import com.techQuest.TechQuest.entities.dtos.requests.MissionRequestDto;
import com.techQuest.TechQuest.entities.dtos.responses.MissionResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MissionMapper {
    MissionMapper INSTANCE = Mappers.getMapper(MissionMapper.class);

    MissionEntity toEntity(MissionRequestDto requestDto);

    MissionResponseDto toResponseDto(MissionEntity mission);
}
