package pl.winterequipmentrental.model.address;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@MappedSuperclass
public abstract class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_address", nullable = false, unique = true, insertable = false, updatable = false)
    private long id;

    @Setter
    @Column(name = "city", nullable = false, length = 40)
    private String city;

    @Setter
    @Column(name = "locality", nullable = false, length = 40)
    private String locality;

    @Setter
    @Column(name = "street", length = 30)
    private String street;

    @Setter
    @Column(name = "zip_code", nullable = false, length = 5)
    private String zipCode;

    @Setter
    @Column(name = "building_number", nullable = false, length = 8)
    private String buildingNumber;

    @Setter
    @Column(name = "numer_lokalu", length = 8)
    private String apartmentNumber;

    @Setter
    @Column(name = "id_province", insertable = false, updatable = false, nullable = false)
    private String provinceId;

    @Setter
    @ManyToOne
    @JoinColumn(name = "id_province", referencedColumnName = "name")
    @JsonIgnoreProperties("id")
    private Province province;

    public Address(String city,
                   String locality,
                   String zipCode,
                   String buildingNumber,
                   Province province) {
        this.city = city;
        this.locality = locality;
        this.zipCode = zipCode;
        this.buildingNumber = buildingNumber;
        this.province = province;
    }

    public Address(String city,
                   String locality,
                   String street,
                   String zipCode,
                   String buildingNumber,
                   Province province) {
        this.city = city;
        this.locality = locality;
        this.street = street;
        this.zipCode = zipCode;
        this.buildingNumber = buildingNumber;
        this.province = province;
    }

    public Address(String city,
                   String locality,
                   String street,
                   String zipCode,
                   String buildingNumber,
                   String apartmentNumber,
                   Province province) {
        this.city = city;
        this.locality = locality;
        this.street = street;
        this.zipCode = zipCode;
        this.buildingNumber = buildingNumber;
        this.apartmentNumber = apartmentNumber;
        this.province = province;
    }
}
