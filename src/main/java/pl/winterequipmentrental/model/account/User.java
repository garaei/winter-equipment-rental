package pl.winterequipmentrental.model.account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Uzytkownicy")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user")
    private long id;

    @Column(name = "login", nullable = false, unique = true, length = 20)
    private String login;

    @Column(name = "haslo", nullable = false, length = 50)
    private String password;

}
