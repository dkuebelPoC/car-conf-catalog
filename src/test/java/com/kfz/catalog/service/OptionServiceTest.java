package com.kfz.catalog.service;

import com.kfz.catalog.model.OptionGroup;
import com.kfz.catalog.model.ProductOption;
import com.kfz.catalog.repository.OptionGroupRepository;
import com.kfz.catalog.repository.ProductOptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for {@link OptionService} that verifies grouping logic. The service
 * should group options by their associated {@link OptionGroup} code, falling back
 * to the "UNGROUPED" bucket when no group is assigned.
 */
@SpringBootTest
public class OptionServiceTest {
    @Autowired
    private OptionService optionService;

    @Autowired
    private ProductOptionRepository productOptionRepository;

    @Autowired
    private OptionGroupRepository optionGroupRepository;

    @BeforeEach
    public void setup() {
        productOptionRepository.deleteAll();
        optionGroupRepository.deleteAll();
    }

    @Test
    public void getGroupedOptions_groupsOptionsIncludingUngrouped() {
        // Prepare a group
        OptionGroup group = new OptionGroup();
        group.setId(UUID.randomUUID());
        group.setCode("ENGINE");
        group.setName("Engine");
        group.setDescription("Engine options");
        optionGroupRepository.save(group);

        // Option with group
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

        // Option without group
        ProductOption ung = new ProductOption();
        ung.setId(UUID.randomUUID());
        ung.setGroup(null);
        ung.setCode("UNK");
        ung.setName("Unknown");
        ung.setDescription("Unknown");
        ung.setPriceDelta(BigDecimal.ZERO);
        ung.setDefault(false);
        ung.setActive(true);
        productOptionRepository.save(ung);

        Map<String, List<ProductOption>> grouped = optionService.getGroupedOptions();
        assertTrue(grouped.containsKey("ENGINE"), "Should contain ENGINE group");
        assertTrue(grouped.containsKey("UNGROUPED"), "Should contain UNGROUPED group");
        assertEquals(1, grouped.get("ENGINE").size());
        assertEquals(1, grouped.get("UNGROUPED").size());
    }
}