package pl.winterequipmentrental.services.phone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.winterequipmentrental.model.person.employee.Employee;
import pl.winterequipmentrental.model.phone.EmployeePhone;
import pl.winterequipmentrental.repository.EmployeePhoneRepository;
import pl.winterequipmentrental.repository.EmployeeRepository;

import java.util.Optional;
import java.util.Set;

@Service
public class EmployeePhoneServiceImpl implements EmployeePhoneService {

    @Autowired
    private EmployeePhoneRepository employeePhoneRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeePhone createEmployeePhone(EmployeePhone employeePhone) {
        return employeePhoneRepository.save(employeePhone);
    }

    @Override
    public EmployeePhone createEmployeePhoneByNumberAndEmployeeId(String numberPhone, long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);

        if (employee.isPresent()) {
            EmployeePhone employeePhone = new EmployeePhone(numberPhone, employee.get());
            return employeePhoneRepository.save(employeePhone);
        }

        return null;
    }

    @Override
    public Set<EmployeePhone> findAllEmployeePhones() {
        return employeePhoneRepository.findAll();
    }

    @Override
    public Optional<EmployeePhone> findEmployeePhoneByPhoneNumber(String phoneNumber) {
        return employeePhoneRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public Optional<EmployeePhone> findEmployeePhoneByEmployee(Employee employee) {
        return employeePhoneRepository.findByEmployee(employee);
    }

    @Override
    public Set<EmployeePhone> findEmployeePhonesByEmployeeId(long employeeId) {
        return employeePhoneRepository.findByEmployeeId(employeeId);
    }

    @Override
    public Set<EmployeePhone> findEmployeePhonesByPositionId(long positionId) {
        return employeePhoneRepository.findByEmployee_PositionId(positionId);
    }

    @Override
    public Set<EmployeePhone> findEmployeePhonesByEmployeeEmail(String email) {
        return employeePhoneRepository.findByEmployee_Email(email);
    }

    @Override
    public void deleteByPhoneNumber(String number) {
        employeePhoneRepository.deleteByPhoneNumber(number);
    }

    @Override
    public void deleteAllByEmployeeId(long employeeId) {
        employeePhoneRepository.deleteAllByEmployeeId(employeeId);
    }

    @Override
    public void deleteByEmployeeEmail(String email) {
        employeePhoneRepository.deleteEmployeePhoneByEmployeeEmail(email);
    }
}
