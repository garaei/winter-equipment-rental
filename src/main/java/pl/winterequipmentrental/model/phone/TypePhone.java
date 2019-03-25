package pl.winterequipmentrental.model.phone;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Rodzaje_telefonow")
public class TypePhone implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_rodzaj_telefonu")
    private long id;

    @Column(name = "nazwa", nullable = false, unique = true, length = 45)
    private String name;
}
