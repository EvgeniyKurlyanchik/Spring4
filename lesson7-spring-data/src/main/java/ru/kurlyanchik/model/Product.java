package ru.kurlyanchik.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;

@Column(nullable = false, unique = true)
private  String title;
@Column(nullable = false)
private int price;

    @Pattern(regexp = "^(?=.*?[0-9])(?=.*?[A-Z]).{8,}$", message = "Password too simple")
    private String password;



    public Product(long id, String title, int price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }
   /* public Product(Long id, String title, int price, @Pattern(regexp = "^(?=.*?[0-9])(?=.*?[A-Z]).{8,}$", message = "Password too simple") String password) {
        this.title = title;
        this.price = price;
        Product product = new Product();*/
    }




