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



public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String product;
    @Column(nullable = false, length = 1000)
    private int price;

    public Product(String product, int price) {
        this.product = product;
        this.price = price;
    }

}
