package pl.winterequipmentrental.model.account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Role")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_rola")
    private long id;

    @Setter
    @Column(name = "nazwa", nullable = false, unique = true, length = 30)
    private String name;

    @Setter
    @Column(name = "opis")
    private String description;

    @Setter
    @ManyToMany
    @JoinTable(name = "Users_roles",
        joinColumns = {@JoinColumn(name = "role_id")},
        inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<User> users;

    public Role(String name) {
        this.name = name;
    }

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }
}