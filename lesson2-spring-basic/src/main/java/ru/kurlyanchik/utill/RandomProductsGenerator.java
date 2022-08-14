package ru.kurlyanchik.utill;

import ru.kurlyanchik.persist.Product;

import java.util.Random;

public class RandomProductsGenerator {

    public static Product generateRandomProduct(){
        Random rand = new Random();
        long id = (long) (Math.random() * (1000));
        int price = rand.nextInt(1000);
        return new Product(id, "testTitle" + id, price);
    }

}