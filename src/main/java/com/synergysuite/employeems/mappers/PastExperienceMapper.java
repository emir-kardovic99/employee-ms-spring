package com.synergysuite.employeems.mappers;

import com.synergysuite.employeems.dto.past_experience.command.PastExperienceCreateCommand;
import com.synergysuite.employeems.dto.past_experience.query.PastExperienceQuery;
import com.synergysuite.employeems.entities.PastExperience;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface PastExperienceMapper {

    PastExperience toPastExperience(PastExperienceCreateCommand pastExperienceCreateCommand);
    PastExperienceQuery toPastExperienceQuery(PastExperience pastExperience);

}
