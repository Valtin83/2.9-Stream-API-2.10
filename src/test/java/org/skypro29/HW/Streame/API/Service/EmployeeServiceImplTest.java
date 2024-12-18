package org.skypro29.HW.Streame.API.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skypro29.HW.Streame.API.Employee.Employee;
import org.skypro29.HW.Streame.API.Exception.EmployeeAlreadyAddedException;
import org.skypro29.HW.Streame.API.Exception.EmployeeNotFoundException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {

    private EmployeeServiceImpl employeeService;
    private Map<String, Employee> employeeMap;

    @BeforeEach
    void setUp() {
        employeeMap = new HashMap<>();
        employeeService = new EmployeeServiceImpl(employeeMap);
    }

    @Test
    void testAddEmployeeSuccessfully() {
        Employee employee = employeeService.add("Иван", "Иванов", 1, 50000);
        assertEquals("Иван Иванов", employee.getFullName());
        assertTrue(employeeMap.containsKey("Иван Иванов"));
        assertEquals(employee, employeeMap.get("Иван Иванов"));
    }

    @Test
    void testAddEmployeeAlreadyExists() {
        employeeService.add("Ян", "Doe", 2, 60000);
        assertThrows(EmployeeAlreadyAddedException.class, () -> {
            employeeService.add("Ян", "Doe", 2, 60000);
        });
    }

    @Test
    void testRemoveEmployeeSuccessfully() {
        employeeService.add("Алиса", "Смирнова", 1, 70000);
        Employee removedEmployee = employeeService.remove("Алиса", "Смирнова", 1, 70000);
        assertEquals("Алиса Смирнова", removedEmployee.getFullName());
        assertFalse(employeeMap.containsKey("Алиса Смирнова"));
    }

    @Test
    void testRemoveNonExistentEmployee() {
        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.remove("Не", "Существующий", 1, 50000);
        });
    }

    @Test
    void testFineEmployeeSuccessfully() {
        employeeService.add("Борис", "Емельянов", 1, 80000);
        Employee foundEmployee = employeeService.fine("Борис", "Емельянов", 1, 80000);
        assertEquals("Борис Емельянов", foundEmployee.getFullName());
    }

    @Test
    void testFineNonExistentEmployee() {
        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.fine("Не", "Существующий", 1, 50000);
        });
    }

    @Test
    void testAllEmployees() {
        employeeService.add("Ева", "Белая", 3, 90000);
        employeeService.add("Адам", "Чёрный", 2, 75000);

        Map<String, Employee> allEmployees = employeeService.allEmployee();
        assertEquals(2, allEmployees.size());
        assertTrue(allEmployees.containsKey("Ева Белая"));
        assertTrue(allEmployees.containsKey("Адам Чёрный"));
    }

    @Test
    void testFindAllEmployees() {
        employeeService.add("Фёдор", "Зелёный", 1, 65000);
        employeeService.add("Дмитрий", "Сергеев", 1, 55000);

        Collection<Employee> allEmployees = employeeService.findAll();
        assertEquals(2, allEmployees.size());
        assertTrue(allEmployees.stream().anyMatch(e -> "Фёдор Зелёный".equals(e.getFullName())));
        assertTrue(allEmployees.stream().anyMatch(e -> "Дмитрий Сергеев".equals(e.getFullName())));
    }
}