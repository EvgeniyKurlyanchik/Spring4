package ru.kurlyanchik.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.pool.TypePool;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kurlyanchik.exceptions.EntityNotFoundException;
import ru.kurlyanchik.model.dto.ProductDto;
import ru.kurlyanchik.service.ProductService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Slf4j
@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class RestResource {

    private final ProductService service;

    @GetMapping
    public List<ProductDto> listPage(
            @RequestParam(required = false) String nameFilter,
            @RequestParam(required = false) String priceFilter,
            @RequestParam(required = false) Optional<Integer> page,
            @RequestParam(required = false) Optional<Integer> size,
            Model model
    ) {
        Integer pageValue = page.orElse(1) - 1;
        Integer sizeValue = size.orElse(3);
        Page<ProductDto> allByFilter = service.findAllByFilter(nameFilter, priceFilter, pageValue, sizeValue);
        List<ProductDto> products = allByFilter.get().collect(Collectors.toList());
        return products;

    }

    @GetMapping("/{id}")
    public ProductDto form(@PathVariable("id") long id, Model model) {
        ProductDto productDto = service.findProductById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        return productDto;
    }

    @PostMapping
    public ProductDto saveProduct(@RequestBody ProductDto product) {
        if (product.getId() != 0) throw new IllegalArgumentException("there shouldn't be a product with this id");
        service.save(product);
        return product;
    }
}

