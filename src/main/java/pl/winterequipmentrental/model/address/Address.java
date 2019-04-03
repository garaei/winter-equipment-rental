package pl.winterequipmentrental.model.address;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.winterequipmentrental.model.branch.Branch;
import pl.winterequipmentrental.model.person.client.Customer;
import pl.winterequipmentrental.model.person.employee.Employee;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Adresy")
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_adres")
    private long id;

    @Setter
    @Column(name = "miasto", nullable = false, length = 40)
    private String city;

    @Setter
    @Column(name = "miejscowosc", nullable = false, length = 40)
    private String locality;

    @Setter
    @Column(name = "ulica", length = 30)
    private String street;

    @Setter
    @Column(name = "kod_pocztowy", nullable = false, length = 5)
    private String zipCode;

    @Setter
    @Column(name = "numer_budynku", nullable = false, length = 8)
    private String buildingNumber;

    @Setter
    @Column(name = "numer_lokalu", length = 8)
    private String apartmentNumber;

    @Setter
    @OneToMany(mappedBy = "address")
    private List<Employee> employees;

    @Setter
    @Column(name = "id_province", insertable = false, updatable = false, nullable = false)
    private String provinceId;

    @Setter
    @ManyToOne
    @JoinColumn(name = "id_province", referencedColumnName = "name")
    private Province province;

    @Setter
    @OneToOne(mappedBy = "address")
    private Branch branch;

    @Setter
    @OneToMany(mappedBy = "address")
    private List<Customer> customers;

    public Address(String city, String locality, String street, String zipCode, String buildingNumber, String apartmentNumber) {
        this.city = city;
        this.locality = locality;
        this.street = street;
        this.zipCode = zipCode;
        this.buildingNumber = buildingNumber;
        this.apartmentNumber = apartmentNumber;
    }

    public void addEmployee(Employee employee) {
        if (employees == null)
            employees = new ArrayList<>();
        employees.add(employee);
    }

    public void addCustomer(Customer customer) {
        if (customers == null)
            customers = new ArrayList<>();
        customers.add(customer);
    }
}
