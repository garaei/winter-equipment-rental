package pl.winterequipmentrental.model.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.UpdateTimestamp;
import pl.winterequipmentrental.model.person.employee.Employee;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user", nullable = false, unique = true, insertable = false, updatable = false)
    private long id;

    /**
     * Unique field which can not be null and can have max 20 chars.
     */
    @Setter
    @NaturalId
    @Column(name = "login", nullable = false, unique = true, length = 20)
    private String login;

    /**
     * Password in coded form. Can have max 50 chars.
     */
    @Setter
    @Column(name = "password", nullable = false, length = 50)
    private String password;

    /**
     * field that describes whether the user is active or inactive:
     * true - active
     * false - inactive
     */
    @Setter
    @Column(name = "active", nullable = false)
    private boolean active;

    /**
     * Date of making the user.
     */
    @Column(name = "create_date", nullable = false)
    @CreationTimestamp
    private Date createDate;

    /**
     * Date of updating the user.
     */
    @Setter
    @Column(name = "update_date", nullable = false)
    @UpdateTimestamp
    private Date updateDate;

    /**
     * ManyToMany relationship with Role entity.
     * A set type list that contains user roles.
     * Ignores the description, users and id fields from the Role class when get JSON.
     */
    @Setter
    @ManyToMany
    @JoinTable(name = "Users_roles",
            joinColumns = {@JoinColumn(name = "id_user")},
            inverseJoinColumns = {@JoinColumn(name = "id_role")})
    @JsonIgnoreProperties({"description", "users", "id"})
    private Set<Role> roles;

    /**
     * Id employee which can not be insertable and updatable.
     */
    @Setter
    @Column(name = "ID_EMPLOYEE", insertable = false, updatable = false)
    private long employeeId;

    /**
     * OneToOne relationship with Employee entity
     * Ignores the position, address, employeePhones, loans, employerContracts,
     * contract, user fields from the Employee class when get JSON.
     */
    @Setter
    @OneToOne
    @JoinColumn(name = "ID_EMPLOYEE")
    @JsonIgnoreProperties({"position", "address", "employeePhones", "loans", "employerContracts", "contract", "user"})
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

    /**
     * Adding a single role to the role list
     * @param role - role which will be add to roles list
     */
    public void addRole(Role role) {
        if (roles == null)
            roles = new HashSet<>();
        roles.add(role);
    }
}
