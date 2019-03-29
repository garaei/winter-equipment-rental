package pl.winterequipmentrental.model.equipment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Sprzet")
public class Equipment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_sprzet")
    private long id;

    @Setter
    @Column(name = "kod", nullable = false, unique = true, length = 30)
    private String code;

    @Setter
    @Column(name = "nazwa", nullable = false)
    private String name;

    @Setter
    @Column(name = "producent", nullable = false, length = 30)
    private String manufacturer;

    @Setter
    @Column(name = "szerokosc", length = 10)
    private String width;

    @Setter
    @Column(name = "wysokosc", length = 10)
    private String height;

    @Setter
    @Column(name = "rozmiar", nullable = false, length = 15)
    private String size;

    @Setter
    @Column(name = "stan_aktualny", nullable = false)
    private int currentStatus;

    @Setter
    @Column(name = "zasob_magazynu", nullable = false)
    private int storeResources;

    @Setter
    @Column(name = "nr_wewnetrzny_filii", nullable = false, length = 20)
    private String extensionBranch;

    @Setter
    @Column(name = "id_type_equipment", insertable = false, updatable = false)
    private String typeEquipmentId;

    @Setter
    @ManyToOne
    @JoinColumn(name = "id_type_equipment", referencedColumnName = "nazwa")
    private TypeEquipment typeEquipment;
}
