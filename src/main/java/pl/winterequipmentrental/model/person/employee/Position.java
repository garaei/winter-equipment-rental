package pl.winterequipmentrental.model.person.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Positions")
public class Position implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_position", nullable = false, unique = true, insertable = false, updatable = false)
    private long id;

    @Setter
    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

    @Setter
    @Lob
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "position")
    @JsonIgnore
    private Set<Employee> employees;

    public Position(String name) {
        this.name = name;
    }

    public Position(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(name, position.name) &&
                Objects.equals(description, position.description) &&
                Objects.equals(employees, position.employees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, employees);
    }
}
