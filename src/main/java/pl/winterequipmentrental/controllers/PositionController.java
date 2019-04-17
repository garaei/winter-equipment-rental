package pl.winterequipmentrental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.winterequipmentrental.model.person.employee.Position;
import pl.winterequipmentrental.services.PositionService;

import java.net.URI;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/positions")
public class PositionController {

    private PositionService positionService;

    @Autowired
    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @PostMapping
    public ResponseEntity<Position> createPosition(@RequestBody final Position position) {
        Position result = positionService.save(position);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Position> findPositionById(@PathVariable final long id) {
        Optional<Position> position = positionService.findById(id);
        if (position.isPresent())
            return ResponseEntity.ok(position.get());
        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<Position> findPositionByName(@RequestParam final String name) {
        Optional<Position> position = positionService.findByName(name);
        if (position.isPresent())
            return ResponseEntity.ok(position.get());
        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<Set<Position>> findAllPosition() {
        return ResponseEntity.ok(positionService.findAll());
    }

    @GetMapping("/names")
    public ResponseEntity<Set<String>> findAllPositionName() {
        return ResponseEntity.ok(positionService.findAllPositionNames());
    }

    @DeleteMapping("/{id}")
    public void removePositionById(@PathVariable final long id) {
        positionService.deleteById(id);
    }

    @DeleteMapping("/{name}")
    public void removePositionByName(@PathVariable final String name) {
        positionService.deleteByName(name);
    }

    @PutMapping
    public ResponseEntity<Position> updatePosition(@PathVariable final Position position) {
        positionService.update(position);
        return ResponseEntity.ok(positionService.findById(position.getId()).orElse(null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Position> updateDescritpionPosition(@PathVariable final long id,
                                                              @RequestParam final String description) {
        positionService.updateDescription(id, description);
        return ResponseEntity.ok(positionService.findById(id).orElse(null));
    }
}
