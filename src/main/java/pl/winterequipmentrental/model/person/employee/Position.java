package pl.winterequipmentrental.model.person.employee;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Stanowiska")
public class Position implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Setter
    @NaturalId
    @Column(name = "nazwa", nullable = false, length = 50, unique = true)
    private String name;

    @Setter
    @Lob
    @Column(name = "opis")
    private String description;

    @Setter
    @OneToMany(mappedBy = "position")
    private Set<Employee> employees;

    public Position(String name) {
        this.name = name;
    }

    public Position(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
