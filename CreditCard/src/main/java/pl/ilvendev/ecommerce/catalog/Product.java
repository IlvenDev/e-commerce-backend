package pl.ilvendev.ecommerce.catalog;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {

    private final UUID id;
    private final String name;
    private final String description;

    private BigDecimal price;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Product(UUID id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id.toString();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal newPrice){
        this.price = newPrice;
    }
}
