package pl.winterequipmentrental.services.phone;

import org.springframework.stereotype.Service;
import pl.winterequipmentrental.model.person.employee.Employee;
import pl.winterequipmentrental.model.phone.EmployeePhone;

import java.util.Optional;
import java.util.Set;

@Service
public interface EmployeePhoneService {

    EmployeePhone createEmployeePhone(EmployeePhone employeePhone);

    EmployeePhone createEmployeePhoneByNumberAndEmployeeId(String numberPhone, long employeeId);

    Set<EmployeePhone> findAllEmployeePhones();

    Optional<EmployeePhone> findEmployeePhoneByPhoneNumber(String phoneNumber);

    Optional<EmployeePhone> findEmployeePhoneByEmployee(Employee employee);

    Set<EmployeePhone> findEmployeePhonesByEmployeeId(long employeeId);

    Set<EmployeePhone> findEmployeePhonesByPositionId(long positionId);

    Set<EmployeePhone> findEmployeePhonesByEmployeeEmail(String email);

    void deleteByPhoneNumber(String number);

    void deleteAllByEmployeeId(long employeeId);

    void deleteByEmployeeEmail(String email);

}
