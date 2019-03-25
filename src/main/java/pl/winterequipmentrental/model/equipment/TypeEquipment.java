package pl.winterequipmentrental.model.equipment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Rodzaje_sprzetu")
public class TypeEquipment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_kategoria_sprzet")
    private long id;

    @Setter
    @Column(name = "nazwa", nullable = false, unique = true, length = 50)
    private String name;

    @Setter
    @Lob
    @Column(name = "opis")
    private String description;

    public TypeEquipment(String name) {
        this.name = name;
    }

    public TypeEquipment(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
