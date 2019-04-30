package pl.winterequipmentrental.controllers.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.winterequipmentrental.exception.person.EmployeeNotFoundException;
import pl.winterequipmentrental.model.person.employee.Employee;
import pl.winterequipmentrental.services.person.employee.EmployeeService;

import java.net.URI;
import java.util.*;

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

    @GetMapping(params = "pesel")
    public ResponseEntity<Employee> findEmployeeByPesel(@RequestParam String pesel) {
        Optional<Employee> employee = employeeService.findEmployeeByPesel(pesel);
        return ResponseEntity.ok(employee.orElseThrow(EmployeeNotFoundException::new));
    }

    @GetMapping(params = "person_id_number")
    public ResponseEntity<Employee> findEmployeeByPersonIdNumber(@RequestParam("person_id_number") String personIdNumber) {
        Optional<Employee> employee = employeeService.findEmployeeByPersonIdNumber(personIdNumber);
        return ResponseEntity.ok(employee.orElseThrow(EmployeeNotFoundException::new));
    }

    @GetMapping(params = "contract_number")
    public ResponseEntity<Employee> findEmployeeByConctractNumber(@RequestParam("contract_number") String contractNumber) {
        Optional<Employee> employee = employeeService.findEmployeeByConctractNumber(contractNumber);
        return ResponseEntity.ok(employee.orElseThrow(EmployeeNotFoundException::new));
    }

    @GetMapping(params = "numbers_phone")
    public ResponseEntity<Set<Employee>> findEmployeeByNumbersPhone(@RequestParam("numbers_phone") String[] numbersPhone) {
        Set<Employee> employees = employeeService.findEmployeeByNumbersPhone(numbersPhone);
        return ResponseEntity.ok(employees);
    }

    @GetMapping(params = {"from", "to"})
    public ResponseEntity<Set<Employee>> findEmployeesWhoAreEmploymentBetween(@RequestParam int from,
                                                                              @RequestParam int to) {
        Set<Employee> employees = employeeService.findEmployeesWhoAreEmploymentBetween(from, to);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/positions/{id}")
    public ResponseEntity<Set<Employee>> findEmployeeByPositionId(@PathVariable long id) {
        Set<Employee> employees = employeeService.findEmployeesByPositionId(id);
        return ResponseEntity.ok(employees);
    }

    @GetMapping(value = "/positions", params = "name")
    public ResponseEntity<Set<Employee>> findEmployeeByPositionName(@RequestParam String name) {
        Set<Employee> employees = employeeService.findEmployeesByPositionName(name);
        return ResponseEntity.ok(employees);
    }

    @GetMapping(value = "/address/{id}")
    public ResponseEntity<Set<Employee>> findEmployeeByAddressId(@PathVariable long id) {
        Set<Employee> employees = employeeService.findEmployeesByAddressId(id);
        return ResponseEntity.ok(employees);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEmployeeById(@PathVariable final long id) {
        employeeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(params = "person_id")
    public ResponseEntity<Object> deleteEmployeeById(@RequestParam("person_id") final String personIdNumber) {
        employeeService.deleteByPersonIdNumber(personIdNumber);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/{id}", params = "hired")
    public ResponseEntity<Employee> updateHired(@PathVariable long id,
                                                @RequestParam boolean hired) {
        Optional<Employee> employee = employeeService.updateEmployeeHired(hired, id);
        return ResponseEntity.ok(employee.orElseThrow(EmployeeNotFoundException::new));
    }

    
}

