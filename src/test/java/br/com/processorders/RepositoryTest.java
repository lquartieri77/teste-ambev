package br.com.processorders;

import br.com.processorders.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class RepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void contextLoads() throws Exception {
        assertThat(orderRepository).isNotNull();
    }
}
