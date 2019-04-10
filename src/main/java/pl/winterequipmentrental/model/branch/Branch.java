package pl.winterequipmentrental.model.branch;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import pl.winterequipmentrental.model.address.BranchAddress;
import pl.winterequipmentrental.model.equipment.EquipmentBranch;
import pl.winterequipmentrental.model.loan.Loan;
import pl.winterequipmentrental.model.phone.BranchPhone;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Table(name = "Branch")
@Builder
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
    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
    private Set<BranchPhone> branchPhones;

    @Setter
    @Column(name = "id_address", insertable = false, updatable = false)
    private long addressId;

    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_address")
    @JsonIgnoreProperties({"branches", "provinceId"})
    private BranchAddress address;

    @OneToMany(mappedBy = "branch")
    @JsonIgnore
    private Set<Loan> loans;

    @Setter
    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<EquipmentBranch> equipmentBranches;
}
