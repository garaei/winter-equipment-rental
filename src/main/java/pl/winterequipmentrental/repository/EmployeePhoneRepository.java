package pl.winterequipmentrental.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.winterequipmentrental.model.person.employee.Employee;
import pl.winterequipmentrental.model.phone.EmployeePhone;

import java.util.Optional;
import java.util.Set;

@Repository
public interface EmployeePhoneRepository extends CrudRepository<EmployeePhone, Long> {

    Set<EmployeePhone> findAll();

    Optional<EmployeePhone> findByPhoneNumber(String phoneNumber);

    Optional<EmployeePhone> findByEmployee(Employee employee);

    Set<EmployeePhone> findByEmployeeId(long employeeId);

    Set<EmployeePhone> findByEmployee_PositionId(long positionId);

    Set<EmployeePhone> findByEmployee_Email(String email);

    void deleteByPhoneNumber(String phoneNumber);

    void deleteAllByEmployeeId(long EmployeeId);

    void deleteEmployeePhoneByEmployeeEmail(String email);
}
