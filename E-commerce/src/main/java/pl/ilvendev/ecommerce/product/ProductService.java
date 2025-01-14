package pl.ilvendev.ecommerce.product;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = ProductMapper.mapToProduct(productDTO);
        Product createdProduct = productRepository.save(product);
        return ProductMapper.mapToDto(createdProduct);
    }

    public ProductDTO getProductById(Long productId) {
        Product fetchedProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("A product with ID " + productId + " was not found."));
        return ProductMapper.mapToDto(fetchedProduct);
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map((product -> ProductMapper.mapToDto(product)))
                .collect(Collectors.toList());
    }

    public ProductDTO updateProduct(Long productId, ProductDTO productDTO) {
        Product targetProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("A product with ID " + productId + " was not found."));

        targetProduct.setProductName(productDTO.getProductName());
        targetProduct.setDescription(productDTO.getDescription());
        targetProduct.setPrice(productDTO.getPrice());

        Product updatedProduct = productRepository.save(targetProduct);

        return ProductMapper.mapToDto(updatedProduct);
    }

    public void deleteProduct(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new ProductNotFoundException("A product with ID " + productId + " was not found.");
        }
        productRepository.deleteById(productId);
    }
}
