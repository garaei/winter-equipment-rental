package pl.winterequipmentrental.model.phone;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.winterequipmentrental.model.branch.Branch;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "BranchPhones")
public class BranchPhone extends Telephone {
    @Setter
    @Column(name = "id_branch_phone", insertable = false, updatable = false, nullable = false)
    private String extensionBranch;

    @Setter
    @ManyToOne
    @JoinColumn(name = "id_branch_phone", referencedColumnName = "extension", nullable = false)
    @JsonIgnore
    private Branch branch;

    @Setter
    @Column(name = "type_phone")
    @Enumerated(value = EnumType.STRING)
    private TypePhone typePhone;

    public BranchPhone(String numberPhone, Branch branch, TypePhone typePhone) {
        super(numberPhone);
        this.branch = branch;
        this.typePhone = typePhone;
    }
}