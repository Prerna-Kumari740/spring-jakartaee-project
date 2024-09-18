package net.empproj.ems.service.impl;

import lombok.AllArgsConstructor;
import net.empproj.ems.dto.EmployeeDto;
import net.empproj.ems.entity.Employee;
import net.empproj.ems.exception.ResourceNotFoundException;
import net.empproj.ems.mapper.EmployeeMapper;
import net.empproj.ems.repository.EmployeeRepository;
import net.empproj.ems.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee =  EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId){
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->
                new ResourceNotFoundException("Employee is not exits with given id"+employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
List<Employee>employees= employeeRepository.findAll ();
    return employees.stream().map(employee-> EmployeeMapper.mapToEmployeeDto(employee))
            .collect(Collectors.toList());
    }

}
