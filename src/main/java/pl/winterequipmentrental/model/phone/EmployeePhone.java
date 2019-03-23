package pl.winterequipmentrental.model.phone;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.winterequipmentrental.model.phone.Telephon;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Telefony_pracownikow")
public class EmployeePhone extends Telephon {
}
