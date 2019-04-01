package pl.winterequipmentrental.model.additional;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Rodzaje_ulg")
public class TypeRelief implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_ulga")
    private long id;

    @Setter
    @NaturalId
    @Column(name = "nazwa", nullable = false, unique = true, length = 40)
    private String name;

    @Setter
    @Column(name = "znizka_procentowa", nullable = false, length = 3)
    private short discount;

    @Setter
    @Lob
    @Column(name = "opis")
    private String description;

    public TypeRelief(String name, short discount) {
        this.name = name;
        this.discount = discount;
    }

    public TypeRelief(String name, short discount, String description) {
        this.name = name;
        this.discount = discount;
        this.description = description;
    }
}
