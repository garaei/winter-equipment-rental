package pl.winterequipmentrental.services.person.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.winterequipmentrental.model.person.employee.Employee;
import pl.winterequipmentrental.repository.EmployeeRepository;
import pl.winterequipmentrental.services.position.PositionService;

import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PositionService positionService;

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Set<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> findEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Optional<Employee> findEmployeeByPesel(String pesel) {
        return Optional.empty();
    }

    @Override
    public Optional<Employee> findEmployeeByPersonIdNumber(String idNumber) {
        return Optional.empty();
    }

    @Override
    public Optional<Employee> findEmployeeByConctractNumber(String contractNumber) {
        return Optional.empty();
    }

    @Override
    public Optional<Employee> findEmployeeByNumberPhone(String numberPhone) {
        return Optional.empty();
    }

    @Override
    public Set<Employee> findEmployeesByPositionId(long positionId) {
        return null;
    }

    @Override
    public Set<Employee> findEmployeesByPositionName(String positionName) {
        return null;
    }

    @Override
    public Set<Employee> findEmployeesByAddressId(long addressId) {
        return null;
    }

    @Override
    public Optional<Employee> updateEmplyee(Employee employee, long idEmployee) {
        return Optional.empty();
    }

    @Override
    public Optional<Employee> updateEmplyeeHired(boolean hired) {
        return Optional.empty();
    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public void deleteByPersonIdNumber(String idNumber) {

    }
}
