package ru.kurlyanchik.Repositories;

import org.springframework.stereotype.Repository;
import ru.kurlyanchik.persist.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
@Repository
public class ProductRepository {
    private static final Map<Long, Product> productMap = new ConcurrentHashMap<>();

    private final AtomicLong identity = new AtomicLong(0);
    @PostConstruct
    public void init()  {
        this.insert(new Product(1L,"Product1"));
        this.insert(new Product(2L,"Product2"));
        this.insert(new Product(3L,"Product3"));
        this.insert(new Product(4L,"Product4"));
        this.insert(new Product(5L,"Product5"));
        this.insert(new Product(6L,"Product6"));
        this.insert(new Product(7L,"Product7"));
        this.insert(new Product(8L,"Product8"));
    }



    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }

    public Product findById(long id) {
        return productMap.get(id);
    }

    public void insert(Product product) {
        long id = identity.incrementAndGet();
        product.setId(id);
        productMap.put(id, product);
    }

 /*   public void update(Product product) {
        productMap.put(product.getId(), product);
    }*/

    public static void delete(long id) {
        productMap.remove(id);
    }

    public Product save(Product product) {
        if (product.getId() == null) {
            product.setId(identity.incrementAndGet());
        }
        productMap.put(product.getId(), product);
        return product;
    }
}