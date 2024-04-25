package pl.ilvendev.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.ilvendev.ecommerce.commerce.catalog.ProductCatalog;
import pl.ilvendev.ecommerce.commerce.catalog.HashMapProductStorage;

@SpringBootApplication
public class App {
    public static void main(String[] args){
        System.out.println("Here");
        SpringApplication.run(App.class, args);
    }

    @Bean
    ProductCatalog createMyProductCatalog(){
        var catalog = new ProductCatalog(new HashMapProductStorage());
        catalog.addProduct("Gamer sups", "Sigma taste");
        catalog.addProduct("Glass jar", "For figurines");


        return catalog;
    }
}
