package pl.winterequipmentrental.model.branch;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

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
    @Column(name = "nr_wewnetrzny", nullable = false, unique = true, length = 20)
    private String extension;

    public Branch(String extension) {
        this.extension = extension;
    }
}
