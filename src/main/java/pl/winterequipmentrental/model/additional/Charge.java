package pl.winterequipmentrental.model.additional;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import pl.winterequipmentrental.model.loan.Loan;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Charge")
public class Charge implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_charge", nullable = false, unique = true, insertable = false, updatable = false)
    private long id;

    @Setter
    @Column(name = "charge", nullable = false, precision = 6, scale = 2)
    private BigDecimal charge;

    @Setter
    @Column(name = "date_payment", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date dateOfPayment;

    @Setter
    @Column(name = "loan_number", insertable = false, updatable = false, length = 20)
    private String loanNumber;

    @Setter
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "loan_number", referencedColumnName = "loan_number")
    @JsonIgnore
    private Loan loan;

    public Charge(BigDecimal charge, Date dateOfPayment, Loan loan) {
        this.charge = charge;
        this.dateOfPayment = dateOfPayment;
        this.loan = loan;
    }
}
