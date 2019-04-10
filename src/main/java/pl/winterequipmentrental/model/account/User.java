package pl.winterequipmentrental.model.account;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.UpdateTimestamp;
import pl.winterequipmentrental.model.loan.Loan;
import pl.winterequipmentrental.model.person.employee.Employee;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Getter
@Table(name = "Users")
@Builder
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user", nullable = false, unique = true, insertable = false, updatable = false)
    private long id;

    @Setter
    @NaturalId
    @Column(name = "login", nullable = false, unique = true, length = 20)
    private String login;

    @Setter
    @Column(name = "password", nullable = false, length = 50)
    @JsonIgnore
    private String password;

    /**
     * field that describes whether the user is active or inactive:
     * true - active
     * false - inactive
     */
    @Setter
    @Column(name = "active", nullable = false)
    private boolean active;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "create_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createDate;

    @Setter
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "update_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updateDate;

    @Setter
    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "Users_roles",
            joinColumns = {@JoinColumn(name = "id_user")},
            inverseJoinColumns = {@JoinColumn(name = "id_role")})
    @JsonIgnoreProperties({"description", "users", "id"})
    private Set<Role> roles;

    @Setter
    @Column(name = "id_employee", insertable = false, updatable = false)
    private long employeeId;

    @Setter
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_employee")
    @JsonIgnore
    private Employee employee;

    /**
     * Loans that this user created
     */
    @OneToMany(mappedBy = "createdByUser")
    @JsonIgnore
    private Set<Loan> loansCreated;

    /**
     * Loans which this user accepted and set as completed
     */
    @OneToMany(mappedBy = "completedByUser")
    @JsonIgnore
    private Set<Loan> completedLoans;

    public void addRole(Role role) {
        if (roles == null)
            roles = new HashSet<>();
        roles.add(role);
    }
}
