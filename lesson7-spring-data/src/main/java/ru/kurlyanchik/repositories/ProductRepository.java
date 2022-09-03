package ru.kurlyanchik.repositories;

import ru.kurlyanchik.persist.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAll();

    Optional<Product> productById(int id);
    Product save(Product product);


}
