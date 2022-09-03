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
import ru.kurlyanchik.repositories.OldProductRepository;
import ru.kurlyanchik.repositories.ProductRepository;
import ru.kurlyanchik.repositories.ProductRepositoryImpl;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@Slf4j
@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepositoryImpl productRepository;
    private ru.kurlyanchik.persist.Product Product;


 /*   public ProductController(@Qualifier("persistentProductRepository") ProductRepositoryImpl productRepository) {
        this.productRepository = productRepository;
    }*/

    @GetMapping
    public String listPage(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "product";
    }

    @GetMapping("/{id}")
    public String form(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", productRepository.productById((int) id));
        return "product_form";
    }

    @GetMapping("/new")
    public String addNewProduct(Model model) {
        model.addAttribute("product", new Product("AAAA ",10));
        return "product_form";
    }

   @PostMapping ("/delete/{id}")
    public String deleteProductById(@PathVariable int id, OldProductRepository ProductRepositoryIml) {
        ProductRepositoryIml.deleteById(id);
        return "redirect:/product";
    }

   @PostMapping
    public String saveUser(@Valid @Size Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product_form";
        }
        productRepository.save(product);
        return "redirect:/product";
    }
    @PostMapping("/update")
    public String updateProduct(Product product, ProductRepository productRepositoryIml) {
        productRepositoryIml.save(product);
        return "redirect:/product";
    }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundExceptionHandler(Model model, EntityNotFoundException e) {
        model.addAttribute("message", e.getMessage());
        return "not_found";
    }


}
