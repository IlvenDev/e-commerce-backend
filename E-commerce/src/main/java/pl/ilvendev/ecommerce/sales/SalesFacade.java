package pl.ilvendev.ecommerce.sales;

import pl.ilvendev.ecommerce.sales.cart.Cart;
import pl.ilvendev.ecommerce.sales.cart.InMemoryCartStorage;

public class SalesFacade {

    private InMemoryCartStorage cartStorage;

    public Offer getCurrentOffer(String customerId) {
        return new Offer();
    }

    public ReservationDetails acceptOffer(String customerId) {
        return new ReservationDetails();
    }

    public void addToCart(String customerId, String productId) {
        Cart cart = loadCartForCustomer(customerId);

        cart.addProduct(productId);
    }

    private Cart loadCartForCustomer(String customerId) {
        return (Cart) cartStorage.findByCustomerId(customerId).orElse(Cart.empty());
    }




}

