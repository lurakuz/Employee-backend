package net.javaguides.springboot.controllers;

import net.javaguides.springboot.models.dto.EmployeeDto;
import net.javaguides.springboot.models.entity.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.services.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeData;

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