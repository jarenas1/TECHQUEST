package com.techQuest.TechQuest.repositories;

import com.techQuest.TechQuest.entities.MissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<MissionEntity, Long> {
}
