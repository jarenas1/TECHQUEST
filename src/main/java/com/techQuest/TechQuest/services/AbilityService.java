package com.techQuest.TechQuest.services;

import com.riwi.hero_training.application.dtos.requests.SkillRequestDto;
import com.riwi.hero_training.application.dtos.responses.SkillResponseDto;
import com.riwi.hero_training.application.mappers.SkillMapper;
import com.riwi.hero_training.domain.entities.Skill;
import com.riwi.hero_training.domain.ports.service.interfaces.ISkillService;
import com.riwi.hero_training.infrastructure.persistence.SkillRepository;
import com.techQuest.TechQuest.entities.AbilityEntity;
import com.techQuest.TechQuest.entities.dtos.requests.AbilityRequestDto;
import com.techQuest.TechQuest.entities.dtos.responses.AbilityResponseDto;
import com.techQuest.TechQuest.entities.mappers.AbilityMapper;
import com.techQuest.TechQuest.repositories.AbilityRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AbilityService implements ISkillService {
    @Autowired
    private AbilityRepository AbilityRepository;

    public AbilityEntity create(AbilityRequestDto request) {
        if (request.getAbility() == null || request.getAbility().isEmpty()) {
            throw new IllegalArgumentException("La habilidad no puede ser nula");
        }

        return AbilityRepository.save(AbilityMapper.INSTANCE.toEntity(request));
    }

    public AbilityEntity update(AbilityRequestDto request, Long id) {
        if (request.getAbility() == null || request.getAbility().isEmpty()) {
            throw new IllegalArgumentException("La habilidad no puede ser nula");
        }
        Optional<AbilityEntity> excistAbility = AbilityRepository.findById(id);
        if ( excistAbility.isPresent()) {
            AbilityEntity ability =  excistAbility.get();
            ability.setAbilidy(request.getAbility());
            return AbilityRepository.save(ability);
        }
        throw new EntityNotFoundException("la habilidad no se econtro" + id);
    }

    public List<AbilityResponseDto> readAll() {
        List<AbilityEntity> abilities = AbilityRepository.findAll();

        return abilities.stream()
                .map(AbilityMapper.INSTANCE::toResponseDto)
                .collect(Collectors.toList());
    }

    public AbilityResponseDto readById(Long id) {
        AbilityEntity skill = AbilityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("la habilidad no se econtro" + id));
        return AbilityMapper.INSTANCE.toResponseDto(skill);
    }

    public void delete(Long id) {
        if (!AbilityRepository.existsById(id)) {
            throw new EntityNotFoundException("la habilidad no se econtro" + id);
        }
        AbilityRepository.deleteById(id);
    }
}
