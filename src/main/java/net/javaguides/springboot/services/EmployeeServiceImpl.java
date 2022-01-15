package net.javaguides.springboot.services;

import java.util.List;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.models.entity.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    EmployeeRepository empRep;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository empRep) {
        this.empRep = empRep;
    }

    @Override
    public List<Employee> getAllEmployees(){
        return (List<Employee>) empRep.findAll();
    }

    @Override
    public Employee findEmployeeById(Long id) {
        return empRep.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));
    }

    @Override
    public Employee saveEmployee(Employee employee){
        return empRep.save(employee);
    }

    @Override
    public void deleteEmployeeById(Employee employee){
        empRep.delete(employee);
    }
}
