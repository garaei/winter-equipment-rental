package pl.winterequipmentrental.model.person.employee;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "TypeContracts")
public class TypeContract implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idTypeContract")
    private long id;

    @Setter
    @Column(name = "name", nullable = false, length = 40, unique = true)
    private String name;

    @Setter
    @Lob
    @Column(name = "description")
    private String description;

    @Setter
    @OneToMany(mappedBy = "typeContract")
    private List<Contract> contracts;

    public TypeContract(String name) {
        this.name = name;
    }

    public TypeContract(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void addContract(Contract contract) {
        if (contracts == null)
            contracts = new ArrayList<>();
        contracts.add(contract);
    }
}



// TODO Change TypeContract Entity to Enum or interface
