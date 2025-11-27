package com.kfz.catalog.controller;

import com.kfz.catalog.model.ProductOption;
import com.kfz.catalog.service.OptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/catalog/options")
public class OptionController {
    private final OptionService service;

    public OptionController(OptionService service) {
        this.service = service;
    }

    @GetMapping
    public Map<String, List<ProductOption>> getAllOptions() {
        return service.getGroupedOptions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductOption> getById(@PathVariable UUID id) {
        Optional<ProductOption> opt = service.findById(id);
        return opt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
