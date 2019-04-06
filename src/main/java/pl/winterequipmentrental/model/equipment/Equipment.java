package pl.winterequipmentrental.model.equipment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import pl.winterequipmentrental.model.loan.LoanItem;

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
    @Column(name = "idEquipment", nullable = false, unique = true)
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
    private TypeEquipment typeEquipment;

    @Setter
    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<LoanItem> loanItemList;

    @Setter
    @Column(name = "id_branch", insertable = false, updatable = false, length = 20)
    private String extensionBranch;

    @Setter
    @OneToMany(mappedBy = "equipment")
    private List<EquipmentBranch> equipmentBranches;

    public Equipment(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Equipment(String code, String name, String manufacturer) {
        this.code = code;
        this.name = name;
        this.manufacturer = manufacturer;
    }

    public Equipment(String code, String name, String manufacturer, TypeEquipment typeEquipment) {
        this.code = code;
        this.name = name;
        this.manufacturer = manufacturer;
        this.typeEquipment = typeEquipment;
    }

    public void addLoanItem(LoanItem loanItem) {
        if (loanItemList == null)
            loanItemList = new ArrayList<>();
        loanItemList.add(loanItem);
    }

    public void addEquipmentBranch(EquipmentBranch equipmentBranch) {
        if (equipmentBranches == null)
            equipmentBranches = new ArrayList<>();
        equipmentBranches.add(equipmentBranch);
    }
}
