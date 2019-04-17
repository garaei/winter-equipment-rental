package pl.winterequipmentrental.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.winterequipmentrental.model.person.employee.Position;
import pl.winterequipmentrental.services.PositionService;

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
    public ResponseEntity<Position> createPosition(@RequestBody Position position) {
        return new ResponseEntity<>(positionService.save(position), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Position> findPositionById(@PathVariable long id) {
        return new ResponseEntity<>(
                positionService.findById(id).orElse(null),
                HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<Position> findPositionByName(@RequestParam String name) {
        return new ResponseEntity<>(
                positionService.findByName(name).orElse(null),
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Set<Position>> findAllPosition() {
        return new ResponseEntity<>(positionService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/names")
    public ResponseEntity<Set<String>> findAllPositionName() {
        return new ResponseEntity<>(positionService.findAllPositionNames(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void removePositionById(@PathVariable long id) {
        positionService.deleteById(id);
    }

    @DeleteMapping("/{name}")
    public void removePositionByName(@PathVariable String name) {
        positionService.deleteByName(name);
    }

    @PutMapping
    public ResponseEntity<Position> updatePosition(@PathVariable Position position) {
        positionService.update(position);
        return new ResponseEntity<>(
                positionService.findById(position.getId()).orElse(null),
                HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Position> updateDescritpionPosition(@PathVariable long id,
                                                              @RequestParam String description) {
        positionService.updateDescription(id, description);
        return new ResponseEntity<>(
                positionService.findById(id).orElse(null),
                HttpStatus.OK);
    }
}
