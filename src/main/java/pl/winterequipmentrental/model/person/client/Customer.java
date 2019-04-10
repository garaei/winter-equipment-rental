package pl.winterequipmentrental.model.person.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.winterequipmentrental.model.address.CustomerAddress;
import pl.winterequipmentrental.model.loan.Loan;
import pl.winterequipmentrental.model.person.Person;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Table(name = "Customers")
@Builder
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_customer", nullable = false, unique = true, insertable = false, updatable = false)
    private long id;

    @Setter
    @Column(name = "email", unique = true, length = 40)
    private String email;

    @Setter
    @Column(name = "pesel", unique = true, length = 11)
    @JsonIgnore
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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_address")
    @JsonIgnoreProperties({"customers", "provinceId"})
    private CustomerAddress address;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private Set<Loan> loans;
}
