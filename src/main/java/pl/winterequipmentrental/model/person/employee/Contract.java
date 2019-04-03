package pl.winterequipmentrental.model.person.employee;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Contracts")
public class Contract implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idContract")
    private long id;

    @Setter
    @Column(name = "salary", precision = 6, scale = 2)
    private BigDecimal salary;

    @Setter
    @Column(name = "id_type_contract", insertable = false, updatable = false, nullable = false)
    private long typeContractId;

    @Setter
    @ManyToOne
    @JoinColumn(name = "id_type_contract")
    private TypeContract typeContract;

    @Setter
    @Column(name = "id_employee", insertable = false, updatable = false, nullable = false)
    private String pesel;

    @Setter
    @OneToOne
    @JoinColumn(name = "id_employee", referencedColumnName = "pesel", nullable = false)
    private Employee employee;

    public Contract(BigDecimal salary, TypeContract typeContract, Employee employee) {
        this.salary = salary;
        this.typeContract = typeContract;
        this.employee = employee;
    }
}
