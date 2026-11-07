package com.khethelihle.vhs.product;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRequest (

        @NotBlank
        String name,

        String description,

        @NotNull
        @DecimalMin(value = "0.01")
        BigDecimal price

    ) {}
