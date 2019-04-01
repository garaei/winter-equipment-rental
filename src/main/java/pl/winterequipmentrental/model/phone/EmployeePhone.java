package pl.winterequipmentrental.model.phone;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.winterequipmentrental.model.person.employee.Employee;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Telefony_pracownikow")
public class EmployeePhone extends Telephone {
    @Setter
    @Column(name = "id_employee", insertable = false, updatable = false, nullable = false)
    private long employeeId;

    @Setter
    @ManyToOne
    @JoinColumn(name = "id_employee")
    private Employee employee;
}
