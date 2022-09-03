package ru.kurlyanchik.persist;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;

@Column(nullable = false, unique = true)
private  String title;
@Column(nullable = false)
@NotNull
private int price;

    public Product(String title, int price) {
        this.title = title;
        this.price = price;
        Product product = new Product();
    }


}

