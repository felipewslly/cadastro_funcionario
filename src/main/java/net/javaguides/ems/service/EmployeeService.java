package net.javaguides.ems.service;

import net.javaguides.ems.dto.EmployeeDto;
import org.apache.catalina.LifecycleState;

import java.util.List;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long employeeId);

    List<EmployeeDto> getAllEmployees();

    EmployeeDto deleteById(Long employeeId);

    EmployeeDto updateById(Long employeeId, EmployeeDto updateEmployee);
}
