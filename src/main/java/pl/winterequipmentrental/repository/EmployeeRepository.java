package pl.winterequipmentrental.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.winterequipmentrental.model.address.Address;
import pl.winterequipmentrental.model.person.employee.Employee;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    Set<Employee> findAll();

    Optional<Employee> findByEmail(String email);

    Optional<Employee> findByPesel(String pesel);

    Set<Employee> findByHired(boolean hired);

    Optional<Employee> findByPersonIDNumber(String idNumber);

    Optional<Employee> findByContractNumber(String contractNumber);

    Set<Employee> findByPositionId(long idPosition);

    Set<Employee> findEmployeesByPositionName(String position_name);

    @Query(value = "update Employee e set e.hired = :hired where e.id = :id")
    Employee updateHired(@Param("id") long id,
                         @Param("hired") boolean hired);

    Employee findByAddress(Address address);
}
