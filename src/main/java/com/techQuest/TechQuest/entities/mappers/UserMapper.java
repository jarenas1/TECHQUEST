package com.techQuest.TechQuest.entities.mappers;

import com.riwi.hero_training.application.dtos.requests.UserRequestDto;
import com.riwi.hero_training.application.dtos.responses.UserResponseDto;
import com.riwi.hero_training.domain.entities.UserEntity;
import com.riwi.hero_training.domain.enums.Roles;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    public UserEntity toEntity(UserRequestDto userRequestDto);
    public UserResponseDto toResponseDto(UserEntity userEntity);
}
