package pl.winterequipmentrental.model.person.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;
import pl.winterequipmentrental.model.account.User;
import pl.winterequipmentrental.model.address.EmployeeAddress;
import pl.winterequipmentrental.model.person.Person;
import pl.winterequipmentrental.model.phone.EmployeePhone;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Table(name = "Employees")
@Builder
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_employee", insertable =  false, updatable = false, nullable = false, unique = true)
    private long id;

    @Setter
    @Column(name = "pesel", nullable = false, unique = true , length = 11)
    private String pesel;

    @Setter
    @Column(name = "email", nullable = false, unique = true, length = 40)
    private String email;

    @Setter
    @Column(name = "hired", nullable = false, columnDefinition = "BIT DEFAULT 0")
    private boolean hired;

    @Setter
    @Embedded
    private Person person;

    @Setter
    @Column(name = "recently_date_employment")
    @Temporal(TemporalType.DATE)
    private Date recentlyDateEmployment;

    @Setter
    @OneToOne(mappedBy = "employee", cascade = CascadeType.REFRESH)
    @JsonIgnoreProperties({"password", "createDate", "updateDate", "roles", "employeeId", "employee"})
    private User user;

    @Setter
    @Column(name = "contract_number", unique = true, length = 12)
    private String contractNumber;

    @Setter
    @OneToOne(mappedBy = "employee")
    @JsonIgnore
    private Contract contract;

    @OneToMany(mappedBy = "employer")
    @JsonIgnore
    private Set<Contract> employerContracts;

    @Setter
    @Column(name = "id_position", insertable =  false, updatable = false, nullable = false)
    private long positionId;

    @Setter
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_position")
    @JsonIgnore
    private Position position;

    @Setter
    @Column(name = "id_address", insertable = false, updatable = false)
    private long addressId;

    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_address")
    @JsonIgnoreProperties({"employees", "provinceId"})
    private EmployeeAddress address;

    @Setter
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"id", "employeeId", "employee"})
    private Set<EmployeePhone> employeePhones;

    @Tolerate
    public Employee() {
    }
}