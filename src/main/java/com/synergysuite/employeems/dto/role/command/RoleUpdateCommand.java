package com.synergysuite.employeems.dto.role.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RoleUpdateCommand {

    @NotNull(message = "Id can't be null.")
    private Integer id;
    @NotBlank(message = "Role name can't be null.")
    private String name;

}
