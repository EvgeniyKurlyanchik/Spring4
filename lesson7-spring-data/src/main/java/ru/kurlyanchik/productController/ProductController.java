package ru.kurlyanchik.productController;

import com.querydsl.core.BooleanBuilder;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kurlyanchik.exceptions.EntityNotFoundException;
import ru.kurlyanchik.model.QProduct;
import ru.kurlyanchik.model.dto.ProductDto;
import ru.kurlyanchik.repositories.ProductRepository;
import ru.kurlyanchik.service.ProductService;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/product")
@RequiredArgsConstructor

public class ProductController {

    private  final ProductService service ;

    @GetMapping
   public String listPage(
            @RequestParam(required = false) String nameFilter,
            @RequestParam(required = false) String priceFilter,
            @RequestParam(required = false) Optional<Integer> page,
            @RequestParam(required = false) Optional<Integer> size,
            Model model
    ) {
        Integer pageValue = page.orElse(1) - 1;
        Integer sizeValue = size.orElse(3);

        model.addAttribute("products", service.findAllByFilter(nameFilter, priceFilter, pageValue, sizeValue));
        return "product";
    }
/*@GetMapping
public String listPage(
        @RequestParam(required = false) String nameFilter,
        @RequestParam(required = false) String priceFilter,

        Model model
) {

    QProduct product= QProduct.product;
    BooleanBuilder predicate = new BooleanBuilder();
    if (nameFilter != null && !nameFilter.isBlank()){
        predicate.and(product.title.contains(nameFilter.trim()));
    }
    if(priceFilter!= null && !priceFilter.isBlank()){
       predicate.and(product.price.like(priceFilter.trim()));
    }
   *//* productRepository.findAll(predicate);*//*
    model.addAttribute("products", service.findAllByFilter(nameFilter,priceFilter));
    return "product";
}*/

    @GetMapping("/{id}")
    public String form(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", service.findProductById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found")));
        return "product_form";
    }
    @GetMapping("/new")
    public String addNewProduct(Model model) {
        model.addAttribute(("product"),new ProductDto(1,"",1));
        return "product_form";
    }
   @PostMapping
    public String saveProduct( @Valid @ModelAttribute("product") ProductDto product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product_form";
        }
        service.save(product);
        return "redirect:/product";
    }
    @DeleteMapping("/{id}")
    public String deleteProductById(@PathVariable long id) {
        service.deleteProductById(id);
        return "redirect:/product";
    }
   /* @GetMapping
    public String listPage(
            @RequestParam(required = false) String nameFilter,
            @RequestParam(required = false) String priceFilter,

            Model model,
            Integer pageValue, Integer sizeValue){
        nameFilter = nameFilter == null || nameFilter.isBlank() ? null : "%" + nameFilter.trim() + "%";
        priceFilter = priceFilter == null || priceFilter.isBlank() ? null : "%" + priceFilter.trim() + "%";
        model.addAttribute("products", service.productsByFilter(nameFilter, priceFilter, pageValue, sizeValue));
        return "product";
    }*/
    @PostMapping("/update/{id}")
    public String updateProduct(@ModelAttribute("product")ProductDto product) {
        service.save(product);
        return "redirect:/product";
    }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundExceptionHandler(Model model, EntityNotFoundException e) {
        model.addAttribute("message", e.getMessage());
        return "not_found";
    }

}
