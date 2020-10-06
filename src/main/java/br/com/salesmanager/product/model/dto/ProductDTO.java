package br.com.salesmanager.product.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ProductDTO {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    private BigDecimal unitaryValue;

    @NotNull
    private Integer quantity;
}
