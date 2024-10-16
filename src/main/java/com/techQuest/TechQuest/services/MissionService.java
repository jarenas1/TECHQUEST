package com.techQuest.TechQuest.services;

import com.riwi.hero_training.application.dtos.requests.MissionRequestDto;
import com.riwi.hero_training.application.dtos.responses.MissionResponseDto;
import com.riwi.hero_training.application.mappers.MissionMapper;
import com.riwi.hero_training.domain.entities.Mission;
import com.riwi.hero_training.domain.enums.Difficulty;
import com.riwi.hero_training.domain.ports.service.interfaces.IMissionService;
import com.riwi.hero_training.infrastructure.persistence.MissionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MissionService implements IMissionService {
    @Autowired
    private MissionRepository missionRepository;

    @Override
    public Mission create(MissionRequestDto request) {
        Mission mission = MissionMapper.INSTANCE.toEntity(request);
        return missionRepository.save(mission);
    }

    @Override
    public Mission update(MissionRequestDto request, Long id) {
        Optional<Mission> existingMission = missionRepository.findById(id);
        if (existingMission.isPresent()) {
            Mission mission = existingMission.get();
            mission.setName(request.getName());
            mission.setDescription(request.getDescription());
            mission.setDifficulty(Difficulty.valueOf(request.getDifficulty()));
            return missionRepository.save(mission);
        }
        throw new EntityNotFoundException("Mission not found with id: " + id);
    }

    @Override
    public List<MissionResponseDto> readAll() {
        List<Mission> missions = missionRepository.findAll();

        return missions.stream()
                .map(MissionMapper.INSTANCE::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public MissionResponseDto readById(Long id) {
        Mission mission = missionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mission not found with id: " + id));
        return MissionMapper.INSTANCE.toResponseDto(mission);
    }

    @Override
    public void delete(Long id) {
        if (!missionRepository.existsById(id)) {
            throw new EntityNotFoundException("Mission not found with id: " + id);
        }
        missionRepository.deleteById(id);
    }

    @Override
    public Mission evaluateMission(MissionRequestDto request, Long id) {
        Optional<Mission> existingMission = missionRepository.findById(id);

        if (existingMission.isPresent()) {
            Mission mission = existingMission.get();
            mission.setEvaluate(request.getEvaluate());
            return missionRepository.save(mission);
        }
        throw new EntityNotFoundException("Mission not found with id: " + id);
    }
}
