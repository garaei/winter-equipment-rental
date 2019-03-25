package pl.winterequipmentrental.model.phone;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Telefony_filii")
public class BranchPhone extends Telephon {
    @Setter
    @Column(name = "nr_wewnetrzny_filii", nullable = false, length = 20)
    private String extensionBranch;

    public BranchPhone(String numberPhone, String extensionBranch) {
        super(numberPhone);
        this.extensionBranch = extensionBranch;
    }
}
