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
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Equipment")
public class Equipment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idEquipment", nullable = false, unique = true, insertable = false, updatable = false)
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
    @Column(name = "size", nullable = false, length = 15)
    private String size;

    @Setter
    @Column(name = "id_type_equipment", insertable = false, updatable = false)
    private String typeEquipmentId;

    @Setter
    @ManyToOne
    @JoinColumn(name = "id_type_equipment", referencedColumnName = "name")
    @JsonIgnoreProperties({"id", "description"})
    private EquipmentType equipmentType;

    @Setter
    @OneToMany(mappedBy = "equipment")
    @JsonIgnore
    private List<EquipmentBranch> equipmentBranches;

    @Setter
    @ManyToMany(mappedBy = "equipmentList")
    @JsonIgnoreProperties({"id"})
    private List<PriceList> priceLists;

    public Equipment(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Equipment(String code, String name, String manufacturer) {
        this.code = code;
        this.name = name;
        this.manufacturer = manufacturer;
    }

    public Equipment(String code, String name, String manufacturer, EquipmentType equipmentType) {
        this.code = code;
        this.name = name;
        this.manufacturer = manufacturer;
        this.equipmentType = equipmentType;
    }

    public void addEquipmentBranch(EquipmentBranch equipmentBranch) {
        if (equipmentBranches == null)
            equipmentBranches = new ArrayList<>();
        equipmentBranches.add(equipmentBranch);
    }

    public void addPrice(PriceList priceList) {
        if (priceLists == null)
            priceLists = new ArrayList<>();
        priceLists.add(priceList);
    }
}
