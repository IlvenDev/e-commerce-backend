package pl.ilvendev.ecommerce.commerce.catalog;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ArrayListProductStorage implements ProductStorage {
    private ArrayList<Product> products;

    public ArrayListProductStorage(){
        this.products = new ArrayList<Product>();
    }

    @Override
    public List<Product> allProducts() {
        return products;
    };

    @Override
    public void add(Product newProduct) {
       products.add(newProduct);
    };

    @Override
    public Product getProductBy(String id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .get();
    }};