package pl.ilvendev.ecommerce.catalog;

import org.junit.jupiter.api.Test;
import pl.ilvendev.ecommerce.commerce.catalog.Product;
import pl.ilvendev.ecommerce.commerce.catalog.ProductStorage;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

public class HashMapProductStorageTest {

    @Test
    void itStoreNewProduct(){
        ProductStorage storage = thereIsProductStorage();
        Product product = thereIsExampleProduct();

        storage.add(product);

        List<Product> products = storage.allProducts();
        assertThat(products)
                .hasSize(1)
                .extracting(Product::getName)
                .contains(TEST_PRODUCT_NAME);
    }

    private Product thereIsExampleProduct() {
        return new Product(UUID.randomUUID(), TEST_PRODUCT_NAME, "simple desc");
    }

    private ProductStorage thereIsProductStorage() {
        return new HashMapProductStorageTest();
    };

    @Test
    void itLoadsAllProducts(){

    };

    @Test
    void itLoadsProductById(){

    };
}
