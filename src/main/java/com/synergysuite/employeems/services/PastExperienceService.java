package com.synergysuite.employeems.services;

import com.synergysuite.employeems.dto.past_experience.command.PastExperienceCreateCommand;
import com.synergysuite.employeems.dto.past_experience.query.PastExperienceQuery;
import com.synergysuite.employeems.entities.PastExperience;
import com.synergysuite.employeems.mappers.PastExperienceMapper;
import com.synergysuite.employeems.repositories.PastExperienceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PastExperienceService {

    private final PastExperienceRepository pastExperienceRepository;
    private final PastExperienceMapper pastExperienceMapper;

    public List<PastExperienceQuery> find(Integer id) {
        List<PastExperience> pastExperiences = pastExperienceRepository.findByEmployeeId(id);

        return pastExperiences.stream().
                map(pastExperienceMapper::toPastExperienceQuery).toList();
    }

    public void add(PastExperienceCreateCommand pastExperiencesCommand) {
        PastExperience pastExperience = pastExperienceMapper.toPastExperience(pastExperiencesCommand);

        pastExperienceRepository.save(pastExperience);
    }

    public void update(PastExperienceCreateCommand pastExperienceCreateCommand, Integer id) {
        PastExperience pastExperience = pastExperienceMapper.toPastExperience(pastExperienceCreateCommand);
        pastExperience.setId(id);

        pastExperienceRepository.save(pastExperience);
    }

    public void delete(Integer id) {
        pastExperienceRepository.deleteById(id);
    }
}
