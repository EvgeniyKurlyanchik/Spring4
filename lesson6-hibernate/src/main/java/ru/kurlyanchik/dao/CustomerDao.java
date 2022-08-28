package ru.kurlyanchik.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;
import ru.kurlyanchik.persist.Customer;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerDao {


    private EntityManager entityManager;

    public CustomerDao() {

    }

    @PostConstruct
    private  void init(){
        EntityManagerFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .buildSessionFactory();
        entityManager = factory.createEntityManager();
    }

    public Optional<Customer> findById(long id){
        return Optional.ofNullable(
                entityManager.find(Customer.class, id)
        );
    }

    public List findAll(){
        return entityManager.createQuery("Select c from Customer c").getResultList();
    }

    public void save(Customer customer){
        entityManager.getTransaction().begin();
        entityManager.persist(customer);
        entityManager.getTransaction().commit();
    }

    public void update(long id, Customer customer){
        entityManager.getTransaction().begin();
        Customer initialProduct;
        initialProduct = customer;
        entityManager.merge(initialProduct);
        entityManager.getTransaction().commit();
    }

    public void deleteById(long id){
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Customer.class, id));
        entityManager.getTransaction().commit();
    }

    public void delete(Customer customer){
        entityManager.getTransaction().begin();
        entityManager.remove(customer);
    }


}
