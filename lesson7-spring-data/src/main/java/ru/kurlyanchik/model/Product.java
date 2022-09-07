package ru.kurlyanchik.model;


import lombok.*;

import javax.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "can not be empty!!!")
    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private int price;


    private String password;


    public Product(long id, String title, int price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

}



