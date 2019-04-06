package pl.winterequipmentrental.model.account;

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
@Table(name = "Role")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idRole", unique = true, nullable = false)
    private long id;

    /**
     * Should be written in capital letters. Unique and can not be null.
     * Can have max 30 chars.
     */
    @Setter
    @Column(name = "name", nullable = false, unique = true, length = 30)
    private String name;

    /**
     * Describe of the role
     */
    @Setter
    @Column(name = "description")
    private String description;

    /**
     * ManyToMany relationship with User entity.
     * A set type list that contains role users.
     */
    @Setter
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role(String name) {
        this.name = name;
    }

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Adding a signle user to the user list
     * @param user - user which will be add to users list
     */
    public void addUser(User user) {
        if (users == null)
            users = new HashSet<>();
        users.add(user);
    }
}
