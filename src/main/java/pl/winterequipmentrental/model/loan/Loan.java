package pl.winterequipmentrental.model.loan;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NaturalId;
import pl.winterequipmentrental.model.account.User;
import pl.winterequipmentrental.model.branch.Branch;
import pl.winterequipmentrental.model.person.client.Customer;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Table(name = "Loans")
@Builder
public class Loan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_loan", nullable = false, unique = true, insertable = false, updatable = false)
    private long id;

    @Setter
    @NaturalId
    @Column(name = "loan_number", nullable = false, unique = true, length = 20)
    private String loanNumber;

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
     * 0 - the loan is in progress
     * 1 - the loan is completed and paid for
     */
    @Setter
    @Column(name = "status", nullable = false, columnDefinition = "BIT DEFAULT 0")
    private boolean status;

    @Setter
    @Column(name = "total_price", precision = 6, scale = 2)
    private BigDecimal totalPrice;

    @Setter
    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<LoanItem> loanItemList;

    @Setter
    @Column(name = "extension_branch", insertable = false, updatable = false)
    private String extensionBranch;

    @Setter
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "extension_branch", referencedColumnName = "extension")
    @JsonIgnore
    private Branch branch;

    @Setter
    @Column(name = "id_customer", insertable = false, updatable = false)
    private long customerId;

    @Setter
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_customer")
    @JsonIgnore
    private Customer customer;

    @Setter
    @Column(name = "id_user_create", insertable = false, updatable = false)
    private long createdByUserId;

    @Setter
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_user_create")
    @JsonIgnore
    private User createdByUser;

    @Setter
    @Column(name = "id_user_completed", insertable = false, updatable = false)
    private long completedByUserId;

    @Setter
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_user_completed")
    @JsonIgnore
    private User completedByUser;
}
