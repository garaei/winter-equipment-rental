package pl.winterequipmentrental.model.person.client;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Company implements Serializable {
    @Setter
    @Column(name = "company_name", length = 100)
    private String name;

    @Setter
    @Column(name = "nip", unique = true, length = 10)
    private String nip;

    @Setter
    @Column(name = "regon", unique = true, length = 14)
    private String regon;

    @Setter
    @Column(name = "company_phone", length = 9)
    private String companyPhone;

    public Company(String name, String nip) {
        this.name = name;
        this.nip = nip;
    }

    public Company(String name, String nip, String regon) {
        this.name = name;
        this.nip = nip;
        this.regon = regon;
    }
}
