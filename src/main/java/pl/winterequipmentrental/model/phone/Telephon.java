package pl.winterequipmentrental.model.phone;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Telephon implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id_telefon")
    private long id;

    @Setter
    @Column(name = "numer", nullable = false, length = 9, unique = true)
    private String numberPhone;

    public Telephon(String numberPhone) {
        this.numberPhone = numberPhone;
    }
}
