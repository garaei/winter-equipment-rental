package pl.winterequipmentrental.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import pl.winterequipmentrental.model.person.employee.Position;
import pl.winterequipmentrental.services.position.PositionServiceImpl;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PositionRestControllerTest {

    private final static String BOSS_NAME = "BOSS";
    private final static String BOSS_DESCRIPTION = "Boss description";
    private final static String PHYSICAL_WORKER_NAME = "PHYSICAL_WORKER";
    private final static String PHYSICAL_WORKER_DESCRIPTION = "Physical worker description";
    private final static String SECURITY_GUARD_NAME = "SECURITY_GUARD";
    private final static String SECURITY_GUARD_DESCRIPTION = "Security guard description";
    private final static String NO_NAME = "NO_NAME";
    private final static Set<String> NAMES_ARRAY = new HashSet<>(Arrays.asList(BOSS_NAME, PHYSICAL_WORKER_NAME, SECURITY_GUARD_NAME));
    private final static Position p1 = new Position(BOSS_NAME, BOSS_DESCRIPTION);
    private final static Position p2 = new Position(PHYSICAL_WORKER_NAME, PHYSICAL_WORKER_DESCRIPTION);

    @InjectMocks
    private PositionRestController positionController;

    @Mock
    PositionServiceImpl positionService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        when(positionService.findByName(BOSS_NAME)).thenReturn(Optional.of(p1));
        when(positionService.findAll()).thenReturn(new HashSet<>(Arrays.asList(p1, p2)));
        when(positionService.findByName("")).thenReturn(null);
        when(positionService.findByName(NO_NAME)).thenReturn(null);
        when(positionService.findAllPositionNames()).thenReturn(NAMES_ARRAY);
        when(positionService.save(p1)).thenReturn(null);
    }


    @Test
    public void getPositionByName_ShouldNameEqualsWithPositionResponseName() {
        Position bodyResponse = getPositionByNameAndVerify(BOSS_NAME);
        Assert.assertEquals(BOSS_NAME, bodyResponse != null ? bodyResponse.getName() : null);
    }

    @Test
    public void getPositionByName_ShouldDescriptionEqualsWithPositionResponseDescription() {
        Position bodyResponse = getPositionByNameAndVerify(BOSS_NAME);
        Assert.assertEquals(BOSS_DESCRIPTION, bodyResponse != null ? bodyResponse.getDescription() : null);
    }

    @Test
    public void getPositionByName_ShouldNameNotEqualsWithPositionResponseName() {
        Position bodyResponse = getPositionByNameAndVerify(BOSS_NAME);
        assertNotEquals(bodyResponse != null ? bodyResponse.getName() : null, SECURITY_GUARD_NAME);
    }

    @Test
    public void getPositionByName_ShouldStatusEquals200() {
        ResponseEntity<Position> position = positionController.findPositionByName(BOSS_NAME);
        verify(positionService).findByName(BOSS_NAME);
        assertEquals(200, position.getStatusCodeValue());
    }

    @Test
    public void getPositionByName_ShouldReturnBadRequestResponse() {
        ResponseEntity<Position> positionByName = positionController.findPositionByName("");
        assertEquals(400, positionByName.getStatusCodeValue());
    }

    private Position getPositionByNameAndVerify(String name) {
        ResponseEntity<Position> position = positionController.findPositionByName(name);
        verify(positionService).findByName(name);
        return position.getBody();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void getAllPositions_ShouldReturnTwoPositions() {
        Set<Position> bodyResponse = getAllPositionsAndVerify();

        if (bodyResponse != null) {
            Assert.assertEquals(2, bodyResponse.size());
        }
    }

    @Test
    public void getAllPositions_ShouldReturnMoreThenZeroPositions() {
        Set<Position> bodyResponse = getAllPositionsAndVerify();

        if (bodyResponse != null) {
            assertTrue(bodyResponse.toArray().length > 0);
        }
    }

    @Test
    public void getAllPositions_ShouldReturnLessThenThreePositions() {
        Set<Position> bodyResponse = getAllPositionsAndVerify();

        if (bodyResponse != null) {
            assertTrue(bodyResponse.toArray().length < 3);
        }
    }

    @Test
    public void getAllPositions_ShouldNotReturnFourPositions() {
        Set<Position> bodyResponse = getAllPositionsAndVerify();

        if (bodyResponse != null) {
            assertNotEquals(4, bodyResponse.toArray().length);
        }
    }

    @Test
    public void getAllPositions_ShouldReturnAnyPosition() {
        Set<Position> bodyResponse = getAllPositionsAndVerify();

        if (bodyResponse != null) {
            assertNotEquals(0, bodyResponse.toArray().length);
        }
    }

    @Test
    public void getAllPositions_ShouldStatusEquals200() {
        ResponseEntity<Set<Position>> allPosition = positionController.findAllPositions();
        verify(positionService).findAll();

        assertEquals(200, allPosition.getStatusCodeValue());
    }

    private Set<Position> getAllPositionsAndVerify() {
        ResponseEntity<Set<Position>> allPosition = positionController.findAllPositions();
        verify(positionService).findAll();
        return allPosition.getBody();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void getAllPositionNames_ShouldHasBody() {
        ResponseEntity<Set<String>> allPositionNames = positionController.findAllPositionNames();
        Assert.assertTrue(allPositionNames.hasBody());
    }

    @Test
    public void getAllPositionNames_ShouldReturnThreeNames() {
        Set<String> bodyResponse = getAllPositionNamesAndVerify();
        Assert.assertEquals(3, bodyResponse.size());
    }

    @Test
    public void getAllPositionNames_ShouldAnyMatchNameEqualsToPhysicalWorkerName() {
        Set<String> bodyResponse = getAllPositionNamesAndVerify();
        assertTrue(bodyResponse.stream().anyMatch(PHYSICAL_WORKER_NAME::equals));
    }

    @Test
    public void getAllPositionNames_ShouldAnyMatchNameNotEqualsToPhysicalWorkerDescription() {
        Set<String> bodyResponse = getAllPositionNamesAndVerify();
        assertFalse(bodyResponse.stream().anyMatch(PHYSICAL_WORKER_DESCRIPTION::equals));
    }

    @Test
    public void getAllPositionNames_ShouldNotEqualsTwoPositions() {
        Set<String> bodyResponse = getAllPositionNamesAndVerify();
        assertNotEquals(2, bodyResponse.size());
    }

    @Test
    public void getAllPositionNames_ShouldContainAllNames() {
        Set<String> bodyResponse = getAllPositionNamesAndVerify();
        assertArrayEquals(
                NAMES_ARRAY.toArray(),
                bodyResponse.toArray());
    }

    @Test
    public void getAllPositionNames_ShouldStatusEquals200() {
        ResponseEntity<Set<String>> allPositionNames = positionController.findAllPositionNames();
        verify(positionService).findAllPositionNames();
        assertEquals(200, allPositionNames.getStatusCodeValue());
    }

    private Set<String> getAllPositionNamesAndVerify() {
        ResponseEntity<Set<String>> allPositionNames = positionController.findAllPositionNames();
        verify(positionService).findAllPositionNames();
        return allPositionNames.getBody();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void createPosition_ShouldStatusEquals201() {
        ResponseEntity response = createPosition();
        assertEquals(201, response.getStatusCodeValue());
    }

    private ResponseEntity createPosition() {
        ResponseEntity position = positionController.createPosition(p1);
        verify(positionService).save(p1);
        return position;
    }
}