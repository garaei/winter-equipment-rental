package pl.winterequipmentrental.model.address;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

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
    private short zipCode;

    @Setter
    @Column(name = "numer_budynku", nullable = false, length = 8)
    private String buildingNumber;

    @Setter
    @Column(name = "numer_lokalu", length = 8)
    private String apartmentNumber;

    public Address(String city, String locality, String street, short zipCode, String buildingNumber, String apartmentNumber) {
        this.city = city;
        this.locality = locality;
        this.street = street;
        this.zipCode = zipCode;
        this.buildingNumber = buildingNumber;
        this.apartmentNumber = apartmentNumber;
    }
}
