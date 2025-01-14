package pl.ilvendev.ecommerce.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductHttpTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void createProduct_shouldCreateProduct() {
        String url = String.format("http://localhost:%s/api/products", port);
        ProductDTO productDTO = new ProductDTO(null,"Name", "Desc", BigDecimal.valueOf(100));

        ResponseEntity<ProductDTO> response = restTemplate.postForEntity(url, productDTO, ProductDTO.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(productDTO.getProductName(), response.getBody().getProductName());

    }

    @Test
    public void getProductById_shouldReturnProduct() {
        String url = String.format("http://localhost:%s/api/products?id=%s", port, 20);

        ResponseEntity<ProductDTO> response = restTemplate.getForEntity(url, ProductDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(20, response.getBody().getId());
    }

    @Test
    public void getALlProducts_shouldReturnAllProducts() {
        String url = String.format("http://localhost:%s/api/products/all_products", port);

        Long length = productRepository.count();

        ResponseEntity<ProductDTO[]> response = restTemplate.getForEntity(url, ProductDTO[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(length, response.getBody().length);
    }

    @Test
    public void updateProduct_shouldUpdateProduct() {
        Long id = (long) ((Math.random() * productRepository.count()) + 1);
        String url = String.format("http://localhost:%s/api/products?id=%s", port, id);
        ProductDTO productDTO = new ProductDTO(id,"newName", "newDesc", BigDecimal.valueOf(200));
        ResponseEntity<ProductDTO> response = restTemplate.postForEntity(url, productDTO, ProductDTO.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(productDTO.getProductName(), response.getBody().getProductName());
    }

    @Test
    public void deleteProduct_shouldDeleteProduct() {
        String createUrl = String.format("http://localhost:%s/api/products", port);
        ProductDTO productDTO = new ProductDTO(null,"ProductTo Delete", "Desc", BigDecimal.valueOf(100));
        ResponseEntity<ProductDTO> createResponse = restTemplate.postForEntity(createUrl, productDTO, ProductDTO.class);
        Long id = createResponse.getBody().getId();

        String deleteUrl = String.format("http://localhost:%s/api/products?id=%s", port, id);
        HttpEntity<Void> requestEntity = new HttpEntity<>(null);
        ResponseEntity<Void> response = restTemplate.exchange(deleteUrl, HttpMethod.DELETE, requestEntity, Void.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(productRepository.existsById(id), "Product with id " + id + " not found");
    }


}
