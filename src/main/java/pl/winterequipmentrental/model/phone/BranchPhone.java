package pl.winterequipmentrental.model.phone;

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
    @Column(name = "idBranchPhone", insertable = false, updatable = false, nullable = false)
    private String extensionBranch;

    @Setter
    @ManyToOne
    @JoinColumn(name = "id_branch", referencedColumnName = "extension", nullable = false)
    private Branch branch;

    @Setter
    @Column(name = "id_type_phone", insertable = false, updatable = false, nullable = false)
    private String typePhoneId;

    @Setter
    @ManyToOne
    @JoinColumn(name = "id_type_phone", referencedColumnName = "name", nullable = false)
    private TypePhone typePhone;

    public BranchPhone(String numberPhone, Branch branch, TypePhone typePhone) {
        super(numberPhone);
        this.branch = branch;
        this.typePhone = typePhone;
    }
}

//TODO Zamiana relacji ManyToOne z TypePhone na enum lub interfejs