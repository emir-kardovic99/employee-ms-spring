package com.synergysuite.employeems.controllers;

import com.synergysuite.employeems.dto.employee.command.EmployeeCreateCommand;
import com.synergysuite.employeems.dto.employee.query.EmployeeAllInfoQuery;
import com.synergysuite.employeems.dto.employee.query.EmployeeQuery;
import com.synergysuite.employeems.dto.holiday.command.HolidayCreateCommand;
import com.synergysuite.employeems.dto.holiday.query.HolidayQuery;
import com.synergysuite.employeems.dto.past_experience.command.PastExperienceCreateCommand;
import com.synergysuite.employeems.dto.past_experience.query.PastExperienceQuery;
import com.synergysuite.employeems.services.EmployeeService;
import com.synergysuite.employeems.services.HolidayService;
import com.synergysuite.employeems.services.PastExperienceService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("employee")
public class EmployeeController {

    private final HolidayService holidayService;
    private final EmployeeService employeeService;
    private final PastExperienceService pastExperienceService;

//    EMPLOYEE

    @GetMapping
    public ResponseEntity<Page<EmployeeQuery>> allEmployees (Pageable pageable) {
        Page<EmployeeQuery> employees = employeeService.findAll(pageable);

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("search")
    public ResponseEntity<Page<EmployeeQuery>> employeeByName(@PathParam("firstName") String firstName,
                                                      Pageable pageable) {
        Page<EmployeeQuery> employees = employeeService.findByName(pageable, firstName);

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("info/{id}")
    public ResponseEntity<EmployeeAllInfoQuery> employeeById(@PathVariable Integer id) {
        EmployeeAllInfoQuery employee = employeeService.findById(id);

        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Void> createEmployee(@RequestBody EmployeeCreateCommand employeeCreateCommand) {
        employeeService.add(employeeCreateCommand);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateEmployee(@RequestBody EmployeeCreateCommand employeeCreateCommand,
                                               @PathVariable Integer id) {
        employeeService.update(employeeCreateCommand, id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
        employeeService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    PAST EXPERIENCE
    @GetMapping("experience/{id}")
    public ResponseEntity<List<PastExperienceQuery>> getExperienceByEmployeeId(@PathVariable Integer id) {
        List<PastExperienceQuery> pastExperiences = pastExperienceService.find(id);

        return new ResponseEntity<>(pastExperiences, HttpStatus.OK);
    }

    @PostMapping("experience")
    public ResponseEntity<Void> addExperience(@RequestBody PastExperienceCreateCommand pastExperienceCreateCommand) {
        pastExperienceService.add(pastExperienceCreateCommand);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("experience/{id}")
    public ResponseEntity<Void> updateExperience(@RequestBody PastExperienceCreateCommand pastExperienceCreateCommand,
                                                 @PathVariable Integer id) {
        pastExperienceService.update(pastExperienceCreateCommand, id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("experience/{id}")
    public ResponseEntity<Void> deleteExperience(@PathVariable Integer id) {
        pastExperienceService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    HOLIDAYS
    @GetMapping("holiday/{id}")
    public ResponseEntity<List<HolidayQuery>> getHolidayByEmployeeId(@PathVariable Integer id) {
        List<HolidayQuery> holidays = holidayService.find(id);

        return new ResponseEntity<>(holidays, HttpStatus.OK);
    }

    @PostMapping("holiday")
    public ResponseEntity<Void> addHoliday(@RequestBody HolidayCreateCommand holidayCreateCommand) {
        holidayService.add(holidayCreateCommand);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("holiday/{id}")
    public ResponseEntity<Void> updateHoliday(@RequestBody HolidayCreateCommand holidayCreateCommand,
                                              @PathVariable Integer id) {
        holidayService.update(holidayCreateCommand, id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("holiday/{id}")
    public ResponseEntity<Void> deleteHoliday(@PathVariable Integer id) {
        holidayService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
