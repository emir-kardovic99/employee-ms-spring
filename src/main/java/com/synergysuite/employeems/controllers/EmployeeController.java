package com.synergysuite.employeems.controllers;

import com.synergysuite.employeems.dto.employee.command.EmployeeCreateCommand;
import com.synergysuite.employeems.dto.employee.command.EmployeeUpdateCommand;
import com.synergysuite.employeems.dto.employee.query.EmployeeAllInfoQuery;
import com.synergysuite.employeems.dto.employee.query.EmployeeQuery;
import com.synergysuite.employeems.dto.holiday.command.HolidayCreateCommand;
import com.synergysuite.employeems.dto.holiday.command.HolidayUpdateCommand;
import com.synergysuite.employeems.dto.holiday.query.HolidayExtendQuery;
import com.synergysuite.employeems.dto.holiday.query.HolidayQuery;
import com.synergysuite.employeems.dto.past_experience.command.PastExperienceCreateCommand;
import com.synergysuite.employeems.dto.past_experience.command.PastExperienceUpdateCommand;
import com.synergysuite.employeems.dto.past_experience.query.PastExperienceQuery;
import com.synergysuite.employeems.dto.role.query.RoleQuery;
import com.synergysuite.employeems.error.ValidationException;
import com.synergysuite.employeems.error.validators.EmployeeUpdateValidator;
import com.synergysuite.employeems.error.validators.EmployeeValidator;
import com.synergysuite.employeems.error.validators.HolidayValidator;
import com.synergysuite.employeems.error.validators.PastExperienceValidator;
import com.synergysuite.employeems.services.EmployeeService;
import com.synergysuite.employeems.services.HolidayService;
import com.synergysuite.employeems.services.PastExperienceService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("api/employees")
public class EmployeeController {

    private final HolidayService holidayService;
    private final EmployeeService employeeService;
    private final PastExperienceService pastExperienceService;
    private final HolidayValidator holidayValidator;
    private final PastExperienceValidator pastExperienceValidator;
    private final EmployeeValidator employeeValidator;
    private final EmployeeUpdateValidator employeeUpdateValidator;

    /* EMPLOYEE */
    @GetMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Page<EmployeeQuery>> allEmployees (Pageable pageable)
    {
        Page<EmployeeQuery> employees = employeeService.findAll(pageable);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("search")
    public ResponseEntity<Page<EmployeeQuery>> employeeByName(@PathParam("firstName") String firstName,
                                                      Pageable pageable)
    {
        Page<EmployeeQuery> employees = employeeService.findByName(pageable, firstName);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("{username}")
    public ResponseEntity<EmployeeAllInfoQuery> employeeByUsername(@PathVariable("username") String username)
    {
        EmployeeAllInfoQuery employee = employeeService.findByUsername(username);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("{id}/info")
    public ResponseEntity<EmployeeAllInfoQuery> employeeById(@PathVariable Integer id)
    {
        EmployeeAllInfoQuery employee = employeeService.findById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Integer> createEmployee(@RequestBody @Valid EmployeeCreateCommand employeeCommand)
            throws ValidationException
    {
        Errors errors = new BeanPropertyBindingResult(employeeCommand, "employeeCommand");
        ValidationUtils.invokeValidator(employeeValidator, employeeCommand, errors);
        if (errors.hasErrors()) {
            throw new ValidationException(errors);
        }
        Integer id = employeeService.add(employeeCommand);

        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PutMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> updateEmployee(@RequestBody @Valid EmployeeUpdateCommand employeeCommand)
            throws ValidationException
    {
        Errors errors = new BeanPropertyBindingResult(employeeCommand, "employeeCommand");
        ValidationUtils.invokeValidator(employeeUpdateValidator, employeeCommand, errors);
        if (errors.hasErrors()) {
            throw new ValidationException(errors);
        }

        employeeService.update(employeeCommand);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("{id}/role")
    public ResponseEntity<Void> addRole(@RequestBody RoleQuery role,
                                        @PathVariable Integer id)
    {
        employeeService.addRole(role, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id)
    {
        employeeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /* PAST EXPERIENCE */
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("{employeeId}/experiences")
    public ResponseEntity<List<PastExperienceQuery>> getExperienceByEmployeeId(@PathVariable("employeeId") Integer id)
    {
        List<PastExperienceQuery> pastExperiences = pastExperienceService.find(id);
        return new ResponseEntity<>(pastExperiences, HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("experiences")
    public ResponseEntity<Void> addExperience(@RequestBody @Valid PastExperienceCreateCommand pastExperienceCommand)
            throws ValidationException
    {
        Errors errors = new BeanPropertyBindingResult(pastExperienceCommand, "pastExperienceCommand");
        ValidationUtils.invokeValidator(pastExperienceValidator, pastExperienceCommand, errors);
        if (errors.hasErrors()) {
            throw new ValidationException(errors);
        }

        pastExperienceService.add(pastExperienceCommand);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("experiences")
    public ResponseEntity<Void> updateExperience(@RequestBody PastExperienceUpdateCommand pastExperienceCommand)
    {
        pastExperienceService.update(pastExperienceCommand);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("experiences/{id}")
    public ResponseEntity<Void> deleteExperience(@PathVariable Integer id)
    {
        pastExperienceService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /* HOLIDAY */
    @GetMapping("holidays")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<List<HolidayExtendQuery>> getAllUnapproved()
    {
        List<HolidayExtendQuery> holidays = holidayService.findAllUnapprovedHolidays();
        return new ResponseEntity<>(holidays, HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("{employeeId}/holidays")
    public ResponseEntity<List<HolidayQuery>> getHolidayByEmployeeId(@PathVariable("employeeId") Integer id)
    {
        List<HolidayQuery> holidays = holidayService.find(id);
        return new ResponseEntity<>(holidays, HttpStatus.OK);
    }

    @PostMapping("holidays")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<Void> addHoliday(@RequestBody @Valid HolidayCreateCommand holidayCreateCommand)
            throws ValidationException
    {
        Errors errors = new BeanPropertyBindingResult(holidayCreateCommand, "holidayCreateCommand");
        ValidationUtils.invokeValidator(holidayValidator, holidayCreateCommand, errors);

        if (errors.hasErrors()) {
            throw new ValidationException(errors);
        }

        holidayService.add(holidayCreateCommand);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("holidays")
    public ResponseEntity<Void> updateHoliday(@RequestBody HolidayUpdateCommand holidayCommand)
    {
        holidayService.update(holidayCommand);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("holidays/{id}")
    public ResponseEntity<Void> deleteHoliday(@PathVariable Integer id)
    {
        holidayService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
