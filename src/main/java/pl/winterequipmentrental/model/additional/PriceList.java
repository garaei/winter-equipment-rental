package pl.winterequipmentrental.model.additional;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.winterequipmentrental.model.equipment.TypeEquipment;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "PriceList")
public class PriceList implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idPriceList")
    private long id;

    @Setter
    @Column(name = "time", nullable = false)
    private int time;

    @Setter
    @Column(name = "price", nullable = false, precision = 6, scale = 2)
    private BigDecimal price;

    @Setter
    @OneToMany(mappedBy = "priceList")
    private List<TypeEquipment> typeEquipments;

    public PriceList(int time, BigDecimal price) {
        this.time = time;
        this.price = price;
    }

    public void addTypeEquipment(TypeEquipment typeEquipment) {
        if (typeEquipments == null)
            typeEquipments = new ArrayList<>();
        typeEquipments.add(typeEquipment);
    }
}
