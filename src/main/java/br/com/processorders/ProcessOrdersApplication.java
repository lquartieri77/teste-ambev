package br.com.processorders;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.scheduling.annotation.EnableScheduling;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

@SpringBootApplication
@EnableScheduling
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
@EnableJpaRepositories(basePackages = "br.com.processorders.repository")
@ComponentScan(basePackages = {
		"br.com.processorders"
})
public class ProcessOrdersApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcessOrdersApplication.class, args);
	}
}
