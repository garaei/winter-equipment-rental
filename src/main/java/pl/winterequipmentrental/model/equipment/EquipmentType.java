package pl.winterequipmentrental.model.equipment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "EquipmentTypes")
public class EquipmentType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_equipment_type", nullable = false, unique = true, insertable = false, updatable = false)
    private long id;

    @Setter
    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

    @Setter
    @Lob
    @Column(name = "description")
    private String description;

    public EquipmentType(String name) {
        this.name = name;
    }

    public EquipmentType(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
