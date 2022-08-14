package ru.kurlyanchik.services;

import ru.kurlyanchik.Repositories.ProductRepository;
import org.springframework.stereotype.Component;
import ru.kurlyanchik.persist.Product;

import java.util.ArrayList;
import java.util.List;

@Component
public class Cart {

    private List<Product> cartProducts;
    private final ProductRepository repository;

    public Cart(ProductRepository repository) {
        this.repository = repository;
        this.cartProducts = new ArrayList<>();
    }




    public void deleteProductById(long id){
        cartProducts.remove(
                cartProducts.stream().filter(product -> product.getId() == id).findFirst().orElseThrow(RuntimeException::new));
    }

    public void addProductById(long id){
        cartProducts.add(repository.getProductById(id).orElseThrow(RuntimeException::new));
    }

    public List<Product> getCartProducts(){
        return cartProducts;
    }
}