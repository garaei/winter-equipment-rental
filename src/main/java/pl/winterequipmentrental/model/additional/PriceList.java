package pl.winterequipmentrental.model.additional;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@Embeddable
public class PriceList implements Serializable {
    @Setter
    @Column(name = "time")
    private short time;

    @Setter
    @Column(name = "price", precision = 6, scale = 2)
    private double price;

    public PriceList(short time, double price) {
        this.time = time;
        this.price = price;
    }
}
