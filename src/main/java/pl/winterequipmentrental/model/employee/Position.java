package pl.winterequipmentrental.model.employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Stanowiska")
@AllArgsConstructor
public class Position implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Setter
    @Column(name = "nazwa", nullable = false, length = 50, unique = true)
    private String name;

    @Setter
    @Lob
    @Column(name = "opis")
    private String description;

    public Position(String name) {
        this.name = name;
    }

    public Position(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
