package pl.winterequipmentrental.model.additional;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Oplaty")
public class Charge implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_oplata")
    private long id;

    @Setter
    @Column(name = "wartosc", nullable = false, precision = 6, scale = 2)
    private BigDecimal charge;

    @Setter
    @Column(name = "data_wplaty", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfPayment;

    public Charge(BigDecimal charge, Date dateOfPayment) {
        this.charge = charge;
        this.dateOfPayment = dateOfPayment;
    }
}
