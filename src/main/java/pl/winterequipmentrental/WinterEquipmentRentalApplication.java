package pl.winterequipmentrental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.winterequipmentrental.model.person.employee.Employee;
import pl.winterequipmentrental.model.phone.BranchPhone;
import pl.winterequipmentrental.model.phone.EmployeePhone;

@SpringBootApplication
public class WinterEquipmentRentalApplication {

    public static void main(String[] args) {
        SpringApplication.run(WinterEquipmentRentalApplication.class, args);
    }

}
