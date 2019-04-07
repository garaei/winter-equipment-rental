package pl.winterequipmentrental.model.branch;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "Branch")
public class Branch implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_branch", nullable = false, unique = true, insertable = false, updatable = false)
    private long id;

    @Setter
    @NaturalId
    @Column(name = "extension", nullable = false, unique = true, length = 20)
    private String extension;

    @Setter
    @OneToMany(mappedBy = "branch")
    @JsonIgnore
    private List<BranchPhone> branchPhones;

    @Setter
    @Column(name = "id_address", insertable = false, updatable = false, nullable = false)
    private long addressId;

    @Setter
    @OneToOne
    @JoinColumn(name = "id_address")
    @JsonIgnoreProperties({"employees", "provinceId", "branch", "customers"})
    private Address address;

    @Setter
    @OneToMany(mappedBy = "branch")
    @JsonIgnore
    private List<Loan> loans;

    @Setter
    @OneToMany(mappedBy = "branch")
    @JsonIgnore
    private List<EquipmentBranch> equipmentBranches;

    public Branch(String extension) {
        this.extension = extension;
    }

    public Branch(String extension, Address address) {
        this.extension = extension;
        this.address = address;
    }

    public void addBranchPhone(BranchPhone branchPhone) {
        if (branchPhones == null)
            branchPhones = new ArrayList<>();
        branchPhones.add(branchPhone);
    }

    public void addLoan(Loan loan) {
        if (loans == null)
            loans = new ArrayList<>();
        loans.add(loan);
    }

    public void addEquipmentBranch(EquipmentBranch equipmentBranch) {
        if (equipmentBranches == null)
            equipmentBranches = new ArrayList<>();
        equipmentBranches.add(equipmentBranch);
    }
}
