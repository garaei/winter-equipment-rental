package pl.winterequipmentrental.model.address;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.winterequipmentrental.model.branch.Branch;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Branch_address")
public class BranchAddress extends Address {

    @OneToOne(mappedBy = "address")
    @JsonIgnore
    private Branch branch;

    public BranchAddress(String city,
                         String locality,
                         String zipCode,
                         String buildingNumber,
                         Province province) {
        super(city, locality, zipCode, buildingNumber, province);
    }

    public BranchAddress(String city,
                         String locality,
                         String street,
                         String zipCode,
                         String buildingNumber,
                         Province province) {
        super(city, locality, street, zipCode, buildingNumber, province);
    }

    public BranchAddress(String city,
                         String locality,
                         String street,
                         String zipCode,
                         String buildingNumber,
                         String apartmentNumber,
                         Province province) {
        super(city, locality, street, zipCode, buildingNumber, apartmentNumber, province);
    }
}
