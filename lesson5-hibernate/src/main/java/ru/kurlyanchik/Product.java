package ru.kurlyanchik;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name="products")
@NamedQueries({
        @NamedQuery(name = "findAllProducts", query = "Select p from Product p"),
        @NamedQuery(name = "countAllProducts", query = "Select  count(p) from Product p"),
        @NamedQuery(name = "deleteProductById", query = "delete  from Product p where p.id = :id")
})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String product;
    @Column(nullable = false, length = 1000)
    private int price;

    public Product(String product, int price) {
        this.id=id;
        this.product = product;
        this.price = price;
    }

}
