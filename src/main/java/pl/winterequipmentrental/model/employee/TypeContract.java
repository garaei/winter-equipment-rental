package pl.winterequipmentrental.model.employee;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Typy_umow")
public class TypeContract implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_typ_umowy")
    private long id;

    @Setter
    @Column(name = "nazwa", nullable = false, length = 40, unique = true)
    private String name;

    @Setter
    @Lob
    @Column(name = "opis")
    private String description;

    public TypeContract(String name) {
        this.name = name;
    }

    public TypeContract(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
