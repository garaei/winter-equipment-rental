package pl.winterequipmentrental.model.person.client;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.winterequipmentrental.model.address.Address;
import pl.winterequipmentrental.model.loan.Loan;
import pl.winterequipmentrental.model.person.Person;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Customers")
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idCustomer", nullable = false, unique = true)
    private long id;

    @Setter
    @Column(name = "email", unique = true, length = 40)
    private String email;

    @Setter
    @Column(name = "pesel", unique = true, length = 11)
    private String pesel;

    @Setter
    @Column(name = "person_phone", length = 9)
    private String numberPhone;

    @Setter
    @Embedded
    private Person person;

    @Setter
    @Embedded
    private Company company;

    @Setter
    @Column(name = "id_address", insertable = false, updatable = false)
    private long addressId;

    @Setter
    @ManyToOne
    @JoinColumn(name = "id_address")
    private Address address;

    @Setter
    @OneToMany(mappedBy = "customer")
    private List<Loan> loans;

    public Customer(Person person) {
        this.person = person;
    }

    public Customer(String email, String pesel, Person person) {
        this.email = email;
        this.pesel = pesel;
        this.person = person;
    }

    public Customer(String email, Person person, Company company) {
        this.email = email;
        this.person = person;
        this.company = company;
    }

    public void addLoan(Loan loan) {
        if (loans == null)
            loans = new ArrayList<>();
        loans.add(loan);
    }
}
