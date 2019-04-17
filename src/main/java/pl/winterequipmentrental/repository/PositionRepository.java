package pl.winterequipmentrental.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.winterequipmentrental.model.person.employee.Position;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

@Repository
public interface PositionRepository extends CrudRepository<Position, Long> {

    Set<Position> findAll();

    Optional<Position> findByName(String name);

    @Modifying
    @Query(
            value = "UPDATE positions SET name = :name, description = :description WHERE id_position = :id",
            nativeQuery = true)
    void update(@Param("id") long id,
                @Param("name") String name,
                @Param("description") String description);

    @Modifying
    @Query(
            value = "UPDATE positions SET name = :name WHERE id_position = :id",
            nativeQuery = true
    )
    void updateName(@Param("id") long id,
                    @Param("name") String name);

    @Modifying
    @Query(
            value = "UPDATE positions SET description = :description WHERE id_position = :id",
            nativeQuery = true
    )
    void updateDescription(@Param("id") long id,
                           @Param("description") String description);

    void deleteByName(String name);

    boolean existsByName(String name);
}
