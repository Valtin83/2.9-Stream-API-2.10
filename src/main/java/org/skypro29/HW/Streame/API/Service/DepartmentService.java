package org.skypro29.HW.Streame.API.Service;

import org.skypro29.HW.Streame.API.Employee.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DepartmentService {

    Employee searchMinSalaryDepartment(int department);

    Employee searchMaxSalaryDepartment(int department);

    Collection<Employee> employeesDepartment(int department);

    Map<Integer, List<Employee>> allEmployeesDepartments();

}