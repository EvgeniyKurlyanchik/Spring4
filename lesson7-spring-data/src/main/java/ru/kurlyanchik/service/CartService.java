package ru.kurlyanchik.service;
import ru.kurlyanchik.model.Product;
import ru.kurlyanchik.model.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface CartService {

    void addProduct(Product product);

    List<Product> getProductList();

    void changeCount(Integer id, Integer delta);

    void delete(Integer id);

    Integer getFullSum(Long id);

    void addProduct(Optional<ProductDto> productById);
}
