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
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Employees")
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idEmployee", nullable = false, unique = true)
    private long id;

    @Setter
    @NaturalId
    @Column(name = "pesel", nullable = false, unique = true , length = 11)
    private String pesel;

    @Setter
    @NaturalId
    @Column(name = "email", nullable = false, unique = true, length = 40)
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
    private String positionId;

    @Setter
    @ManyToOne
    @JoinColumn(name = "id_position", referencedColumnName = "name", nullable = false)
    private Position position;

    @Setter
    @Column(name = "id_address", insertable = false, updatable = false)
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

    public Employee(String pesel,
                    String email,
                    Person person,
                    Position position) {
        this.pesel = pesel;
        this.email = email;
        this.person = person;
        this.position = position;
    }

    public Employee(String pesel,
                    String email,
                    Person person,
                    Contract contract,
                    Position position) {
        this.pesel = pesel;
        this.email = email;
        this.person = person;
        this.contract = contract;
        this.position = position;
    }

    public Employee(String pesel,
                    String email,
                    Person person,
                    Contract contract,
                    Position position,
                    Address address) {
        this.pesel = pesel;
        this.email = email;
        this.person = person;
        this.contract = contract;
        this.position = position;
        this.address = address;
    }

    public Employee(String pesel,
                    String email,
                    Person person,
                    Contract contract,
                    Position position,
                    Address address,
                    List<EmployeePhone> employeePhones) {
        this.pesel = pesel;
        this.email = email;
        this.person = person;
        this.contract = contract;
        this.position = position;
        this.address = address;
        this.employeePhones = employeePhones;
    }


    public void addEmployeePhone(EmployeePhone employeePhone) {
        if (employeePhones == null)
            employeePhones = new ArrayList<>();
        employeePhones.add(employeePhone);
    }

    public void addLoan(Loan loan) {
        if (loans == null)
            loans = new ArrayList<>();
        loans.add(loan);
    }
}

// TODO Add new column -> number_account_bank or new table with this number and then this class should contain a relation
