package com.techQuest.TechQuest.services;

import com.riwi.hero_training.application.dtos.requests.SkillRequestDto;
import com.riwi.hero_training.application.dtos.responses.SkillResponseDto;
import com.riwi.hero_training.application.mappers.SkillMapper;
import com.riwi.hero_training.domain.entities.Skill;
import com.riwi.hero_training.domain.ports.service.interfaces.ISkillService;
import com.riwi.hero_training.infrastructure.persistence.SkillRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SkillService implements ISkillService {
    @Autowired
    private final SkillRepository skillRepository;

    @Override
    public Skill create(SkillRequestDto request) {
        if (request.getSkill() == null || request.getSkill().isEmpty()) {
            throw new IllegalArgumentException("Skill name cannot be null or blank");
        }

        return skillRepository.save(SkillMapper.INSTANCE.toEntity(request));
    }

    @Override
    public Skill update(SkillRequestDto request, Long id) {
        if (request.getSkill() == null || request.getSkill().isEmpty()) {
            throw new IllegalArgumentException("Skill name cannot be null or blank");
        }

        Optional<Skill> existingSkill = skillRepository.findById(id);
        if (existingSkill.isPresent()) {
            Skill skill = existingSkill.get();
            skill.setSkill(request.getSkill());
            return skillRepository.save(skill);
        }
        throw new EntityNotFoundException("Skill not found with id: " + id);
    }

    @Override
    public List<SkillResponseDto> readAll() {
        List<Skill> skills = skillRepository.findAll();

        return skills.stream()
                .map(SkillMapper.INSTANCE::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public SkillResponseDto readById(Long id) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Skill not found with id: " + id));
        return SkillMapper.INSTANCE.toResponseDto(skill);
    }

    @Override
    public void delete(Long id) {
        if (!skillRepository.existsById(id)) {
            throw new EntityNotFoundException("Skill not found with id: " + id);
        }
        skillRepository.deleteById(id);
    }
}
