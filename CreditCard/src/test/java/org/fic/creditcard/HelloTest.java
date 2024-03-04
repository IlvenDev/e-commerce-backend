package org.fic.creditcard;

import org.junit.jupiter.api.Test;

public class HelloTest {
    @Test
    void helloTest(){
        var name = "Jakub";
        var message = String.format("Hello %s", name);

        System.out.println(message);
    }

    @Test
    void equationTest(){
        //AAA
        // / Arrange
        int a = 2;
        int b = 3;
        // / Act
        var result = a + b;
        // / Assert
        assert (5 == result);
    }
}
