package pl.winterequipmentrental.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {
    @Column(name = "imie", nullable = false, length = 20)
    private String firstName;

    @Column(name = "drugie_imie", length = 20)
    private String secondName;

    @Column(name = "nazwisko", nullable = false, length = 40)
    private String lastName;

    @Column(name = "nr_dowodu", nullable = false, length = 10, unique = true)
    private String IDNumber;

    @Column(name = "telefon", length = 12)
    private String phoneNumber;
}
