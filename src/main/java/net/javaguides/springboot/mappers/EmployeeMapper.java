package net.javaguides.springboot.mappers;

import net.javaguides.springboot.models.dto.EmployeeDto;
import net.javaguides.springboot.models.entity.Employee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeDto map(Employee employee);
    Employee map(EmployeeDto employeeDto);
    List<EmployeeDto> map(List<Employee> employees);
}
