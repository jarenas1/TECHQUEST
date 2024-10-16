package com.techQuest.TechQuest.services;

import com.techQuest.TechQuest.entities.MissionEntity;
import com.techQuest.TechQuest.entities.dtos.requests.MissionRequestDto;
import com.techQuest.TechQuest.entities.dtos.responses.MissionResponseDto;
import com.techQuest.TechQuest.entities.mappers.MissionMapper;
import com.techQuest.TechQuest.repositories.MissionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MissionService {
    @Autowired
    private MissionRepository missionRepository;

    public MissionEntity create(MissionRequestDto request) {
        MissionEntity mission = MissionnMapper.INSTANCE.toEntity(request);
        return missionRepository.save(mission);
    }

    public MissionEntity update(MissionRequestDto request, Long id) {
        Optional<MissionEntity> missionOptional = missionRepository.findById(id);
        if (missionOptional.isPresent()) {
            MissionEntity missionEntity = missionOptional.get();
            missionEntity.setName(request.getName());
            missionEntity.setDescription(request.getDescription());
            missionEntity.setDifficulty(Difficulty.valueOf(request.getDifficulty()));
            return missionRepository.save(missionEntity);
        }
        throw new EntityNotFoundException("Mision " + id+" no encontrada");
    }

    public List<MissionResponseDto> readAll() {
        List<MissionEntity> missions = missionRepository.findAll();

        return missions.stream()
                .map(MissionMapper.INSTANCE::toResponseDto)
                .collect(Collectors.toList());
    }

    public MissionResponseDto readById(Long id) {
        MissionEntity missionEntity = missionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mision " + id+" no encontrada"));
        return MissionMapper.INSTANCE.toResponseDto(missionEntity);
    }

    public void delete(Long id) {
        if (!missionRepository.existsById(id)) {
            throw new EntityNotFoundException("Mision " + id + " no encontrada");
        }
        missionRepository.deleteById(id);
    }

    public MissionEntity Mission(MissionRequestDto request, Long id) {
        Optional<MissionEntity> optionalMission = missionRepository.findById(id);

        if (optionalMission.isPresent()) {
            MissionEntity missionEntity = optionalMission.get();
            missionEntity.setEvaluate(request.getEvaluate());
            return missionRepository.save(missionEntity);

        throw new EntityNotFoundException("Mission not found with id: " + id);
    }
}
