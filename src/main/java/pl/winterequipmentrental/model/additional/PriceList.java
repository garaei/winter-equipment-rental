package pl.winterequipmentrental.model.additional;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.winterequipmentrental.model.equipment.Equipment;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Price_list")
public class PriceList implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_price", nullable = false, unique = true, updatable = false, insertable = false)
    private long id;

    @Setter
    @Column(name = "time")
    private short time;

    @Setter
    @Column(name = "price", precision = 6, scale = 2)
    private double price;

    @Setter
    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "Pricelist_Equipment",
        joinColumns = {@JoinColumn(name = "id_price_list")},
        inverseJoinColumns = {@JoinColumn(name = "id_equipment")})
    private Set<Equipment> equipmentList;

    public PriceList(short time, double price) {
        this.time = time;
        this.price = price;
    }

    public void addEquipment(Equipment equipment) {
        if (equipmentList == null)
            equipmentList = new HashSet<>();
        equipmentList.add(equipment);
    }
}
