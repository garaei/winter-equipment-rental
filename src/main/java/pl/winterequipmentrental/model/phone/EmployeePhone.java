package pl.winterequipmentrental.model.phone;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.winterequipmentrental.model.person.employee.Employee;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "EmployeePhones")
public class EmployeePhone extends Phone {
    @Setter
    @Column(name = "id_employee", insertable = false, updatable = false, nullable = false)
    private long employeeId;

    @Setter
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_employee")
    @JsonIgnore
    private Employee employee;

    public EmployeePhone(String numberPhone, Employee employee) {
        super(numberPhone);
        this.employee = employee;
    }
}
