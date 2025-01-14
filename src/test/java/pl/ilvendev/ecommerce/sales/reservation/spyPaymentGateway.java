package pl.ilvendev.ecommerce.sales.reservation;

public class spyPaymentGateway {
    static int requestCount = 0;

    public static int getRequestCount() {
        return requestCount;
    }
}
