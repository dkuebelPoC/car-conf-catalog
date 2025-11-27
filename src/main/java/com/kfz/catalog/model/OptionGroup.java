package com.kfz.catalog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.UUID;

@Entity
@lombok.Getter
@lombok.Setter
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class OptionGroup {
    @Id
    private UUID id;
    private String code;
    private String name;
    private String description;
}
