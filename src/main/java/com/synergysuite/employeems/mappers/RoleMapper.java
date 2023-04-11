package com.synergysuite.employeems.mappers;

import com.synergysuite.employeems.dto.role.command.RoleCommand;
import com.synergysuite.employeems.dto.role.command.RoleUpdateCommand;
import com.synergysuite.employeems.dto.role.query.RoleQuery;
import com.synergysuite.employeems.entities.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface RoleMapper {

    Role toRole(RoleCommand roleCommand);
    Role toRole(RoleUpdateCommand roleUpdateCommand);
    RoleQuery toRoleQuery(Role role);

}
