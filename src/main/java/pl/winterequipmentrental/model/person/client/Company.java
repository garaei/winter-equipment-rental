package pl.winterequipmentrental.model.person.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
