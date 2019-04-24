package pl.winterequipmentrental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.winterequipmentrental.exception.position.PositionNotFoundException;
import pl.winterequipmentrental.exception.position.PositionNotUpdatedException;
import pl.winterequipmentrental.model.person.employee.Position;
import pl.winterequipmentrental.services.position.PositionService;

import java.net.URI;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/positions")
public class PositionRestController {

    private PositionService positionService;

    @Autowired
    public PositionRestController(PositionService positionService) {
        this.positionService = positionService;
    }

    @PostMapping({"/",""})
    public ResponseEntity createPosition(@RequestBody final Position position) {
        Position result = positionService.save(position);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Position> findPositionById(@PathVariable final long id) {
        Optional<Position> position = positionService.findById(id);
        return ResponseEntity.ok(position.orElseThrow(PositionNotFoundException::new));
    }

    @GetMapping
    public ResponseEntity<Position> findPositionByName(@RequestParam final String name) {
        if (name.isBlank())
            return ResponseEntity.badRequest().build();

        Optional<Position> position = positionService.findByName(name);

        return ResponseEntity.ok(position.orElseThrow(PositionNotFoundException::new));
    }

    @GetMapping("/")
    public ResponseEntity<Set<Position>> findAllPositions() {
        return ResponseEntity.ok(positionService.findAll());
    }

    @GetMapping("/names")
    public ResponseEntity<Set<String>> findAllPositionNames() {
        return ResponseEntity.ok(positionService.findAllPositionNames());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removePositionById(@PathVariable final long id) {
        positionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping({"/",""})
    public ResponseEntity removePositionByName(@RequestParam final String name) {
        positionService.deleteByName(name);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePosition(@RequestBody final Position position,
                                                 @PathVariable final long id) {
        if (!positionService.findById(id).isPresent())
            return ResponseEntity.notFound().build();

        Optional<Position> updatePosition = positionService.update(id, position);

        return ResponseEntity.ok(updatePosition.orElseThrow(PositionNotUpdatedException::new));
    }
}
