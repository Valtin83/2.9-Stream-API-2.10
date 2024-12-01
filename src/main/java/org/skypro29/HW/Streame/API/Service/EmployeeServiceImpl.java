package org.skypro29.HW.Streame.API.Service;

import org.skypro29.HW.Streame.API.Employee.Employee;
import org.skypro29.HW.Streame.API.Exception.EmployeeAlreadyAddedException;
import org.skypro29.HW.Streame.API.Exception.EmployeeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    Map<String, Employee> employeeMap;

    public EmployeeServiceImpl(Map<String, Employee> employeeMap) {
        this.employeeMap = employeeMap;
    }

    @Override
    public Employee add(String firstName, String lastName, int department, double salary) {
        Employee employee = new Employee(firstName, lastName, department, salary);
        if (this.employeeMap.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Работник уже в списке");
        } else {
            this.employeeMap.put(employee.getFullName(), Employee.valueOf(employee));
            return employee;
        }
    }

    @Override
    public Employee remove(String firstName, String lastName, int department, double salary) {
        Employee employee = new Employee(firstName, lastName, department, salary);
        if (this.employeeMap.containsKey(employee.getFullName())) {
            this.employeeMap.remove(employee.getFullName());
            return employee;
        } else {
            throw new EmployeeNotFoundException("Работника нет в списке");
        }
    }

    @Override
    public Employee fine(String firstName, String lastName, int department, double salary) {
        Employee employee = new Employee(firstName, lastName, department, salary);
        if (this.employeeMap.containsKey(employee.getFullName())) {
            return employee;
        } else {
            throw new EmployeeNotFoundException("Работника нет в списке");
        }
    }

    @Override
    public Map<String, Employee> allEmployee() {
        return Collections.unmodifiableMap(this.employeeMap);
    }

    @Override
    public Collection<Employee> findAll() {
        return employeeMap.values();
    }
}