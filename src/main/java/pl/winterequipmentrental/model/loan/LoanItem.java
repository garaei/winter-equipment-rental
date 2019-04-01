package pl.winterequipmentrental.model.loan;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Pozycje_wypozyczen")
public class LoanItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_pozycja")
    private long id;

    @Setter
    @Column(name = "naleznosc", precision = 6, scale = 2)
    private BigDecimal charge;

    @Setter
    @Column(name = "data_pobrania", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date rentalDate;

    @Setter
    @Column(name = "data_zdania")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfReturn;

    @Setter
    @Column(name = "status", nullable = false)
    private boolean status;
}




