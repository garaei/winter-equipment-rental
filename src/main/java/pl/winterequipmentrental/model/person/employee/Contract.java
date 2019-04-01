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
@Table(name = "Umowy")
public class Contract implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_umowa")
    private long id;

    @Setter
    @Column(name = "wynagrodzenie", precision = 6, scale = 2)
    private BigDecimal salary;

    @Setter
    @Column(name = "type_contract_id", insertable = false, updatable = false, nullable = false)
    private long typeContractId;

    @Setter
    @ManyToOne
    @JoinColumn(name = "type_contract_id")
    private TypeContract typeContract;

    @Setter
    @Column(name = "employee_id", insertable = false, updatable = false, nullable = false)
    private String pesel;

    @Setter
    @OneToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "pesel")
    private Employee employee;

    public Contract(BigDecimal salary) {
        this.salary = salary;
    }
}
