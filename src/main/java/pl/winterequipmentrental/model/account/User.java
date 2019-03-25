package pl.winterequipmentrental.model.account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Setter
    @Column(name = "login", nullable = false, unique = true, length = 20)
    private String login;

    @Setter
    @Column(name = "haslo", nullable = false, length = 50)
    private String password;

    @Setter
    @Column(name = "aktywnosc", nullable = false, length = 1)
    private short active;

    public User(String login, String password, short active) {
        this.login = login;
        this.password = password;
        this.active = active;
    }
}
