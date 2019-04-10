package pl.winterequipmentrental.model.equipment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import pl.winterequipmentrental.model.additional.PriceList;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Equipments")
public class Equipment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_equipment", nullable = false, unique = true, insertable = false, updatable = false)
    private long id;

    @Setter
    @NaturalId
    @Column(name = "code", nullable = false, unique = true, length = 30)
    private String code;

    @Setter
    @Column(name = "name", nullable = false)
    private String name;

    @Setter
    @Column(name = "manufacturer", nullable = false, length = 30)
    private String manufacturer;

    @Setter
    @Column(name = "width", length = 10)
    private String width;

    @Setter
    @Column(name = "height", length = 10)
    private String height;

    @Setter
    @Column(name = "size", length = 15)
    private String size;

    @Setter
    @Column(name = "id_type_equipment", insertable = false, updatable = false)
    private String typeEquipmentId;

    @Setter
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_type_equipment", referencedColumnName = "name")
    @JsonIgnoreProperties({"id", "description"})
    private EquipmentType equipmentType;

    @Setter
    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<EquipmentBranch> equipmentBranches;

    @Setter
    @ManyToMany(mappedBy = "equipmentList", cascade = {CascadeType.PERSIST})
    @JsonIgnoreProperties({"id"})
    private Set<PriceList> priceLists;

    public Equipment(String code, String name, String manufacturer, EquipmentType equipmentType) {
        this.code = code;
        this.name = name;
        this.manufacturer = manufacturer;
        this.equipmentType = equipmentType;
    }

    public Equipment(String code, String name, String manufacturer, String size, EquipmentType equipmentType) {
        this.code = code;
        this.name = name;
        this.manufacturer = manufacturer;
        this.size = size;
        this.equipmentType = equipmentType;
    }

    public Equipment(String code, String name, String manufacturer, String width, String height, String size, EquipmentType equipmentType) {
        this.code = code;
        this.name = name;
        this.manufacturer = manufacturer;
        this.width = width;
        this.height = height;
        this.size = size;
        this.equipmentType = equipmentType;
    }
}
