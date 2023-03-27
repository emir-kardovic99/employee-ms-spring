package com.synergysuite.employeems.repositories;

import com.synergysuite.employeems.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Page<Employee> findByFirstName(Pageable pageable, String firstName);
}
