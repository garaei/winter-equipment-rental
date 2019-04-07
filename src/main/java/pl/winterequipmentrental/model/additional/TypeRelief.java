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
@Table(name = "TypeRelief")
public class TypeRelief implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idTypeRelief", nullable = false, unique = true, insertable = false, updatable = false)
    private long id;

    @Setter
    @NaturalId
    @Column(name = "name", nullable = false, unique = true, length = 40)
    private String name;

    @Setter
    @Column(name = "discount", nullable = false, length = 3)
    private short discount;

    @Setter
    @Lob
    @Column(name = "description")
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
