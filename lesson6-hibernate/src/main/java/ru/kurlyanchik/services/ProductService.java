package ru.kurlyanchik.services;

import org.springframework.stereotype.Service;
import ru.kurlyanchik.dao.ProductDao;
import ru.kurlyanchik.persist.Product;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductDao dao;

    public ProductService(ProductDao productDao) {
        this.dao = productDao;
    }



    public List getAll(){
        return dao.findAll();
    }

    public Product getById(long id){
        return dao.findById(id).orElseThrow(RuntimeException::new);
    }

    public void save( Product product){
        dao.save(product);
    }

    private void update(long id, Product product){
        dao.update(id, product);
    }

    public void deleteById(long id){
        dao.deleteById(id);
    }

    public void delete(Product product){
        dao.delete(product);
    }

    public Optional<Object> findCustomers(long id){
        Optional<Product> product = dao.findById(id);
        if(product.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(product.get().getCustomers());
    }


}
