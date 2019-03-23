package pl.winterequipmentrental.model.employee;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.winterequipmentrental.model.Person;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Pracownicy")
@Getter
@NoArgsConstructor
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_pracownik")
    private long id;

    @Setter
    @Column(name = "pesel", nullable = false, length = 11, unique = true)
    private String pesel;

    @Setter
    @Column(name = "email", length = 40, unique = true)
    private String email;

    @Setter
    @Embedded
    private Person person;

    public Employee(String pesel, String email, Person person) {
        this.pesel = pesel;
        this.email = email;
        this.person = person;
    }
}
