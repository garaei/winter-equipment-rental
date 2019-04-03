package pl.winterequipmentrental.model.phone;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.winterequipmentrental.model.person.employee.Employee;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "PhoneEmployees")
public class EmployeePhone extends Telephone {
    @Setter
    @Column(name = "id_employee", insertable = false, updatable = false, nullable = false)
    private long employeeId;

    @Setter
    @ManyToOne
    @JoinColumn(name = "id_employee", nullable = false)
    private Employee employee;

    public EmployeePhone(String numberPhone, Employee employee) {
        super(numberPhone);
        this.employee = employee;
    }
}
