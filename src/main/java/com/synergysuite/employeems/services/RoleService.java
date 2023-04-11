package com.synergysuite.employeems.services;

import com.synergysuite.employeems.dto.role.command.RoleCommand;
import com.synergysuite.employeems.dto.role.command.RoleUpdateCommand;
import com.synergysuite.employeems.dto.role.query.RoleQuery;
import com.synergysuite.employeems.entities.Role;
import com.synergysuite.employeems.mappers.RoleMapper;
import com.synergysuite.employeems.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public List<RoleQuery> findAll()
    {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(roleMapper::toRoleQuery).toList();
    }

    public RoleQuery findById(Integer id)
    {
        Role role = roleRepository.findById(id).orElse(null);

        return roleMapper.toRoleQuery(role);
    }

    public void create(RoleCommand role) {
        roleRepository.save(roleMapper.toRole(role));
    }

    public void update(RoleUpdateCommand roleCommand) {
        Role role = roleMapper.toRole(roleCommand);
        roleRepository.save(role);
    }

    public void delete(Integer id) {
        roleRepository.deleteById(id);
    }
}
