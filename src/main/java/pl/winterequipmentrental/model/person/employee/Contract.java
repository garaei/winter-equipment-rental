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
    @ManyToOne
    @JoinColumn(name = "type_contract_id")
    private TypeContract typeContract;

    @Setter
    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Contract(BigDecimal salary) {
        this.salary = salary;
    }
}
