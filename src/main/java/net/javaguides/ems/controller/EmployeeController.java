package net.javaguides.ems.controller;

import lombok.AllArgsConstructor;
import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    //Cria uma REST API que adiciona os Employees

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    //Cria uma REST API que busca os dados do Employee
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId){
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employeeDto);
    }

    //Cria uma REST API que busca todos os IDs
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    //Cria uma REST API que atualiza os dados.
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeDto updateEmployee){
        EmployeeDto employeeDto = employeeService.updateById(employeeId, updateEmployee);
        return ResponseEntity.ok(employeeDto);


    }

    //Cria uma REST API que deleta o usuario pelo Id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable ("id") Long employeeId){
        EmployeeDto employeeDto = employeeService.deleteById(employeeId);
        return ResponseEntity.ok("Funcionário deletado com sucesso! " + employeeDto);
    }


}
