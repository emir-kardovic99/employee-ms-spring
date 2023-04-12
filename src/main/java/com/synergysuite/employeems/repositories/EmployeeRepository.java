package com.synergysuite.employeems.repositories;

import ch.qos.logback.core.model.conditional.ElseModel;
import com.synergysuite.employeems.dto.employee.command.EmployeeUpdateCommand;
import com.synergysuite.employeems.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query(value = """
            select employee
            from Employee employee
            where employee.firstName like concat('%', :firstName, '%') 
            """)
    Page<Employee> findByName(Pageable pageable, @Param("firstName") String firstName);

    @Query(value = """
            select employee
            from Employee employee
            join fetch employee.roles
            where employee.username = :username
            """)
    Optional<Employee> findByUsernameWithRoles(String username);

    Optional<Employee> findByUsernameAndPassword(String username, String password);

    Boolean existsByUsername(String username);

    @Query(value = """
            select case when count(employee) > 0 then true else false end
            from Employee employee
            where :username in (select emp.username from Employee emp where emp.id != :id)
            """)
    Boolean existsByUsernameAndDifferentId(@Param("username") String username, @Param("id") Integer id);

    Employee findByUsername(String username);

    @Query(value = """
            select employee
            from Employee employee
            join fetch employee.roles
            where employee.id = :id
            """)
    Optional<Employee> findByIdWithRoles(@Param("id") Integer id);
}
