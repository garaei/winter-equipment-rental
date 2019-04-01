package pl.winterequipmentrental.model.address;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Wojewodztwa")
public class Province implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_wojewodztwo")
    private long id;

    @Setter
    @NaturalId
    @Column(name = "nazwa", nullable = false, unique = true, length = 20)
    private String name;

    @Setter
    @OneToMany(mappedBy = "province")
    private List<Address> addresses;

    public Province(String name) {
        this.name = name;
    }
}
