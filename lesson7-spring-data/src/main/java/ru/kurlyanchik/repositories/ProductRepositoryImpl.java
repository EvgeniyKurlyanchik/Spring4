package ru.kurlyanchik.repositories;

import org.springframework.stereotype.Repository;
import ru.kurlyanchik.persist.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    /*private static EntityManager entityManager;*/
    @PersistenceContext
    private  EntityManager entityManager;

    public List<Product> findAll() {
        return entityManager.createQuery("select p from Product p", Product.class).getResultList();
    }

    public Optional<Product> productById(int id) {
        return Optional.ofNullable(entityManager.find(Product.class, id));
    }
@Transactional
   public Product save(Product product){
        if ( product.getTitle()!=null ) {
            entityManager.merge(product);
        } else {
            entityManager.persist(product);
        }
        return product;
    }
@Transactional
    public void deleteById(int id){
        entityManager.createQuery("delete from Product p where p.id =:id")
                .setParameter("id",id)
                .executeUpdate();
    }
}