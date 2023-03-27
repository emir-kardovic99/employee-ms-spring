package com.synergysuite.employeems.controllers;

import com.synergysuite.employeems.dto.employee.command.EmployeeCreateCommand;
import com.synergysuite.employeems.dto.employee.query.EmployeeQuery;
import com.synergysuite.employeems.services.EmployeeService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<Page<EmployeeQuery>> all(Pageable pageable) {
        Page<EmployeeQuery> employees = employeeService.findAll(pageable);

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("search")
    public ResponseEntity<Page<EmployeeQuery>> byName(@PathParam("firstName") String firstName,
                                                      Pageable pageable) {
        Page<EmployeeQuery> employees = employeeService.findByName(pageable, firstName);

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmployeeQuery> create(@RequestBody EmployeeCreateCommand employeeCreateCommand) {
        EmployeeQuery employee = employeeService.create(employeeCreateCommand);

        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<EmployeeQuery> update(@RequestBody EmployeeCreateCommand employeeCreateCommand,
                                                @PathVariable Integer id) {
        EmployeeQuery employee = employeeService.update(employeeCreateCommand, id);

        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        employeeService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
