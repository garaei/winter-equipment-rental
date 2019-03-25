package pl.winterequipmentrental.model.additional;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Cennik")
public class PriceList implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_cena")
    private long id;

    @Setter
    @Column(name = "czas", nullable = false)
    private int time;

    @Setter
    @Column(name = "cena", nullable = false, precision = 6, scale = 2)
    private BigDecimal price;

    public PriceList(int time, BigDecimal price) {
        this.time = time;
        this.price = price;
    }
}
