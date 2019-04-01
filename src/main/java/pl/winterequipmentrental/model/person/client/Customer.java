package pl.winterequipmentrental.model.person.client;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.winterequipmentrental.model.address.Address;
import pl.winterequipmentrental.model.loan.Loan;
import pl.winterequipmentrental.model.person.Person;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Klienci")
@Getter
@NoArgsConstructor
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_klient")
    private long id;

    @Setter
    @Column(name = "email", length = 40, unique = true)
    private String email;

    @Setter
    @Column(name = "pesel", length = 11, unique = true)
    private String pesel;

    @Setter
    @Column(name = "telefon", length = 9)
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

    public Customer(String email, Person person, Company company) {
        this.email = email;
        this.person = person;
        this.company = company;
    }

    public Customer(String email, String pesel, Person person, Company company) {
        this.email = email;
        this.pesel = pesel;
        this.person = person;
        this.company = company;
    }

    public Customer(String email, String pesel, String numberPhone, Person person, Company company) {
        this.email = email;
        this.pesel = pesel;
        this.numberPhone = numberPhone;
        this.person = person;
        this.company = company;
    }
}
