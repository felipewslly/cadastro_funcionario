package net.javaguides.ems.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.entity.Employee;
import net.javaguides.ems.exception.ResourceNotFoundException;
import net.javaguides.ems.mapper.EmployeeMapper;
import net.javaguides.ems.repository.EmployeeRepository;
import net.javaguides.ems.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
       Employee employee =  employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                new ResourceNotFoundException("Funcionário não encontrado com este Id: " + employeeId));

        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
       List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto deleteById(Long employeeId) {
        Employee employee =  employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado com este Id:" + employeeId));

        employeeRepository.deleteById(employeeId);

        return EmployeeMapper.mapToEmployeeDto(employee);

    }
    @Override
    public EmployeeDto updateById(Long employeeId, EmployeeDto updateEmployee) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com este Id: " + employeeId));

        employee.setFirstName(updateEmployee.getFirstName());
        employee.setLastName(updateEmployee.getLastName());
        employee.setEmail(updateEmployee.getEmail());

        Employee updateEmployeeObj = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(updateEmployeeObj);


    }

}
