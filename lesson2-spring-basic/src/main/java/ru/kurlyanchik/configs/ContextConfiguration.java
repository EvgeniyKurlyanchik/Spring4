package ru.kurlyanchik.configs;

import ru.kurlyanchik.Repositories.ProductRepository;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.kurlyanchik.services.Cart;

@Configuration
public class ContextConfiguration {

    @Bean
    public ProductRepository productRepository() {
        return new ProductRepository();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Cart cart(ProductRepository productRepository) {
        return new Cart(productRepository);
    }
}