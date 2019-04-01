package pl.winterequipmentrental.model.person.employee;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import pl.winterequipmentrental.model.account.User;
import pl.winterequipmentrental.model.address.Address;
import pl.winterequipmentrental.model.loan.Loan;
import pl.winterequipmentrental.model.person.Person;
import pl.winterequipmentrental.model.phone.EmployeePhone;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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
    @NaturalId
    @Column(name = "pesel", nullable = false, length = 11, unique = true)
    private String pesel;

    @Setter
    @NaturalId
    @Column(name = "email", length = 40, unique = true)
    private String email;

    @Setter
    @Embedded
    private Person person;

    @Setter
    @OneToOne(mappedBy = "employee")
    private User user;

    @Setter
    @OneToOne(mappedBy = "employee")
    private Contract contract;

    @Setter
    @Column(name = "id_position", insertable =  false, updatable = false, nullable = false)
    private long positionId;

    @Setter
    @ManyToOne
    @JoinColumn(name = "id_position")
    private Position position;

    @Setter
    @Column(name = "id_address", insertable = false, updatable = false, nullable = false)
    private long addressId;

    @Setter
    @ManyToOne
    @JoinColumn(name = "id_address")
    private Address address;

    @Setter
    @OneToMany(mappedBy = "employee")
    private List<EmployeePhone> employeePhones;

    @Setter
    @OneToMany
    private List<Loan> loans;

    public Employee(String pesel, String email, Person person) {
        this.pesel = pesel;
        this.email = email;
        this.person = person;
    }
}

