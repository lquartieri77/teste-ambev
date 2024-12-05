package br.com.processorders.domain;

import br.com.processorders.domain.serializer.LocalDateTimeDeserializer;
import br.com.processorders.domain.serializer.LocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Product> productList;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime orderDate;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime orderReceived;

    private Integer clientCod;
    private String clientName;
    private String clienteDocument;
    private Integer sellerCode;


    /**
     * O objetivo aqui eh garantir que o sistme entenda como ordens iguais se tiverem estes mesmos dados iguais
     * Claro, uma evolucao eh verificar ainda em base se ja existe ordem igual
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderDate, order.orderDate) && Objects.equals(clienteDocument, order.clienteDocument) && Objects.equals(sellerCode, order.sellerCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderDate, clienteDocument, sellerCode);
    }
}
