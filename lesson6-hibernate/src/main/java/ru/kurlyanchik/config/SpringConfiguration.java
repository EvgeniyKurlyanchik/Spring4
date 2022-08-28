package ru.kurlyanchik.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kurlyanchik.dao.CustomerDao;
import ru.kurlyanchik.dao.ProductDao;
import ru.kurlyanchik.services.CustomerService;
import ru.kurlyanchik.services.ProductService;


@Configuration
public class SpringConfiguration {

    @Bean
    public CustomerDao customerDao(){
        return new CustomerDao();
    }

    @Bean
    public ProductDao productDao(){
        return new ProductDao();
    }

    @Bean
    public ProductService productService(ProductDao productDao){
        return new ProductService(productDao);
    }

    @Bean
    public CustomerService customerService(CustomerDao customerDao){
        return new CustomerService(customerDao);
    }

}
