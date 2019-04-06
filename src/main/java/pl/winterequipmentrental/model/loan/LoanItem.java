package pl.winterequipmentrental.model.loan;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import pl.winterequipmentrental.model.additional.TypeRelief;
import pl.winterequipmentrental.model.equipment.Equipment;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "LoanItem")
public class LoanItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idLoanItem", nullable = false, unique = true)
    private long id;

    @Setter
    @Column(name = "charge", precision = 6, scale = 2)
    private BigDecimal charge;

    @Setter
    @Column(name = "rentalDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date rentalDate;

    @Setter
    @Column(name = "dateOfReturn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfReturn;

    /**
     * If status is set:
     * 0 - the loan item is in progress
     * 1 - the loan item is completed
     */
    @Setter
    @Column(name = "status", nullable = false)
    private boolean status;

    @Setter
    @Column(name = "loan_number", insertable = false, updatable = false, nullable = false)
    private String loanNumber;

    @Setter
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "loan_number", referencedColumnName = "loan_number")
    private Loan loan;

    @Setter
    @Column(name = "code_equipment", insertable = false, updatable = false, nullable = false)
    private String equipmentId;

    @Setter
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "code_equipment", referencedColumnName = "code")
    private Equipment equipment;

    @Setter
    @Column(name = "relief", insertable = false, updatable = false, length = 40)
    private String typeReliefNaturalId;

    @Setter
    @ManyToOne
    @JoinColumn(name = "relief", referencedColumnName = "name")
    private TypeRelief typeRelief;

    public LoanItem(boolean status, Loan loan, Equipment equipment) {
        this.status = status;
        this.loan = loan;
        this.equipment = equipment;
    }
}




