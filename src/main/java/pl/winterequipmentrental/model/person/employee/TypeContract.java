package pl.winterequipmentrental.model.person.employee;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Rodzaje_umow")
public class TypeContract implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_rodzaj_umowy")
    private long id;

    @Setter
    @Column(name = "nazwa", nullable = false, length = 40, unique = true)
    private String name;

    @Setter
    @Lob
    @Column(name = "opis")
    private String description;

    @Setter
    @OneToMany(mappedBy = "typeContract")
    private List<Contract> contracts;

    public TypeContract(String name) {
        this.name = name;
    }

    public TypeContract(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
