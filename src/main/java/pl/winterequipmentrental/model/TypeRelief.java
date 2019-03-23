package pl.winterequipmentrental.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Rodzaje_ulg")
public class TypeRelief {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_ulga")
    private long id;

    @Setter
    @Column(name = "nazwa", nullable = false, unique = true, length = 80)
    private String name;

    @Setter
    @Lob
    @Column(name = "opis")
    private String description;

    public TypeRelief(String name) {
        this.name = name;
    }

    public TypeRelief(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
