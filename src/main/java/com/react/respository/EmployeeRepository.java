package com.react.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.react.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
