package com.techQuest.TechQuest.repositories;

import com.techQuest.TechQuest.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    
}
