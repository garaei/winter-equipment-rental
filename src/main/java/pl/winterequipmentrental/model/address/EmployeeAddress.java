package pl.winterequipmentrental.model.address;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.winterequipmentrental.model.person.employee.Employee;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Employee_address")
public class EmployeeAddress extends Address {

    @OneToOne(mappedBy = "address")
    @JsonIgnore
    private Employee employee;

    public EmployeeAddress(String city,
                           String locality,
                           String zipCode,
                           String buildingNumber,
                           Province province) {
        super(city, locality, zipCode, buildingNumber, province);
    }

    public EmployeeAddress(String city,
                           String locality,
                           String street,
                           String zipCode,
                           String buildingNumber,
                           Province province) {
        super(city, locality, street, zipCode, buildingNumber, province);
    }

    public EmployeeAddress(String city,
                           String locality,
                           String street,
                           String zipCode,
                           String buildingNumber,
                           String apartmentNumber,
                           Province province) {
        super(city, locality, street, zipCode, buildingNumber, apartmentNumber, province);
    }
}
