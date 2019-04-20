package pl.winterequipmentrental.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.winterequipmentrental.AbstractTest;
import pl.winterequipmentrental.model.person.employee.Position;
import pl.winterequipmentrental.repository.PositionRepository;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class PositionIntegrationTest extends AbstractTest {

    private final static String BOSS_NAME = "BOSS";
    private final static String BOSS_DESCRIPTION = "Boss description";
    private final static String PHYSICAL_WORKER_NAME = "PHYSICAL_WORKER";
    private final static String PHYSICAL_WORKER_DESCRIPTION = "Physical worker description";
    private final static String SECURITY_GUARD_NAME = "SECURITY_GUARD";
    private final static String SECURITY_GUARD_DESCRIPTION = "Security guard description";
    private final static String NEW_POSITION_NAME = "NEW_POSITION";
    private final static String NEW_POSITION_DESCRIPTION = "New position description";
    private final static String UPDATE_POSITION_NAME = "UPDATE_POSITION";
    private final static String UPDATE_POSITION_DESCRIPTION = "Update position description";

    @Autowired
    PositionRepository positionRepository;

    @Override
    @Before
    public void setUp() {
        super.setUp();
//        positionRepository.save(new Position(BOSS_NAME, BOSS_DESCRIPTION));
//        positionRepository.save(new Position(PHYSICAL_WORKER_NAME, PHYSICAL_WORKER_DESCRIPTION));
//        positionRepository.save(new Position(SECURITY_GUARD_NAME, SECURITY_GUARD_DESCRIPTION));
    }

    void insert() {
        positionRepository.save(new Position(BOSS_NAME, BOSS_DESCRIPTION));
        positionRepository.save(new Position(PHYSICAL_WORKER_NAME, PHYSICAL_WORKER_DESCRIPTION));
        positionRepository.save(new Position(SECURITY_GUARD_NAME, SECURITY_GUARD_DESCRIPTION));
    }

    void delete() {
        positionRepository.deleteByName(BOSS_NAME);
        positionRepository.deleteByName(PHYSICAL_WORKER_NAME);
        positionRepository.deleteByName(SECURITY_GUARD_NAME);
    }

    @Test
    @Transactional
    public void getPositionsList() throws Exception {
        insert();
        String uri = "/positions/";

        MvcResult mvcResult = mvc.perform(get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        Position[] positionList = super.mapFromJson(content, Position[].class);

        assertEquals(3, positionList.length);
        assertTrue(positionList.length > 0);
    }

    @Test
    @Transactional
    public void getPositionById() throws Exception {
        insert();
        String uri = "/positions/1";
        MvcResult mvcResult = mvc.perform(get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Position position = super.mapFromJson(content, Position.class);

        assertEquals(BOSS_NAME, position.getName());
        assertEquals(BOSS_DESCRIPTION, position.getDescription());
        assertNotEquals(SECURITY_GUARD_NAME, position.getName());
    }

//    @Test
    @Transactional
    public void getPositionByName() throws Exception {
        insert();
        String uri = "/positions?name=" + PHYSICAL_WORKER_NAME;
        MvcResult mvcResult = mvc.perform(get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        Position position = super.mapFromJson(content, Position.class);

        assertNotEquals(BOSS_NAME, position.getName());
        assertEquals(PHYSICAL_WORKER_NAME, position.getName());
    }

    @Test
    @Transactional
    public void getAllPositionNames() throws Exception {
        insert();
        String uri = "/positions/names";
        MvcResult mvcResult = mvc.perform(get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();

        String content = mvcResult.getResponse().getContentAsString();
        HashSet positionNames = super.mapFromJson(content, HashSet.class);

        assertEquals(200, status);
        assertTrue(positionNames.stream().anyMatch(PHYSICAL_WORKER_NAME::equals));
        assertEquals(3, positionNames.size());
        assertFalse(positionNames.stream().anyMatch(PHYSICAL_WORKER_DESCRIPTION::equals));
    }

//    @Transactional
//    @Test
//    public void createPosition() throws Exception {
//        insert();
//        String uri = "/positions";
//
//        Position postPosition = new Position(NEW_POSITION_NAME, NEW_POSITION_DESCRIPTION);
//
//        String inputJson = super.mapToJson(postPosition);
//
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .contentType(inputJson)).andReturn();
//
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(201, status);
//
////        String location = mvcResult.getResponse().getHeader("Location");
////        assertEquals("123", location);
//    }

    @Test
    @Transactional
    public void updatePosition() throws Exception {
        insert();
        String uri = "/positions/2";
        Position position = new Position(UPDATE_POSITION_NAME, UPDATE_POSITION_DESCRIPTION);

        String inputJson = super.mapToJson(position);


        String x = "{\"id\":2,\"name\":\"" + UPDATE_POSITION_NAME + "\",\"description\":\"" + UPDATE_POSITION_DESCRIPTION + "\"}";

        mvc
                .perform(put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
                .andExpect(status().isOk())
                .andExpect(content().string(x));
    }

    @Test
    @Transactional
    public void notUpdatePosition_BadId() throws Exception {
        insert();
        String uri = "/positions/99999999999999";
        Position position = new Position(UPDATE_POSITION_NAME, UPDATE_POSITION_DESCRIPTION);

        String inputJson = super.mapToJson(position);

        mvc
                .perform(
                        put(uri)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(inputJson))
                .andExpect(status().isNotFound());

    }

//    @Test
//    public void deletePositionById() throws Exception {
//        String uri = "/positions/2";
//
//        mvc
//                .perform(delete(uri))
//                .andExpect(status().isNoContent());
//    }
//
//    @Test
//    public void deletePositionByName() throws Exception {
//        String uri = "/positions?name=" + PHYSICAL_WORKER_NAME;
//
//        mvc
//                .perform(delete(uri))
//                .andExpect(status().isNoContent());
//    }
//
//    @Test
//    public void shouldReturnPositionWithId2() throws Exception {
//
//        String expected = "{\"id\":2,\"name\":\"" + PHYSICAL_WORKER_NAME + "\",\"description\":\"" + PHYSICAL_WORKER_DESCRIPTION + "\"}";
//        mvc
//                .perform(get("/positions/2"))
//                .andExpect(status().isOk())
//                .andExpect(content().string(expected));
//    }

}