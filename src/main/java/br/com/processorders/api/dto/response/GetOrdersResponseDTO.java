package br.com.processorders.api.dto.response;

import br.com.processorders.api.dto.DTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;
@Builder
@Getter
@Setter
public class GetOrdersResponseDTO  extends DTO {
    @JsonProperty("assinatura-resposta")
    private String assinaturaResposta;
    @JsonProperty("data-hora-resposta")
    private LocalDateTime dataHoraResposta ;

    @JsonProperty("orders")
    private Page<OrderDTO> listaDeOrders;

    public String getAssinaturaResposta() throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(String.valueOf(LocalDateTime.now()).getBytes());
        return new BigInteger(1, md.digest()).toString(32);
    }

}
