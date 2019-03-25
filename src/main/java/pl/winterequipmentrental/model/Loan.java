package pl.winterequipmentrental.model;

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
@Table(name = "Wypozyczenia")
public class Loan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_wypozyczenie")
    private long id;

    @Setter
    @Column(name = "data_pobrania")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rentalDate;

    @Setter
    @Column(name = "data_zwrotu")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfReturn;

    @Setter
    @Column(name = "status", nullable = false, length = 1)
    private short status;

    @Setter
    @Column(name = "cena_calkowita", precision = 6, scale = 2)
    private BigDecimal totalPrice;

    @Setter
    @Column(name = "nr_wewnetrzny_filii", nullable = false, length = 20)
    private String extensionBranch;
}
