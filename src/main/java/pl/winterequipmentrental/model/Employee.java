package pl.winterequipmentrental.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Column(name = "pesel", nullable = false, length = 11, unique = true)
    private String pesel;

    @Column(name = "email", length = 40, unique = true)
    private String email;

    @Embedded
    private Person person;

}
