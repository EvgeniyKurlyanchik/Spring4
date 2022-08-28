package ru.kurlyanchik;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.kurlyanchik.config.SpringConfiguration;
import ru.kurlyanchik.services.CustomerService;

public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        CustomerService service = context.getBean(CustomerService.class);
        System.out.println(service.getProductsList(1));
        System.out.println(service.getProductsList(2));
    }

}
