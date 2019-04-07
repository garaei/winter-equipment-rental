package pl.winterequipmentrental.model.person.employee;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.winterequipmentrental.model.person.client.Company;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Contracts")
public class Contract implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_contract", insertable = false, updatable = false, nullable = false, unique = true)
    private long id;

    @Setter
    @Column(name = "contract_number", unique = true, nullable = false, length = 10)
    private String contractNumber;

    @Setter
    @Column(name = "salary", precision = 6, scale = 2)
    private BigDecimal salary;

    @Setter
    @Embedded
    private Company company;

    @Setter
    @Lob
    @Column(name = "conditions")
    private String conditionsEmployment;

    @Setter
    @Column(name = "type_contract")
    @Enumerated(value = EnumType.STRING)
    private ContractType contractType;

    @Setter
    @Column(name = "period_from")
    @Temporal(TemporalType.DATE)
    private Date periodFrom;

    @Setter
    @Column(name = "period_to")
    @Temporal(TemporalType.DATE)
    private Date periodTo;

    @Setter
    @Column(name = "breaking_date")
    @Temporal(TemporalType.DATE)
    private Date dateBreaking;

    @Setter
    @Column(name = "id_employee", insertable = false, updatable = false, nullable = false)
    private long employeeId;

    @Setter
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_employee", nullable = false, referencedColumnName = "ID_EMPLOYEE")
    @JsonIgnoreProperties({"position", "address", "employeePhones", "loans", "employerContracts", "contract", "user"})
    private Employee employee;

    @Setter
    @Column(name = "id_employer", insertable = false, updatable = false, nullable = false)
    private long employerId;

    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_employer", nullable = false, referencedColumnName = "ID_EMPLOYEE")
    @JsonIgnoreProperties({"position", "address", "employeePhones", "loans", "employerContracts", "contract", "user"})
    private Employee employer;

    public Contract(ContractType contractType, Employee employee) {
        this.contractType = contractType;
        this.employee = employee;
    }

    public Contract(BigDecimal salary, ContractType contractType, Employee employee) {
        this.salary = salary;
        this.contractType = contractType;
        this.employee = employee;
    }
}
