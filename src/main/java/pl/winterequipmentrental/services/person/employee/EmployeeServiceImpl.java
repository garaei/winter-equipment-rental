package pl.winterequipmentrental.services.person.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.winterequipmentrental.model.person.employee.Employee;
import pl.winterequipmentrental.repository.EmployeeRepository;
import pl.winterequipmentrental.services.position.PositionService;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
        return employeeRepository.findByPesel(pesel);
    }

    @Override
    public Optional<Employee> findEmployeeByPersonIdNumber(String idNumber) {
        return employeeRepository.findByPersonIDNumber(idNumber);
    }

    @Override
    public Optional<Employee> findEmployeeByConctractNumber(String contractNumber) {
        return employeeRepository.findByContractNumber(contractNumber);
    }

    @Override
    public Optional<Employee> findEmployeeByNumberPhone(String numberPhone) {
        return Optional.empty();
    }

    @Override
    public Set<Employee> findEmployeesWhoAreEmploymentBetween(int from, int to) {
        LocalDate today = LocalDate.now();

        return employeeRepository.findAll()
                .stream()
                .filter(Employee::isHired)
                .filter(employee ->
                        {
                            LocalDate dateEmployment = employee
                                    .getRecentlyDateEmployment()
                                    .toInstant()
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDate();
                            Period period = Period.between(today, dateEmployment);
                            int diff = period.getDays();
                            if (diff >= from && diff <= to) return true;
                            return false;
                        }
                )
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Employee> findEmployeesByPositionId(long positionId) {
        return employeeRepository.findByPositionId(positionId);
    }

    @Override
    public Set<Employee> findEmployeesByPositionName(String positionName) {
        return employeeRepository.findEmployeesByPositionName(positionName);
    }

    @Override
    public Set<Employee> findEmployeesByAddressId(long addressId) {
        return employeeRepository.findEmployeesByAddressId(addressId);
    }

    @Override
    public Optional<Employee> updateEmplyee(Employee employee, long idEmployee) {
        return employeeRepository.findById(idEmployee)
                .map(e -> {
                    e.setPosition(employee.getPosition());
                    e.setAddress(employee.getAddress());
                    e.setHired(employee.isHired());
                    e.setEmail(employee.getEmail());
                    e.setPesel(employee.getPesel());
                    // TODO dokończyć
                    return employeeRepository.save(e);
                });
    }

    @Override
    public Optional<Employee> updateEmplyeeHired(boolean hired, long idEmployee) {
        if (resourceFound(idEmployee)) {
            employeeRepository.updateHired(idEmployee, hired);
            return employeeRepository.findById(idEmployee);
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void deleteByPersonIdNumber(String idNumber) {
        employeeRepository.deleteByPersonIDNumber(idNumber);
    }

    public boolean resourceFound(long id) {
        return employeeRepository.findById(id).isPresent();
    }
}
