package com.kfz.catalog.repository;

import com.kfz.catalog.model.OptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Repository for {@link OptionGroup} entities. Although not used directly in the production
 * code yet, this repository is leveraged in tests to set up the database and could be used
 * in future enhancements of the catalog service.
 */
public interface OptionGroupRepository extends JpaRepository<OptionGroup, UUID> {
}