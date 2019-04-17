package pl.winterequipmentrental.services;

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

    private PositionRepository positionRepository;

    @Autowired
    public PositionServiceImpl(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

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
    public void update(Position position) {
        if (positionRepository.existsById(position.getId()))
            positionRepository.update(
                    position.getId(),
                    position.getName(),
                    position.getDescription()
            );
    }

    @Override
    @Transactional
    public void updateName(long id, String name) {
        if (positionRepository.existsById(id))
            positionRepository.updateName(id, name);
    }

    @Override
    @Transactional
    public void updateDescription(long id, String description) {
        if (positionRepository.existsById(id))
            positionRepository.updateName(id, description);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        if (positionRepository.existsById(id))
            positionRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteByName(String name) {
        if (positionRepository.existsByName(name))
            positionRepository.deleteByName(name);
    }
}
