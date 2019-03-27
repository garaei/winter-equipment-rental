package pl.winterequipmentrental.model.person.employee;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.winterequipmentrental.model.account.User;
import pl.winterequipmentrental.model.person.Person;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Pracownicy")
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

    @Setter
    @OneToOne(mappedBy = "employee")
    private User user;

    @Setter
    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    @Setter
    @OneToOne(mappedBy = "employee")
    private Contract contract;

    public Employee(String pesel, String email, Person person) {
        this.pesel = pesel;
        this.email = email;
        this.person = person;
    }
}

