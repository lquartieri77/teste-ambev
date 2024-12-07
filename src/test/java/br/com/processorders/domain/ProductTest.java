package br.com.processorders.domain;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    @Test
    public void testProductGettersAndSetters() {
        Product product = new Product();
        product.setCodProduct(1L);
        product.setNameProduct("Test Product");
        product.setProductType("Type A");
        product.setQuantity(10);
        product.setValue(new BigDecimal("99.99"));

        assertEquals(1L, product.getCodProduct());
        assertEquals("Test Product", product.getNameProduct());
        assertEquals("Type A", product.getProductType());
        assertEquals(10, product.getQuantity());
        assertEquals(new BigDecimal("99.99"), product.getValue());
    }

    @Test
    public void testProductNoArgsConstructor() {
        Product product = new Product();
        assertNotNull(product);
        assertNull(product.getCodProduct());
        assertNull(product.getNameProduct());
        assertNull(product.getProductType());
        assertNull(product.getQuantity());
        assertNull(product.getValue());
    }

    @Test
    public void testProductAllArgsConstructor() {
        Product product = new Product(1L, "Test Product", "Type A", 10, new BigDecimal("99.99"));

        assertEquals(1L, product.getCodProduct());
        assertEquals("Test Product", product.getNameProduct());
        assertEquals("Type A", product.getProductType());
        assertEquals(10, product.getQuantity());
        assertEquals(new BigDecimal("99.99"), product.getValue());
    }
}
