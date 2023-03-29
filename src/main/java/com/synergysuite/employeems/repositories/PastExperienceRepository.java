package com.synergysuite.employeems.repositories;

import com.synergysuite.employeems.entities.PastExperience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PastExperienceRepository extends JpaRepository<PastExperience, Integer> {
    List<PastExperience> findByEmployeeId(Integer id);
}
