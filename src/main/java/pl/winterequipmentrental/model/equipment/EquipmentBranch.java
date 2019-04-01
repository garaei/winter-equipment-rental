package pl.winterequipmentrental.model.equipment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.winterequipmentrental.model.branch.Branch;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Sprzet_filii")
public class EquipmentBranch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_sprzet_filii")
    private long id;

    @Setter
    @Column(name = "stan_aktualny", nullable = false)
    private int currentStatus;

    @Setter
    @Column(name = "zasob_magazynu", nullable = false)
    private int storeResources;

    @Setter
    @Column(name = "id_branch", insertable = false, updatable = false, nullable = false)
    private String extensionBranch;

    @Setter
    @ManyToOne
    @JoinColumn(name = "id_branch", referencedColumnName = "nr_wewnetrzny")
    private Branch branch;

    @Setter
    @Column(name = "id_equipment", insertable = false, updatable = false, nullable = false)
    private String equipmentCode;

    @Setter
    @ManyToOne
    @JoinColumn(name = "id_equipment", referencedColumnName = "kod")
    private Equipment equipment;
}
