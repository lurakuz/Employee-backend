package net.javaguides.springboot.services;

import java.util.List;

import net.javaguides.springboot.models.entity.Employee;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Employee findEmployeeById(Long id);

    Employee saveEmployee(Employee employee);

    void deleteEmployeeById(Employee employee);
}
