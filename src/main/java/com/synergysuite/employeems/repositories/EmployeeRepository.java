package com.synergysuite.employeems.repositories;

import com.synergysuite.employeems.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query(value = """
            select employee
            from Employee employee
            where employee.firstName like concat('%', :firstName, '%') 
            """)
    Page<Employee> findByName(Pageable pageable, @Param("firstName") String firstName);

//    @Query(value = "select employee " +
//            "from Employee employee " +
//            "join PastExperience pastexp on employee = pastexp.employee " +
//            "join Holiday holiday on employee = holiday.employee")
//    Page<Employee> findAll1(Pageable pageable);
}
