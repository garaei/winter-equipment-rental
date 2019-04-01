package pl.winterequipmentrental.model.branch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import pl.winterequipmentrental.model.equipment.EquipmentBranch;
import pl.winterequipmentrental.model.address.Address;
import pl.winterequipmentrental.model.loan.Loan;
import pl.winterequipmentrental.model.phone.BranchPhone;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Filie")
public class Branch implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_filia")
    private long id;

    @Setter
    @NaturalId
    @Column(name = "nr_wewnetrzny", nullable = false, unique = true, length = 20)
    private String extension;

    @Setter
    @OneToMany(mappedBy = "branch")
    @JsonIgnoreProperties("branch")
    private List<BranchPhone> branchPhones;

    @Setter
    @Column(name = "id_address", insertable = false, updatable = false, nullable = false)
    private long addressId;

    @Setter
    @OneToOne
    @JoinColumn(name = "id_address")
    private Address address;

    @Setter
    @OneToMany(mappedBy = "branch")
    private List<Loan> loans;

    @Setter
    @OneToMany(mappedBy = "branch")
    private List<EquipmentBranch> equipmentBranches;

    public Branch(String extension) {
        this.extension = extension;
    }

    public void addBranchPhones(BranchPhone branchPhone) {
        if (branchPhones == null)
            branchPhones = new ArrayList<>();
        branchPhones.add(branchPhone);
    }
}
