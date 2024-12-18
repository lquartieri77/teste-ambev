package br.com.processorders.api.dto.request;

import br.com.processorders.api.dto.DTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper=false)
public class FilterOrderRequestDTO extends DTO {

    @JsonProperty("data-processamento-order")
    private LocalDateTime orderReceived;

    @JsonProperty("cod-vendedor")
    private Integer sellerCode;

    @JsonProperty("cliente-id")
    private String clienteDocument;

}
