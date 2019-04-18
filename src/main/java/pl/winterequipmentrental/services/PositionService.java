package pl.winterequipmentrental.services;

import pl.winterequipmentrental.model.person.employee.Position;

import java.util.Optional;
import java.util.Set;

public interface PositionService {

    Position save(Position position);

    Optional<Position> findById(long id);

    Optional<Position> findByName(String name);

    Set<Position> findAll();

    Set<String> findAllPositionNames();

    Optional<Position> update(long id, Position position);

    void deleteById(long id);

    void deleteByName(String name);

}
