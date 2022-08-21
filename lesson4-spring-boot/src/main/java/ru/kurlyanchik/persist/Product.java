package ru.kurlyanchik.persist;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter

public class Product {

    private Long id;
    @NotBlank(message = "can not be empty!!!")
    @Size(min=2,max = 20,message = "поле должно быть от 2 до 20 символов")
    private String product;
@Min(1)
@Max(10000)
    private int price;

    public Product( String product, int price) {
        this.product = product;
        this.price= price;
    }

}
