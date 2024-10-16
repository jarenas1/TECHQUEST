package com.techQuest.TechQuest.entities.mappers;


import com.techQuest.TechQuest.entities.UserEntity;
import com.techQuest.TechQuest.entities.dtos.requests.AuthUserRequestDto;
import com.techQuest.TechQuest.entities.dtos.responses.AuthUserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthMapper {
    AuthMapper INSTANCE = Mappers.getMapper(AuthMapper.class);

    UserEntity toEntity(AuthUserRequestDto authUserRequestDto);
    AuthUserResponseDto toResponseDto(UserEntity userEntity, String token);
}
