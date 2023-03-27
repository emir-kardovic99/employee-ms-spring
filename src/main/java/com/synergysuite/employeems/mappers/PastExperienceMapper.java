package com.synergysuite.employeems.mappers;

import com.synergysuite.employeems.dto.past_experience.query.PastExperienceQueryDTO;
import com.synergysuite.employeems.entities.PastExperience;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface PastExperienceMapper {

    PastExperience toPastExperience(PastExperienceQueryDTO pastExperienceQueryDTO);
    PastExperienceQueryDTO toPastExperienceQueryDTO(PastExperience pastExperience);

}
