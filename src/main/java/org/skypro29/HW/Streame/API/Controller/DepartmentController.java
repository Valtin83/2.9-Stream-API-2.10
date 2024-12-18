package org.skypro29.HW.Streame.API.Controller;

import org.skypro29.HW.Streame.API.Employee.Employee;
import org.skypro29.HW.Streame.API.Service.DepartmentServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentServiceImpl departmentService;

    public DepartmentController(DepartmentServiceImpl departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee maxSalaryDepartment(@PathVariable int departmentId) {
        return departmentService.searchMaxSalaryDepartment(departmentId);
    }

    @GetMapping("/min-salary")
    public Employee minSalaryDepartment(@PathVariable int departmentId) {
        return departmentService.searchMinSalaryDepartment(departmentId);
    }

    @GetMapping(value = "/all", params = "department")
    public Collection<Employee> allDepartment(@PathVariable int departmentId) {
        return departmentService.employeesDepartment(departmentId);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> all() {
        return departmentService.allEmployeesDepartments();
    }
}