package com.example.gccoffee.repository;

import com.example.gccoffee.model.Category;
import com.example.gccoffee.model.Product;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Testcontainers
@ActiveProfiles("test")
class ProductJdbcRepositoryTest {
    @Container
    private static MySQLContainer mySQLContainer = new MySQLContainer<>("mysql:8")
            .withInitScript("schema.sql");

    
    @Autowired
    ProductRepository repository;
    private static final Product newProduct = new Product(UUID.randomUUID(),"new-product", Category.COFFEE_BEAN_PACKAGE,1000L);
    @Test
    @Order(1)
    @DisplayName("상품을 추가할 수있다.")
    void testInsert(){
        repository.insert(newProduct);
        var all = repository.findAll();
        assertThat(all.isEmpty()).isFalse();
    }
    @Test
    @Order(2)
    @DisplayName("상품을 이름으로 죄회할 수 있다.")
    void testFindByName(){
        var product = repository.findByName(newProduct.getProductName());
        assertThat(product.isEmpty()).isFalse();
    }
    @Test
    @Order(3)
    @DisplayName("상품을 상품아이디로 죄회할 수 있다.")
    void testFindById(){
        var product = repository.findById(newProduct.getProductId());
        assertThat(product.isEmpty()).isFalse();
    }
    @Test
    @Order(4)
    @DisplayName("상품들을 카테고리로 죄회할 수 있다.")
    void testFindByCategory(){
        var product = repository.findByCategory(newProduct.getCategory());
        assertThat(product.isEmpty()).isFalse();
    }
    @Test
    @Order(5)
    @DisplayName("상품을 수정할 수 있다.")
    void testUpdate(){
        newProduct.setProductName("updated-product");
        repository.update(newProduct);
        var product = repository.findById(newProduct.getProductId());
        assertThat(product.isEmpty()).isFalse();
        assertThat(product.get()).isEqualTo(newProduct);
    }
    @Test
    @Order(6)
    @DisplayName("상품을 전체 삭제한다")
    void testDeleteAll(){
        repository.deleteAll();
        var all = repository.findAll();
        assertThat(all.isEmpty()).isTrue();
    }
    
}