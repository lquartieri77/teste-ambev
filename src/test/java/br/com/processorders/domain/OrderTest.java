package br.com.processorders.domain;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    @Test
    public void testOrderGettersAndSetters() {
        LocalDateTime now = LocalDateTime.now();
        Order order = new Order();
        order.setId(1L);
        order.setClienteDocument("123456789");
        order.setSellerCode(322);
        order.setOrderReceived(now);

        assertEquals(1L, order.getId());
        assertEquals("123456789", order.getClienteDocument());
        assertEquals(322, order.getSellerCode());
        assertEquals(now, order.getOrderReceived());
    }

    @Test
    public void testOrderConstructor() {
        Order order = new Order();
        assertNotNull(order);
    }

    @Test
    public void testOrderDefaultValues() {
        Order order = new Order();
        assertNull(order.getId());
        assertNull(order.getClienteDocument());
        assertNull(order.getSellerCode());
        assertNull(order.getOrderReceived());
    }
}
