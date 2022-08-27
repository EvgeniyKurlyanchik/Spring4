package ru.kurlyanchik;

import jakarta.persistence.EntityManagerFactory;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

@AllArgsConstructor
public class Main {

    private static EntityManagerFactory EntityManager;
    private static Session entityManager;

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = (EntityManagerFactory) new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        ProductRepository pr = new ProductRepository(entityManagerFactory);
       /* pr.findAll();
       pr.findById(2);
        pr.count();*/
        pr.delete(3);
        pr.save("product1");


        entityManagerFactory.close();
    }

}


/*

        jakarta.persistence.EntityManager entityManager = entityManagerFactory.createEntityManager();

        //INSERT
        entityManager.getTransaction().begin();

        entityManager.persist(new Product("Product1", 111));
        entityManager.persist(new Product("Product2", 222));
        entityManager.persist(new Product("Product4", 333));

        entityManager.getTransaction().commit();
        // DELETE
    */
/*   entityManager.getTransaction().begin();

        Product product = entityManager.find(Product.class, 2L);
       entityManager.remove(product);
       entityManager.createQuery("delete from Product u where u.id = 1").executeUpdate();

        entityManager.getTransaction().commit();
*//*

    // SELECT
        Product product = entityManager.find(Product.class, 1L);
        System.out.println();
        // JPQL, HQL
        
       List<Product> users = entityManager.createQuery("select p from Product p", Product.class)
                .getResultList();

        for (Product productFromDb : users.toArray(new Product[0])) {
            System.out.println(productFromDb);
        }


        entityManager.close();
        entityManagerFactory.close();
    }}*/
