package pl.ilvendev.ecommerce.catalog;

import org.junit.jupiter.api.Test;
import pl.ilvendev.ecommerce.commerce.catalog.HashMapProductStorage;
import pl.ilvendev.ecommerce.commerce.catalog.Product;
import pl.ilvendev.ecommerce.commerce.catalog.ProductCatalog;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


public class ProductCatalogTest {
    @Test
    void canListAvailableProducts() {
        ProductCatalog catalog = new ProductCatalog(new HashMapProductStorage());

        List<Product> products = catalog.allProducts();

        assert products.isEmpty();
    }

    @Test
    void canAddProduct() {
        ProductCatalog catalog = new ProductCatalog(new HashMapProductStorage());

        catalog.addProduct("Lego set 8083", "Nice on!");
        List<Product> products = catalog.allProducts();

        assertThat(products).hasSize(1);
    }

    @Test
    void canLoadSingleProductById() {
        ProductCatalog catalog = new ProductCatalog(new HashMapProductStorage());
        String id = catalog.addProduct("Lego set 8083", "Nice one!");
        Product loaded = catalog.getProductById(id);

        assertThat(id).isEqualTo(loaded.getId());
    }

    @Test
    void canChangePrice() {
        ProductCatalog catalog = new ProductCatalog(new HashMapProductStorage());
        String id = catalog.addProduct("Lego set 8083", "Nice one!");

        catalog.changePrice(id, BigDecimal.valueOf(10.10));
        Product loaded = catalog.getProductById(id);

        assertThat(BigDecimal.valueOf(10.10)).isEqualTo(loaded.getPrice());
    }
}
