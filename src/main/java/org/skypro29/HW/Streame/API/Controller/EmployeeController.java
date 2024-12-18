package org.skypro29.HW.Streame.API.Controller;

import org.apache.coyote.BadRequestException;
import org.skypro29.HW.Streame.API.Employee.Employee;
import org.skypro29.HW.Streame.API.Service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping({"/employee"})
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping({"/add"})
    public Employee addEmployee(@RequestParam String firstName,
                                @RequestParam String lastName,
                                @RequestParam int departmentId,
                                @RequestParam double salary) throws BadRequestException {
        return this.service.add(firstName, lastName, departmentId, salary);
    }

    @GetMapping({"/remove"})
    public Employee removeEmployee(@RequestParam String firstName,
                                   @RequestParam String lastName,
                                   @RequestParam int departmentId,
                                   @RequestParam double salary) {
        return this.service.remove(firstName, lastName, departmentId, salary);
    }

    @GetMapping({"/fine"})
    public Employee fineEmployee(@RequestParam String firstName,
                                 @RequestParam String lastName,
                                 @RequestParam int departmentId,
                                 @RequestParam double salary) {
        return this.service.fine(firstName, lastName, departmentId, salary);
    }

    @GetMapping
    public Map<String, Employee> allEmployee() {
        return this.service.allEmployee();
    }
}