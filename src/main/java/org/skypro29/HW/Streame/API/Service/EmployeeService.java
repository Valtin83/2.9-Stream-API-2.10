package org.skypro29.HW.Streame.API.Service;

import org.apache.coyote.BadRequestException;
import org.skypro29.HW.Streame.API.Employee.Employee;

import java.util.Collection;
import java.util.Map;

public interface EmployeeService {

    Employee remove(String firstName, String lastName, int departmentId, double salary);

    Employee add(String firstName, String lastName, int departmentId, double salary) throws BadRequestException;

    Employee fine(String firstName, String lastName, int departmentId, double salary);

    Map<String, Employee> allEmployee();

    Collection<Employee> findAll();
}