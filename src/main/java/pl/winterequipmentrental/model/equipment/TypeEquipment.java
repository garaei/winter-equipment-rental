package pl.winterequipmentrental.model.equipment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.winterequipmentrental.model.additional.PriceList;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "TypeEquipments")
public class TypeEquipment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idTypeEquipment", nullable = false, unique = true)
    private long id;

    @Setter
    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

    @Setter
    @Lob
    @Column(name = "description")
    private String description;

    @Setter
    @Embedded
    private PriceList priceList;

    public TypeEquipment(String name) {
        this.name = name;
    }

    public TypeEquipment(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public TypeEquipment(String name, PriceList priceList) {
        this.name = name;
        this.priceList = priceList;
    }

    public TypeEquipment(String name, String description, PriceList priceList) {
        this.name = name;
        this.description = description;
        this.priceList = priceList;
    }
}
