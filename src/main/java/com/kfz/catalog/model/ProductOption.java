package com.kfz.catalog.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@lombok.Getter
@lombok.Setter
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ProductOption {
    @Id
    private UUID id;

    @ManyToOne
    private OptionGroup group;

    private String code;
    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal priceDelta;
    private boolean isDefault;
    private boolean active;
}
