package pl.winterequipmentrental.services.position;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.winterequipmentrental.model.person.employee.Position;
import pl.winterequipmentrental.repository.PositionRepository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionRepository positionRepository;

    @Override
    @Transactional
    public Position save(Position position) {
        return positionRepository.save(position);
    }

    @Override
    public Optional<Position> findById(long id) {
        return positionRepository.findById(id);
    }

    @Override
    public Optional<Position> findByName(String name) {
        return positionRepository.findByName(name);
    }

    @Override
    public Set<Position> findAll() {
        return positionRepository.findAll();
    }

    @Override
    public Set<String> findAllPositionNames() {
        return positionRepository.findAll()
                .stream()
                .map(Position::getName)
                .collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public Optional<Position> update(long id, Position position) {
         return positionRepository.findById(id)
                .map(p -> {
                    p.setName(position.getName());
                    p.setDescription(position.getDescription());
                    return positionRepository.save(p);
                });
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        positionRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteByName(String name) {
        positionRepository.deleteByName(name);
    }
}
