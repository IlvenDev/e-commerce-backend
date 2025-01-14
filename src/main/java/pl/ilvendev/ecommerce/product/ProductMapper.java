package pl.ilvendev.ecommerce.product;

public class ProductMapper {
    public static ProductDTO mapToDto(Product product) {
        return new ProductDTO(
            product.getId(),
            product.getProductName(),
            product.getDescription(),
            product.getPrice()
        );
    }

    public static Product mapToProduct(ProductDTO productDTO) {
        return new Product(
                productDTO.getId(),
                productDTO.getProductName(),
                productDTO.getDescription(),
                productDTO.getPrice()
        );
    }
}
