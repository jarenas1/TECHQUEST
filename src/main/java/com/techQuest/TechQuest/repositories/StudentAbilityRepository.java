package com.techQuest.TechQuest.repositories;

import com.techQuest.TechQuest.entities.StudentAbility;
import com.techQuest.TechQuest.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentAbilityRepository extends JpaRepository<StudentAbility, Long> {
    //custom Query
    List<StudentAbility> findByStudent(UserEntity student);
}
