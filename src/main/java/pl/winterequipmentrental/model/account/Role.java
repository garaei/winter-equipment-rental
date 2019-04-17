package pl.winterequipmentrental.model.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Roles")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_role", unique = true, nullable = false, insertable = false, updatable = false)
    private long id;

    @Setter
    @Column(name = "name", nullable = false, unique = true, length = 30)
    private String name;

    @ManyToMany(mappedBy = "roles", cascade = CascadeType.REFRESH)
    @JsonIgnore
    private Set<User> users;

    public Role(String name) {
        this.name = name;
    }
}
