package org.skypro29.HW.Streame.API.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.skypro29.HW.Streame.API.Employee.Employee;
import org.skypro29.HW.Streame.API.Exception.EmployeeNotFoundException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DepartmentServiceImplTest {
    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private Employee employee1;
    private Employee employee2;
    private Employee employee3;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        employee1 = new Employee("Алиса", "Симонова", 1, 70000);
        employee2 = new Employee("Борис", "Иванов", 1, 50000);
        employee3 = new Employee("Дмитрий", "Сергеев", 2, 60000);
    }

    @Test
    void testSearchMinSalaryDepartment() {
        when(employeeService.findAll()).thenReturn(Arrays.asList(employee1, employee2, employee3));

        Employee minSalaryEmployee = departmentService.searchMinSalaryDepartment(1);
        assertEquals(employee2, minSalaryEmployee);
    }

    @Test
    void testSearchMinSalaryDepartmentNotFound() {
        when(employeeService.findAll()).thenReturn(Collections.emptyList());

        assertThrows(EmployeeNotFoundException.class, () -> {
            departmentService.searchMinSalaryDepartment(1);
        });
    }

    @Test
    void testSearchMaxSalaryDepartment() {
        when(employeeService.findAll()).thenReturn(Arrays.asList(employee1, employee2, employee3));

        Employee maxSalaryEmployee = departmentService.searchMaxSalaryDepartment(1);
        assertEquals(employee1, maxSalaryEmployee);
    }

    @Test
    void testSearchMaxSalaryDepartmentNotFound() {
        when(employeeService.findAll()).thenReturn(Collections.emptyList());

        assertThrows(EmployeeNotFoundException.class, () -> {
            departmentService.searchMaxSalaryDepartment(1);
        });
    }

    @Test
    void testEmployeesDepartment() {
        when(employeeService.findAll()).thenReturn(Arrays.asList(employee1, employee2, employee3));

        List<Employee> employees = (List<Employee>) departmentService.employeesDepartment(1);
        assertEquals(2, employees.size());
        assertTrue(employees.contains(employee1));
        assertTrue(employees.contains(employee2));
    }

    @Test
    void testEmployeesDepartmentEmpty() {
        when(employeeService.findAll()).thenReturn(Collections.emptyList());

        List<Employee> employees = (List<Employee>) departmentService.employeesDepartment(1);
        assertTrue(employees.isEmpty());
    }

    @Test
    void testAllEmployeesDepartments() {
        when(employeeService.findAll()).thenReturn(Arrays.asList(employee1, employee2, employee3));

        Map<Integer, List<Employee>> result = departmentService.allEmployeesDepartments();
        assertEquals(2, result.size());
        assertTrue(result.containsKey(1));
        assertTrue(result.containsKey(2));
        assertEquals(2, result.get(1).size());
        assertEquals(1, result.get(2).size());
    }
}