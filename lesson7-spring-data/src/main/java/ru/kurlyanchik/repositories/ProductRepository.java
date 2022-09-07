package ru.kurlyanchik.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import ru.kurlyanchik.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product> {
    Page<Product> findAllByTitleLike(String nameFilter, Pageable pageable);

    @Query(value = """
            select * from products p
            where (:nameFilter is null or p.title like :nameFilter)
            and (:priceFilter is null or p.price like :priceFilter)
            """,
            countQuery = """
                    select count(*) from products p
                    where (:nameFilter is null or p.title like :nameFilter)
                    and (:priceFilter is null or p.price like :priceFilter)
                    """,
            nativeQuery = true)
    Page<Product> productsByFilter(String nameFilter, String priceFilter, Pageable pageable);

}
