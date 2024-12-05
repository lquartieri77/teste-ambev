package br.com.processorders.api.dto.response;

import br.com.processorders.api.dto.DTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class ProductDTO extends DTO {

    @JsonProperty("cod-produto")
    private Long codProduct;

    @JsonProperty("descricao")
    private String nameProduct;

    @JsonProperty("tipo")
    private String productType;

    @JsonProperty("quantidade")
    private Integer quantity;

    @JsonProperty("valor")
    private BigDecimal value;
}
