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
    @Column(name = "idPhone", nullable = false, unique = true)
    private long id;

    @Setter
    @Column(name = "number", nullable = false, unique = true, length = 9)
    private String numberPhone;

    public Telephone(String numberPhone) {
        this.numberPhone = numberPhone;
    }
}
