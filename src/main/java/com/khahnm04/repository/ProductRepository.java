package com.khahnm04.repository;

import com.khahnm04.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByStoreId(Long storeId);

    @Query("""
            SELECT p FROM Product p
            WHERE p.store.id = :storeId
            AND LOWER(p.name) LIKE LOWER(CONCAT('%', :query, '%'))
            OR LOWER(p.brand) LIKE LOWER(CONCAT('%', :query, '%'))
            OR LOWER(p.sku) LIKE LOWER(CONCAT('%', :query, '%'))
    """)
    List<Product> searchByKeyWord(@Param("storeId") Long storeId, @Param("query") String keyWord);

}
