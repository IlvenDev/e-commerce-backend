package pl.ilvendev.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.ilvendev.ecommerce.sales.SalesFacade;
import pl.ilvendev.ecommerce.sales.cart.HashMapCartStorage;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    /*@Bean
    ProductCatalog createCatalog() {
        var catalog = new ProductCatalog(new ArrayListProductStorage());
        var pid1 = catalog.addProduct("Lego set 8083", "nice one", BigDecimal.valueOf(100.00));
        catalog.changePrice(pid1, BigDecimal.valueOf(100.10));

        var pid2 = catalog.addProduct("Cobi set 8083", "nice one", BigDecimal.valueOf(100.00));
        catalog.changePrice(pid2, BigDecimal.valueOf(50.10));

        return catalog;
    }*/

    @Bean
    SalesFacade createSales() {
        return new SalesFacade(new HashMapCartStorage());
    }
}
