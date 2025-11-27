package com.kfz.catalog.controller;

import com.kfz.catalog.model.OptionGroup;
import com.kfz.catalog.model.ProductOption;
import com.kfz.catalog.repository.OptionGroupRepository;
import com.kfz.catalog.repository.ProductOptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration test for {@link OptionController}. Spins up the application on a random
 * port and verifies that the endpoint returns grouped options as expected.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OptionControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private OptionGroupRepository optionGroupRepository;

    @Autowired
    private ProductOptionRepository productOptionRepository;

    @BeforeEach
    void setup() {
        productOptionRepository.deleteAll();
        optionGroupRepository.deleteAll();
        OptionGroup group = new OptionGroup();
        group.setId(UUID.randomUUID());
        group.setCode("ENGINE");
        group.setName("Engine");
        group.setDescription("Engine options");
        optionGroupRepository.save(group);
        ProductOption engine = new ProductOption();
        engine.setId(UUID.randomUUID());
        engine.setGroup(group);
        engine.setCode("E1");
        engine.setName("Engine 1");
        engine.setDescription("Desc");
        engine.setPriceDelta(BigDecimal.ONE);
        engine.setDefault(true);
        engine.setActive(true);
        productOptionRepository.save(engine);
    }

    @Test
    void getOptions_returnsGroupedOptions() {
        ResponseEntity<Map> resp = restTemplate.getForEntity("/api/catalog/options", Map.class);
        assertEquals(200, resp.getStatusCode().value());
        Map body = resp.getBody();
        assertNotNull(body);
        assertTrue(body.containsKey("ENGINE"));
        List engineList = (List) body.get("ENGINE");
        assertEquals(1, engineList.size());
    }
}