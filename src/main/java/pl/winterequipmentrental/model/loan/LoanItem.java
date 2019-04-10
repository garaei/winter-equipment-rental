package pl.winterequipmentrental.model.loan;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
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
@Table(name = "Loan_items")
@Builder
public class LoanItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_loan_item", nullable = false, unique = true, insertable = false, updatable = false)
    private long id;

    @Setter
    @Column(name = "charge", precision = 6, scale = 2)
    private BigDecimal charge;

    @Setter
    @Column(name = "rental_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date rentalDate;

    @Setter
    @Column(name = "date_return")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfReturn;

    /**
     * If status is set:
     * 0 - the loan item is in progress
     * 1 - the loan item is completed
     */
    @Setter
    @Column(name = "status", nullable = false, columnDefinition = "BIT DEFAULT 0")
    private boolean status;

    @Setter
    @Column(name = "loan_number", insertable = false, updatable = false, nullable = false)
    private String loanNumber;

    @Setter
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "loan_number", referencedColumnName = "loan_number")
    @JsonIgnore
    private Loan loan;

    @Setter
    @Column(name = "code_equipment", insertable = false, updatable = false)
    private String equipmentId;

    @Setter
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "code_equipment", referencedColumnName = "code")
    @JsonIgnore
    private Equipment equipment;

    @Setter
    @Column(name = "relief", insertable = false, updatable = false, length = 40)
    private String typeReliefName;

    @Setter
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "relief", referencedColumnName = "name")
    @JsonIgnore
    private TypeRelief typeRelief;
}




