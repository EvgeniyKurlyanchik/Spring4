package ru.kurlyanchik.model;

import org.springframework.stereotype.Component;
import ru.kurlyanchik.exceptions.ProductNotFoundException;

import javax.annotation.PostConstruct;
import java.util.*;


@Component
public class Cart {

    private List<Product> cartStorage;

    private Map<Long, Integer> productCounts;// <productId, count>

    @PostConstruct
    public void init() {
        this.cartStorage = new ArrayList<>();
        this.productCounts = new HashMap<>();
    }

    public void addProduct(Product product) {
        if (!cartStorage.contains(product)) {
            productCounts.put(product.getId(), 1); // первый вызов addProduct
        } else {
            Long id = product.getId();
            Integer count = productCounts.get(id);
            productCounts.put(id, ++count);
        }
        cartStorage.add(product);
    }

    public List<Product> getCartStorage() {
        return cartStorage;
    }

    public Integer fullSum(Long id) {
        Product product = cartStorage.stream().filter(p -> p.getId() == id.longValue())
                .findFirst().orElseThrow(() -> new ProductNotFoundException("Can not found product with id:" + id));
        Integer sum = product.getPrice() * getCount(id);
        return sum;
    }

    public void remove(Integer id) {
        Optional<Product> product = cartStorage.stream().filter(p -> p.getId() == id.longValue())
                .findFirst();
        product.ifPresent(cartStorage::remove);
    }

    public Integer getCount(Long id) {
        return productCounts.get(id);
    }
}
