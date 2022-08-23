package ru.kurlyanchik;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Entity
@Getter
@Setter

public class Product {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "can not be empty!!!")
    @Size(min=2,max = 20,message = "поле должно быть от 2 до 20 символов")
    private String product;
@Min(1)
@Max(10000)
    private int price;


}
