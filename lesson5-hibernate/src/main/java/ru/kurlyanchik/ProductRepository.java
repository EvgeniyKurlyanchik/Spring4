package ru.kurlyanchik;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.Optional;

public class ProductRepository {
    private final EntityManagerFactory emFactory;

    public ProductRepository(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    // findById
    public Optional<Product> findById(long id) {
        EntityManager em = emFactory.createEntityManager();
        try {
            return Optional.ofNullable(em.find(Product.class, id));
        } finally {
            em.close();
        }
    }

    // findAll
    public List<Product> findAll() {
        EntityManager em = emFactory.createEntityManager();
        try {
            return em.createNamedQuery("findAllProducts", Product.class).getResultList();
        } finally {
            em.close();
        }
    }

    public long count() {
        EntityManager em = emFactory.createEntityManager();
        try {
            return em.createNamedQuery("countAllProducts", Long.class).getSingleResult();
        } finally {
            em.close();
        }
    }

    // save
    public void save(String product) {
        EntityManager em = emFactory.createEntityManager();
        try {
            em.getTransaction().begin();

            if (product == null) {
                em.persist(product);
            } else {
                em.merge(product);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    //delete
    public void delete(long id) {
        EntityManager em = emFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("deleteProductById").setParameter("id", id).executeUpdate();


            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}