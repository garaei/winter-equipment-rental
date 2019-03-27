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
@Table(name = "Uzytkownicy")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user")
    private long id;

    @Setter
    @NaturalId
    @Column(name = "login", nullable = false, unique = true, length = 20)
    private String login;

    @Setter
    @Column(name = "haslo", nullable = false, length = 50)
    private String password;

    @Setter
    @Column(name = "aktywnosc", nullable = false)
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
    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public User(String login, String password, boolean active) {
        this.login = login;
        this.password = password;
        this.active = active;
    }

    public void setRoleToRoles(Role role) {
        if (roles == null)
            roles = new HashSet<>();
        roles.add(role);
    }
}
