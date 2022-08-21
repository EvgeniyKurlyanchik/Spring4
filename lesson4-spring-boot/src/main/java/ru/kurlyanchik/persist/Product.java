package ru.kurlyanchik.persist;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Product {

    private Long id;

    private String product;
    private int price;

    public Product(long id, String product, int price) {
    this.id=id;
        this.product = product;
        this.price= price;
    }

}
