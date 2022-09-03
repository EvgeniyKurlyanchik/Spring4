package ru.kurlyanchik.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


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

    public Product(Long id, String title, int price, @Pattern(regexp = "^(?=.*?[0-9])(?=.*?[A-Z]).{8,}$", message = "Password too simple") String password) {
        this.title = title;
        this.price = price;
        Product product = new Product();
    }


}

