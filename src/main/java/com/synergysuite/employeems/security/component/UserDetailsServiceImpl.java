package com.synergysuite.employeems.security.component;

import com.synergysuite.employeems.entities.Employee;
import com.synergysuite.employeems.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private EmployeeService employeeService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> optionalEmployee = employeeService.findByUsernameWithRoles(username);

        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();

            List<GrantedAuthority> authorities = employee
                    .getRoles()
                    .stream()
                    .map(role -> {
                        return (GrantedAuthority) new SimpleGrantedAuthority(role.getName());
                    })
                    .toList();

            return new User(employee.getUsername(), employee.getPassword(), authorities);
        }
        else {
            throw new UsernameNotFoundException("Employee with username: " + username + " not found!");
        }
    }
}
