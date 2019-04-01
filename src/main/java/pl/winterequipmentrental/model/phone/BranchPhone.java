package pl.winterequipmentrental.model.phone;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.winterequipmentrental.model.branch.Branch;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Telefony_filii")
public class BranchPhone extends Telephone {
    @Setter
    @Column(name = "id_branch", insertable = false, updatable = false, nullable = false)
    private String extensionBranch;

    @Setter
    @ManyToOne
    @JoinColumn(name = "id_branch", referencedColumnName = "nr_wewnetrzny")
    private Branch branch;

    @Setter
    @Column(name = "id_type_phone", insertable = false, updatable = false)
    private String typePhoneId;

    @Setter
    @ManyToOne
    @JoinColumn(name = "id_type_phone", referencedColumnName = "nazwa")
    private TypePhone typePhone;

    public BranchPhone(String numberPhone, String extensionBranch) {
        super(numberPhone);
        this.extensionBranch = extensionBranch;
    }
}

//TODO Zamiana relacji ManyToOne z TypePhone na enum lub interfejs