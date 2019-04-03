package pl.winterequipmentrental.model.person.employee;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Positions")
public class Position implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idPosition", nullable = false, unique = true)
    private long id;

    @Setter
    @NaturalId
    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

    @Setter
    @Lob
    @Column(name = "description")
    private String description;

    @Setter
    @OneToMany(mappedBy = "position")
    private List<Employee> employees;

    public Position(String name) {
        this.name = name;
    }

    public Position(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void addEmployee(Employee employee) {
        if (employees == null)
            employees = new ArrayList<>();
        employees.add(employee);
    }
}
