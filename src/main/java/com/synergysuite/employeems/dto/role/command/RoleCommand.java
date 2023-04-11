package com.synergysuite.employeems.dto.role.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RoleCommand {

    @NotBlank(message = "Role name can't be null.")
    private String name;

}
