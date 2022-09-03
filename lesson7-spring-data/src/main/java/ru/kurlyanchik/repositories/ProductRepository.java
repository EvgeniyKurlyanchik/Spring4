package ru.kurlyanchik.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.kurlyanchik.model.Product;

import java.util.List;
public interface ProductRepository extends JpaRepository<Product, Long> {


    @Transactional
    @Modifying
    @Query("delete from Product p where p.id = ?1")
    void deleteById(String id);

   /* @Query(value = """
            select * from products p
            where (:nameFilter is null or p.title like :nameFilter)
            and (:priceFilter is null or p.price like :priceFilter)
            """,
           *//* countQuery = """
            select count(*) from products p
            where (:nameFilter is null or p.title like :nameFilter)
            and (:priceFilter is null or p.pricelike :priceFilter)
            """,*//*
            nativeQuery = true)
    List<Product> productsByFilter(String nameFilter, String priceFilter);*/

}
