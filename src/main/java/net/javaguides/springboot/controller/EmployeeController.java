package net.javaguides.springboot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.javaguides.springboot.model.dto.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.model.entity.Employee;
import net.javaguides.springboot.services.EmployeeServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/")
public class EmployeeController {

    private final EmployeeServiceImpl employeeServiceImpl;

    @Autowired
    public EmployeeController(EmployeeServiceImpl employeeServiceImpl) {
        this.employeeServiceImpl = employeeServiceImpl;
    }

    @GetMapping("/employees") //http://localhost:8080/api/v1/employees
    public List<EmployeeDto> getAllEmployees(){
        List<Employee> employees = employeeServiceImpl.getAllEmployees();
        List<EmployeeDto> employeesDto = new ArrayList<>();
        for (Employee employee : employees){
            EmployeeDto employeeDto = new EmployeeDto();
            employeeDto.setId(employee.getId());
            employeeDto.setEmailId(employee.getEmailId());
            employeeDto.setFirstName(employee.getFirstName());
            employeeDto.setLastName(employee.getLastName());
            employeesDto.add(employeeDto);
        }
        return employeesDto;
    }

    @PostMapping("/employees")
    public EmployeeDto createEmployee(@RequestBody Employee employee) {
        employeeServiceImpl.saveEmployee(employee);
        return employeeServiceImpl.employeeToEmployeeDto(employee);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeServiceImpl.findEmployeeById(id);
        return ResponseEntity.ok(employeeServiceImpl.employeeToEmployeeDto(employee));
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id,@RequestBody Employee employeeDetails){

        Employee employee = employeeServiceImpl.findEmployeeById(id);

        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmailId(employeeDetails.getEmailId());

        Employee updatedEmployee = employeeServiceImpl.saveEmployee(employee);
        return ResponseEntity.ok(employeeServiceImpl.employeeToEmployeeDto(updatedEmployee));
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
        Employee employee = employeeServiceImpl.findEmployeeById(id);

        employeeServiceImpl.deleteEmployeeById(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);

    }

}