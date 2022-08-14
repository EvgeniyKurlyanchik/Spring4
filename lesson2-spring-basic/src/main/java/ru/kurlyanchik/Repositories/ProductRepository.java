package ru.kurlyanchik.Repositories;

import org.springframework.stereotype.Repository;
import ru.kurlyanchik.persist.Product;
import ru.kurlyanchik.utill.RandomProductsGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {

    private List<Product> products;

    public ProductRepository() {
        products = new ArrayList<>();
        List<Product> randomProducts = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            randomProducts.add(RandomProductsGenerator.generateRandomProduct());
        }
        products.addAll(randomProducts);
    }

    public List<Product> getProducts(){
        return products;
    }

    public Optional<Product> getProductById(long id){
        return products
                .stream()
                .filter(product -> product.getId() == id)
                .findFirst();
    }

}