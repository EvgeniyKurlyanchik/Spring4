package ru.kurlyanchik.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;
import ru.kurlyanchik.persist.Product;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductDao {

    private EntityManager entityManager;

    public ProductDao() {

    }

    @PostConstruct
    private void init(){
        EntityManagerFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .buildSessionFactory();
        entityManager = factory.createEntityManager();
    }

    public Optional<Product> findById(long id){
        return Optional.ofNullable(
                entityManager.find(Product.class, id)
        );
    }

    public List findAll(){
        return entityManager.createQuery("select product from Product as product").getResultList();
    }

    public void save(Product product){
        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();
    }

    public void update(long id, Product product){
        entityManager.getTransaction().begin();
        Product initialProduct = entityManager.find(Product.class, id);
        initialProduct = product;
        entityManager.merge(initialProduct);
        entityManager.getTransaction().commit();
    }

    public void deleteById(long id){
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Product.class, id));
        entityManager.getTransaction().commit();
    }

    public void delete(Product product){
        entityManager.getTransaction().begin();
        entityManager.remove(product);
        entityManager.getTransaction().commit();
    }

}

