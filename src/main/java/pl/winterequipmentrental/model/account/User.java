package pl.winterequipmentrental.model.account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.UpdateTimestamp;
import pl.winterequipmentrental.model.person.employee.Employee;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idUser", nullable = false, unique = true)
    private long id;

    @Setter
    @NaturalId
    @Column(name = "login", nullable = false, unique = true, length = 20)
    private String login;

    @Setter
    @Column(name = "password", nullable = false, length = 50)
    private String password;

    @Setter
    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "create_date", nullable = false)
    @CreationTimestamp
    private LocalDateTime createDate;

    @Setter
    @Column(name = "update_date", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateDate;

    @Setter
    @ManyToMany(mappedBy = "users")
    private Set<Role> roles;

    @Setter
    @Column(name = "id_employee", insertable = false, updatable = false, nullable = false, unique = true, length = 40)
    private String email;

    @Setter
    @OneToOne
    @JoinColumn(name = "id_employee", referencedColumnName = "email", nullable = false, unique = true)
    private Employee employee;

    public User(String login, String password, boolean active) {
        this.login = login;
        this.password = password;
        this.active = active;
    }

    public User(String login, String password, boolean active, Employee employee) {
        this.login = login;
        this.password = password;
        this.active = active;
        this.employee = employee;
    }

    public void addRole(Role role) {
        if (roles == null)
            roles = new HashSet<>();
        roles.add(role);
    }
}
