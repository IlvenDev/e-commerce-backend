package pl.ilvendev.ecommerce.commerce.catalog;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductCatalog {

    ArrayListProductStorage productStorage;

    public ProductCatalog(ProductStorage productStorage) {

        this.productStorage = productStorage;
    }

    public List<Product> allProducts() {

        return productStorage.allProducts();
    };

    public String addProduct(String name, String description) {
        UUID id = UUID.randomUUID();
        Product newProduct = new Product(id, name, description);

        productStorage.add(newProduct);

        return id.toString();
    }

    public Product getProductBy(String id) {
        return productStorage.getProductBy(id);
    }

    public void changePrice(String id, BigDecimal newPrice) {
        Product loaded = getProductBy(id);

        loaded.setPrice(newPrice);
    }
}
