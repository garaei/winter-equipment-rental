package pl.winterequipmentrental.model.account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Role_uzytkownikow")
public class UsersRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_rola_uzytkownika")
    private long id;
}
