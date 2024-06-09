package pl.ilvendev.ecommerce.sales.reservation;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;


public class OfferAcceptanceTest {
    @Test
    void canAcceptOffer() {
        SalesFacade sales = thereIsSales();
        String customer = thereIsCustomer("John");
        String productId = thereIsProduct("X", BigDecimal.valueOf(10));

        sales.addToCart(customer, productId);
        sales.addToCart(customer, productId);

        AcceptOfferRequest acceptOfferRequest = new AcceptOfferRequest();
        acceptOfferRequest
                .setFirstname("John")
                .setLastname("Doe")
                .setEmail("John.doe@example.com");

        ReservationDetails reservationDetails = sales.acceptOffer(customer);

        assertThat(reservationDetails.getPaymentURL()).isNotNull();
        assertThat(reservationDetails.getReservationId()).isNotNull();

        assertPaymentHasBeenRegistered();
        assertThereIsReservationWithId(reservationDetails.getReservationId());
        assertReservationIsPending(reservationDetails.getReservationId());
        assertReservationIsDone(reservationDetails.getReservationId());


    }

    private void assertReservationIsDone(Object reservationId) {
    }

    private void assertReservationIsPending(Object reservationId) {
        
    }

    private void assertThereIsReservationWithId(Object reservationId) {
    }

    private String thereIsProduct(String x, BigDecimal bigDecimal) {
        return null;
    }

    private SalesFacade thereIsSales() {
        return new SalesFacade(new InMemoryCartStorage(), new OfferCalculator());
    }

    private void assertPaymentHasBeenRegistered() {
        assert assertThat(spyPaymentGateway.getRequestCount()).isEqualTo(1);
    }

}
