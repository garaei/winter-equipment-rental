package pl.winterequipmentrental.model.address;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;

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
    @Column(name = "nazwa", nullable = false, unique = true, length = 20)
    private String name;

    public Province(String name) {
        this.name = name;
    }
}
