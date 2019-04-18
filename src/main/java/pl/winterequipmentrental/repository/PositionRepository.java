package pl.winterequipmentrental.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.winterequipmentrental.model.person.employee.Position;

import java.util.Optional;
import java.util.Set;

@Repository
public interface PositionRepository extends CrudRepository<Position, Long> {

    Set<Position> findAll();

    Optional<Position> findByName(String name);

    void deleteByName(String name);
}
