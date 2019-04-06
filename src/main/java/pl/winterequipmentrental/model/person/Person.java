package pl.winterequipmentrental.model.person;

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
    @Setter
    @Column(name = "name", nullable = false, length = 20)
    private String firstName;

    @Setter
    @Column(name = "second_name", length = 20)
    private String secondName;

    @Setter
    @Column(name = "last_name", nullable = false, length = 40)
    private String lastName;

    @Setter
    @Column(name = "id_number", nullable = false, unique = true, length = 10)
    private String IDNumber;

    public Person(String firstName, String lastName, String IDNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.IDNumber = IDNumber;
    }
}
