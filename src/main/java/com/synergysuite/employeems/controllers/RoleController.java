package com.synergysuite.employeems.controllers;

import com.synergysuite.employeems.dto.role.command.RoleCommand;
import com.synergysuite.employeems.dto.role.command.RoleUpdateCommand;
import com.synergysuite.employeems.dto.role.query.RoleQuery;
import com.synergysuite.employeems.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleQuery>> getAll() {
        List<RoleQuery> roles = roleService.findAll();

        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<RoleQuery> getById(@PathVariable Integer id) {
        RoleQuery role = roleService.findById(id);

        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody RoleCommand role) {
        roleService.create(role);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody RoleUpdateCommand role) {
        roleService.update(role);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        roleService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
