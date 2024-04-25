package pl.ilvendev.creditcard;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class CreditCardTest {
    @Test
    void canAssignCredit(){
        CreditCard card = new CreditCard();

        card.assignCredit(BigDecimal.valueOf(1000));

        assert BigDecimal.valueOf(1000).equals(card.getBalance());
    }

    @Test
    void cannotAssignCreditBelowThreshold(){
        CreditCard card = new CreditCard();

        try{
            card.assignCredit(BigDecimal.valueOf(50));
            assert false;
        } catch (CreditBelowThresholdException e){
            assert true;
        }
    }
}
