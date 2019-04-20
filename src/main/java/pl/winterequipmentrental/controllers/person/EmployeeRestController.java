package pl.winterequipmentrental.controllers.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.winterequipmentrental.exception.person.EmployeeNotFoundException;
import pl.winterequipmentrental.model.person.employee.Employee;
import pl.winterequipmentrental.services.person.employee.EmployeeService;

import java.net.URI;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/employees")
public class EmployeeRestController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping({"/", ""})
    public ResponseEntity createEmployee(@RequestBody Employee employee) {
        Employee result = employeeService.createEmployee(employee);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findEmployeeById(@PathVariable long id) {
        Optional<Employee> employee = employeeService.findEmployeeById(id);
        return ResponseEntity.ok(employee.orElseThrow(EmployeeNotFoundException::new));
    }

    @GetMapping
    public ResponseEntity<Set<Employee>> findAllEmployees() {
        Set<Employee> employees = employeeService.findAllEmployees();
        return ResponseEntity.ok(employees);
    }

}

