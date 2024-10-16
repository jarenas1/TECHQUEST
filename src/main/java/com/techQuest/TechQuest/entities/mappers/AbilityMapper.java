package com.techQuest.TechQuest.entities.mappers;


import com.techQuest.TechQuest.entities.AbilityEntity;
import com.techQuest.TechQuest.entities.dtos.requests.AbilityRequestDto;
import com.techQuest.TechQuest.entities.dtos.responses.AbilityResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AbilityMapper {
    AbilityMapper INSTANCE = Mappers.getMapper(AbilityMapper.class);

    public AbilityEntity toEntity(AbilityRequestDto abilityRequestDto);
    public AbilityResponseDto toResponseDto(AbilityEntity ability);
}
