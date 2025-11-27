package com.kfz.catalog.service;

import com.kfz.catalog.model.ProductOption;
import com.kfz.catalog.repository.ProductOptionRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OptionService {
    private final ProductOptionRepository repository;

    public OptionService(ProductOptionRepository repository) {
        this.repository = repository;
    }

    public Map<String, List<ProductOption>> getGroupedOptions() {
        List<ProductOption> options = repository.findAll();
        // Group by the option group code. If no group is present, collect under "UNGROUPED".
        return options.stream()
                .collect(Collectors.groupingBy(o -> {
                    if (o.getGroup() == null || o.getGroup().getCode() == null) {
                        return "UNGROUPED";
                    }
                    return o.getGroup().getCode();
                }));
    }

    public Optional<ProductOption> findById(UUID id) {
        return repository.findById(id);
    }
}
