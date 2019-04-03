package pl.winterequipmentrental.model.phone;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "TypePhones")
public class TypePhone implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idTypePhone", nullable = false, unique = true)
    private long id;

    @Setter
    @NaturalId
    @Column(name = "name", nullable = false, unique = true, length = 45)
    private String name;
}
