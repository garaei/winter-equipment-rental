package pl.winterequipmentrental.model.loan;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NaturalId;
import pl.winterequipmentrental.model.branch.Branch;
import pl.winterequipmentrental.model.person.client.Customer;
import pl.winterequipmentrental.model.person.employee.Employee;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Loans")
public class Loan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idLoan", nullable = false, unique = true)
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
    @Column(name = "status", nullable = false)
    private boolean status;

    @Setter
    @Column(name = "total_price", precision = 6, scale = 2)
    private BigDecimal totalPrice;

    @Setter
    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<LoanItem> loanItemList;

    @Setter
    @Column(name = "extension_branch", insertable = false, updatable = false, nullable = false)
    private String extensionBranch;

    @Setter
    @ManyToOne
    @JoinColumn(name = "extension_branch", referencedColumnName = "extension", nullable = false)
    private Branch branch;

    @Setter
    @Column(name = "id_customer", insertable = false, updatable = false, nullable = false)
    private long customerId;

    @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_customer")
    private Customer customer;

    @Setter
    @Column(name = "ID_EMPLOYEE", insertable = false, updatable = false, nullable = false)
    private long employeeId;

    @Setter
    @ManyToOne
    @JoinColumn(name = "ID_EMPLOYEE", nullable = false)
    private Employee employee;

    public Loan(String loanNumber, Date rentalDate, boolean status) {
        this.loanNumber = loanNumber;
        this.rentalDate = rentalDate;
        this.status = status;
    }

    public Loan(String loanNumber, Date rentalDate, boolean status, Branch branch, Customer customer, Employee employee) {
        this.loanNumber = loanNumber;
        this.rentalDate = rentalDate;
        this.status = status;
        this.branch = branch;
        this.customer = customer;
        this.employee = employee;
    }

    public void addLoanItem(LoanItem loanItem) {
        if (loanItemList == null)
            loanItemList = new ArrayList<>();
        loanItemList.add(loanItem);
    }
}
