package pl.ilvendev.ecommerce.sales;

import java.math.BigDecimal;

public class ReservationDetails {
    public BigDecimal getTotal() {
        return BigDecimal.ZERO;
    }

    public String getReservationId() {
        return "123abc";
    }

    public String getPaymentURL() {
        return "http://example-payment-gateway.com/123abc";
    }
}
