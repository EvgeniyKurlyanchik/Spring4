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
/*
1. Создать сущность «Товар» (id, название, стоимость) и соответствующую таблицу в
        БД. Заполнить таблицу тестовыми данными (20 записей).
        2. Сделать RestController позволяющий выполнять следующий
        набор операции над этой сущностью:
        получение товара по id [ GET .../app/products/{id} ]
        получение всех товаров [ GET .../app/products ]
        создание нового товара [ POST .../app/products ]
        удаление товара по id.[ GET .../app/products/delete/{id} ]
        (Замечание: пока делаем немного не по правилам REST API, эта тема
        будет разбираться на следующих занятиях, поэтому удаление выполняется
        через http-метод GET, а не DELETE)*/
