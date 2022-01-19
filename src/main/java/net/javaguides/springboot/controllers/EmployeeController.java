package net.javaguides.springboot.controllers;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import net.javaguides.springboot.mappers.EmployeeMapper;
import net.javaguides.springboot.models.dto.EmployeeDto;
import net.javaguides.springboot.models.entity.Employee;
import net.javaguides.springboot.services.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/employees")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeController {

    final EmployeeMapper employeeMapper;

    final EmployeeServiceImpl employeeServiceImpl;

    @Autowired
    public EmployeeController(EmployeeMapper employeeMapper, EmployeeServiceImpl employeeServiceImpl) {
        this.employeeMapper = employeeMapper;
        this.employeeServiceImpl = employeeServiceImpl;
    }

    @GetMapping //http://localhost:8080/api/v1/employees
    public List<EmployeeDto> getAllEmployees(){
        return employeeMapper.map(employeeServiceImpl.getAllEmployees());
    }

//    @GetMapping("/employees")
//    public ResponseEntity<List<Employee>> getAllEmployees(
//            @RequestParam(defaultValue = "0") Integer pageNo,
//            @RequestParam(defaultValue = "7") Integer pageSize,
//            @RequestParam(defaultValue = "id") String sortBy)
//    {
//        List<Employee> list = employeeServiceImpl.getAllEmployees(pageNo, pageSize, sortBy);
//
//        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
//    }

    @PostMapping("/employees")
    public EmployeeDto createEmployee(@RequestBody Employee employee) {
        employeeServiceImpl.saveEmployee(employee);
        return employeeMapper.map(employee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeServiceImpl.findEmployeeById(id);
        return ResponseEntity.ok(employeeMapper.map(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id,@RequestBody Employee employeeDetails){

        Employee employee = employeeServiceImpl.findEmployeeById(id);

        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmailId(employeeDetails.getEmailId());

        Employee updatedEmployee = employeeServiceImpl.saveEmployee(employee);
        return ResponseEntity.ok(employeeMapper.map(updatedEmployee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
        Employee employee = employeeServiceImpl.findEmployeeById(id);

        employeeServiceImpl.deleteEmployeeById(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);

    }

}