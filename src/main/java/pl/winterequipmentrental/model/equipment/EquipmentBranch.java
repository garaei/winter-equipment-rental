package pl.winterequipmentrental.model.equipment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.winterequipmentrental.model.branch.Branch;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Equipment_branch")
public class EquipmentBranch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_equipment_branch", nullable = false, unique = true, insertable = false, updatable = false)
    private long id;

    /**
     * stores information about the amount of equipment in the branch
     * which are available current time
     */
    @Setter
    @Column(name = "current_status", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int currentStatus;

    /**
     * stores information about the amount of equipment in the branch
     */
    @Setter
    @Column(name = "store_resources", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int storeResources;

    @Setter
    @Column(name = "id_branch", insertable = false, updatable = false, nullable = false)
    private String extensionBranch;

    @Setter
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_branch", referencedColumnName = "extension")
    @JsonIgnore
    private Branch branch;

    @Setter
    @Column(name = "id_equipment", insertable = false, updatable = false, nullable = false, length = 30)
    private String equipmentCode;

    @Setter
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_equipment", referencedColumnName = "code", nullable = false)
    @JsonIgnore
    private Equipment equipment;

    public EquipmentBranch(Branch branch, Equipment equipment) {
        this.branch = branch;
        this.equipment = equipment;
    }

    public EquipmentBranch(int currentStatus, int storeResources, Branch branch, Equipment equipment) {
        this.currentStatus = currentStatus;
        this.storeResources = storeResources;
        this.branch = branch;
        this.equipment = equipment;
    }
}
