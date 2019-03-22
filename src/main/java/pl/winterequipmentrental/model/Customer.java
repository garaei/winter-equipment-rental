package pl.winterequipmentrental.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Column(name = "email", length = 40, unique = true)
    private String email;

    @Embedded
    private Person person;

    @Embedded
    private Company company;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
