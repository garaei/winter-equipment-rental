package pl.winterequipmentrental.model.loan;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import pl.winterequipmentrental.model.branch.Branch;
import pl.winterequipmentrental.model.person.client.Customer;
import pl.winterequipmentrental.model.person.employee.Employee;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
    @NaturalId
    @Column(name = "nr_wypozyczenia", nullable = false, unique = true, length = 20)
    private String numberLoan;

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
    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<LoanItem> loanItemList;

    @Setter
    @Column(name = "nr_wewnetrzny_filii", insertable = false, updatable = false, nullable = false)
    private String extensionBranch;

    @Setter
    @ManyToOne
    @JoinColumn(name = "nr_wewnetrzny_filii", referencedColumnName = "nr_wewnetrzny")
    private Branch branch;

    @Setter
    @Column(name = "id_customer", insertable = false, updatable = false, nullable = false)
    private long customerId;

    @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_customer")
    private Customer customer;

    @Setter
    @Column(name = "id_employee", insertable = false, updatable = false, nullable = false)
    private long employeeId;

    @Setter
    @ManyToOne
    @JoinColumn(name = "id_employee")
    private Employee employee;
}
