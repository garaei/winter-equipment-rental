package pl.winterequipmentrental.services.person.employee;

import org.springframework.stereotype.Service;
import pl.winterequipmentrental.model.person.employee.Employee;

import java.util.Optional;
import java.util.Set;

@Service
public interface EmployeeService {

    Employee createEmployee(Employee employee);

    Set<Employee> findAllEmployees();

    Optional<Employee> findEmployeeById(Long id);

    Optional<Employee> findEmployeeByPesel(String pesel);

    Optional<Employee> findEmployeeByPersonIdNumber(String idNumber);

    Optional<Employee> findEmployeeByConctractNumber(String contractNumber);

    Set<Employee> findEmployeeByNumbersPhone(String[] numbersPhone);

    Set<Employee> findEmployeesWhoAreEmploymentBetween(int from, int to);

    Set<Employee> findEmployeesByPositionId(long positionId);

    Set<Employee> findEmployeesByPositionName(String positionName);

    Set<Employee> findEmployeesByAddressId(long addressId);

    Optional<Employee> updateEmployee(Employee employee, long idEmployee);

    Optional<Employee> updateEmployeeHired(boolean hired, long idEmployee);

    void deleteById(long id);

    void deleteByPersonIdNumber(String idNumber);
}
