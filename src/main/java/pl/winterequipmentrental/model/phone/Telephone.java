package pl.winterequipmentrental.model.phone;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Telephone implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id_telefon")
    private long id;

    @Setter
    @Column(name = "numer", nullable = false, length = 9, unique = true)
    private String numberPhone;

    public Telephone(String numberPhone) {
        this.numberPhone = numberPhone;
    }
}