package pl.winterequipmentrental.services.person.employee;

import pl.winterequipmentrental.model.person.employee.Employee;

import java.util.Optional;
import java.util.Set;

public interface EmployeeService {

    Employee createEmployee(Employee employee);

    Set<Employee> findAllEmployees();

    Optional<Employee> findEmployeeById(Long id);

    Optional<Employee> findEmployeeByPesel(String pesel);

    Optional<Employee> findEmployeeByPersonIdNumber(String idNumber);

    Optional<Employee> findEmployeeByConctractNumber(String contractNumber);

    Optional<Employee> findEmployeeByNumberPhone(String numberPhone);

    Set<Employee> findEmployeesByPositionId(long positionId);

    Set<Employee> findEmployeesByPositionName(String positionName);

    Set<Employee> findEmployeesByAddressId(long addressId);

    Optional<Employee> updateEmplyee(Employee employee, long idEmployee);

    Optional<Employee> updateEmplyeeHired(boolean hired);

    void deleteById(long id);

    void deleteByPersonIdNumber(String idNumber);
}
