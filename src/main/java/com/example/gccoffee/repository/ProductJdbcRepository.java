package com.example.gccoffee.repository;

import com.example.gccoffee.model.Category;
import com.example.gccoffee.model.Product;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public class ProductJdbcRepository implements ProductRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private static final RowMapper<Product> productRowMapper = (rs, rowNum) -> {
        var productId = JdbcUtils.toUUID(rs.getBytes("product_id"));
        var productName = rs.getString("product_name");
        var category = Category.valueOf(rs.getString("category"));
        var price = rs.getLong("price");
        var description = rs.getString("description");
        var createdAt = JdbcUtils.toLocalDateTime(rs.getTimestamp("created_at"));
        var updatedAt = JdbcUtils.toLocalDateTime(rs.getTimestamp("updated_at"));
        return new Product(productId, productName, category, price, description, createdAt, updatedAt);
    };

    public ProductJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query("select * from products;", productRowMapper);
    }

    @Override
    public Product insert(Product product) {
        var update= jdbcTemplate.update("INSERT INTO products(product_id, product_name, category, price, description, created_at, updated_at)" +
                " VALUES(UUID_TO_BIN(:productId),:productName, :category, :price, :description, :createdAt, :updatedAt);",toParamMap(product));
        if(update==0){
            throw new RuntimeException("Noting was inserted");
        }
        return product;
    }

    @Override
    public Product update(Product product) {
        var update = jdbcTemplate.update("UPDATE products SET  product_name=:productName ,category=:category ,price=:price ,description=:description ,updated_at=:updatedAt" +
                " WHERE product_id = UUID_TO_BIN(:productId)", toParamMap(product));
        if(update != 1){
            throw new RuntimeException("Nothing was updated");
        }
        return product;
    }

    @Override
    public Optional<Product> findById(UUID prductId) {

        try {
            return Optional.of(jdbcTemplate.queryForObject("SELECT * FROM products WHERE product_id=UUID_TO_BIN(:productId)",
                    Collections.singletonMap("productId",prductId.toString()),productRowMapper));
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<Product> findByName(String productName) {
        try {
            return Optional.of(jdbcTemplate.queryForObject("SELECT * FROM products WHERE product_name=:productName",
                    Collections.singletonMap("productName",productName.toString()),productRowMapper));
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return jdbcTemplate.query("SELECT * FROM products WHERE category = :category",Collections.singletonMap("category",category.name()),productRowMapper);
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.getJdbcTemplate().update("DELETE FROM products");
    }
    private  Map<String,Object> toParamMap(Product product){
        var paramMap = new HashMap<String, Object>();
        paramMap.put("productId",product.getProductId().toString());
        paramMap.put("productName",product.getProductName());
        paramMap.put("category",product.getCategory().name());
        paramMap.put("price",product.getPrice());
        paramMap.put("description",product.getDescription());
        paramMap.put("createdAt",product.getCreatedAt());
        paramMap.put("updatedAt",product.getUpdatedAt());
        return paramMap;
    }

}
