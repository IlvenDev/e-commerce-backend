package pl.ilvendev.ecommerce.playground;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import pl.ilvendev.ecommerce.commerce.catalog.Product;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class SqlTest {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setupDatabase(){
        jdbcTemplate.execute("DROP TABLE `productCatalog__products` IF EXISTS;");
        var createTableSql = """
            CREATE TABLE `productCatalog__products` (
                `id` varchar(255) NOT NULL,
                `name` VARCHAR(100) NOT NULL,
                `price` DECIMAL(12,2),
                PRIMARY KEY(id)
            );
        """;
        jdbcTemplate.execute(createTableSql);
    }

    @Test
    void itSelectForCurrentDate() {
        var myDate = jdbcTemplate.queryForObject(
                "Select now() myCurrentDate",
                String.class
        );

        assertThat(myDate).contains("2024");
    }

    @Test
    void itCreatesTable(){

        var countSql = "SELECT COUNT(*) FROM `productCatalog__products`;";
        var results = jdbcTemplate.queryForObject(countSql, Integer.class);

        assertThat(results).isEqualTo(0);
    }

    @Test
    void itStoreProducts(){
        var myInsertSql = """
            INSERT INTO `productCatalog__products` (id,name,price)
            VALUES
                ('productId1', 'gamer supps', 12.12),
                ('productId2', 'glass jar', 13.13);
        """;

        jdbcTemplate.execute(myInsertSql);

        var countSql = "SELECT COUNT(*) FROM `productCatalog__products`;";
        var results = jdbcTemplate.queryForObject(countSql, Integer.class);

        assertThat(results).isEqualTo(2);
    }

    @Test
    void itStoreDynamicProducts(){
        var product = new Product(UUID.randomUUID(), "gamer supps", "Gamer");
        product.setPrice(BigDecimal.valueOf(10.10));
        var myInsertSql = """
            INSERT INTO `productCatalog__products` (id,name,price)
            VALUES
                (?,?,?)
                ;
        """;
        jdbcTemplate.update(myInsertSql, product.getId(), product.getName(), product.getPrice());

        var countSql = "SELECT COUNT(*) FROM `productCatalog__products`;";
        var results = jdbcTemplate.queryForObject(countSql, Integer.class);

        assertThat(results).isEqualTo(1);
    }

    @Test
    void loadProductsById(){
        var product = new Product(UUID.randomUUID(), "gamer supps", "Gamer");
        product.setPrice(BigDecimal.valueOf(10.10));

        var myInsertSql = """
            INSERT INTO `productCatalog__products` (id,name,price)
            VALUES
                (?,?,?)
                ;
        """;
        jdbcTemplate.update(myInsertSql, product.getId(), product.getName(), product.getPrice());

        var productId = product.getId();
        var selectProductSql = "SELECT * FROM `productCatalog__products` where id = ?";
        Product loadedProduct = jdbcTemplate.queryForObject(
                selectProductSql,
                new Object[]{productId},
                (rs,i) -> {
                    var myProduct = new Product(
                            UUID.fromString(rs.getString("id")),
                            rs.getString("name"),
                            rs.getString("name")
                    );

                    myProduct.setPrice(BigDecimal.valueOf(rs.getDouble("price")));
                    return myProduct;
                }
        );

    }

    @Test
    void itLoadsAllProductsAtOnce(){
        var myInsertSql = """
            INSERT INTO `productCatalog__products` (id,name,price)
            VALUES
                ('productId1', 'gamer supps', 12.12),
                ('productId2', 'glass jar', 13.13);
        """;
        var selectProductSql = "SELECT * FROM `productCatalog__products`";

        jdbcTemplate.execute(myInsertSql);

        List<Map<String, Object>> productList = jdbcTemplate.queryForList(
                selectProductSql
        );
    };

    /*class ProductRowMapper implements RowMapper<Product>{
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException{
            var myProduct = new Product(
                    UUID.fromString(rs.getString("id")),
                    rs.getString("name"),
                    rs.getString("name")
            );

            myProduct.setPrice(BigDecimal.valueOf(rs.getDouble("price")));
            return myProduct;
        };
    };*/
}
