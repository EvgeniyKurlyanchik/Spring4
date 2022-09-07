package ru.kurlyanchik.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private long id;
    @NotBlank
    private String title;

    private int price;

    private String password;

    private String matchingPassword;

    public ProductDto(long id, String title, int price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

}
