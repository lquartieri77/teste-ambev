package br.com.processorders.api.dto.response;

import br.com.processorders.api.dto.DTO;
import br.com.processorders.domain.Product;
import br.com.processorders.domain.serializer.LocalDateTimeDeserializer;
import br.com.processorders.domain.serializer.LocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Builder
@Getter
@Setter
public class OrderDTO  extends DTO {

    @JsonProperty("data-pedido")
    private LocalDateTime orderDate;

    @JsonProperty("data-processamento-order")
    private LocalDateTime orderReceived;
    @JsonProperty("cliente-nome")
    private String clientName;

    @JsonProperty("cliente-id")
    private String clienteDocument;

    @JsonProperty("vendedor")
    private Integer sellerCode;

    @JsonProperty("valor-total-order")
    private BigDecimal valorTotal;

    @JsonProperty("produtos")
    private List<ProductDTO> productList;



}
