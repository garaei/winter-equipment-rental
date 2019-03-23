package pl.winterequipmentrental.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

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
    @Embedded
    private Person person;

    @Setter
    @Embedded
    private Company company;

    public Customer(String email, Person person, Company company) {
        this.email = email;
        this.person = person;
        this.company = company;
    }
}
