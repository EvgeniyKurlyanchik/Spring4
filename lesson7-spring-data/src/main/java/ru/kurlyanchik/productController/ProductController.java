package ru.kurlyanchik.productController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kurlyanchik.exceptions.EntityNotFoundException;
import ru.kurlyanchik.persist.Product;
import ru.kurlyanchik.repositories.ProductRepository;
import javax.validation.Valid;
import javax.validation.constraints.Size;
@Slf4j
@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;
    private ru.kurlyanchik.persist.Product Product;

    @GetMapping("/{id}")
    public String form(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productRepository.findById((long) id));
        return "product_form";
    }
    @GetMapping("/new")
    public String addNewProduct(Model model) {
        model.addAttribute("product", new Product("AAAA", 10));
        return "product_form";
    }
    @PostMapping
    public String saveUser(@Valid @Size Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product_form";
        }
        productRepository.save(product);
        return "redirect:/product";
    }
    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/product";
    }
    @GetMapping
    public String listPage(
            @RequestParam(required = false) String nameFilter,
            @RequestParam(required = false) String priceFilter,
            Model model
    ){
        nameFilter = nameFilter == null || nameFilter.isBlank() ? null : "%" + nameFilter.trim() + "%";
        priceFilter = priceFilter == null || priceFilter.isBlank() ? null : "%" + priceFilter.trim() + "%";
        model.addAttribute("products", productRepository.productsByFilter(nameFilter, priceFilter));
        return "product";
    }
    @PostMapping("/update/{id}")
    public String updateProduct(Product product, ProductRepository productRepository) {
        productRepository.save(product);
        return "redirect:/product";
    }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundExceptionHandler(Model model, EntityNotFoundException e) {
        model.addAttribute("message", e.getMessage());
        return "not_found";
    }

}
