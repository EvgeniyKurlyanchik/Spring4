package ru.kurlyanchik.Controllers;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kurlyanchik.model.Product;
import ru.kurlyanchik.model.dto.CreateProductDto;
import ru.kurlyanchik.service.CartService;
import ru.kurlyanchik.service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;

    private final ProductService productService;

   /* private final ModelMapper mapper;*/

    @Autowired
    public CartController(CartService cartService,
                          ProductService productService
                          ) {
        this.cartService = cartService;
        this.productService = productService;

    }

    @GetMapping("/items")
    public List<Product> cartItems() {
        return cartService.getProductList();
    }

    @PostMapping("/add")
    public void add(@RequestBody CreateProductDto productDto) {
//        ProductValidator.validate(productDto);
//        Product product = mapper.map(productDto, Product.class);
        cartService.addProduct(productService.findProductById(productDto.getId()));
    }

    @GetMapping("/sum")
    public Integer sum(@RequestBody CreateProductDto productDto) {
        return cartService.getFullSum(productDto.getId());
    }

    @GetMapping("/change_count")
    public void changeCount(@RequestParam Integer productId, @RequestParam Integer delta){
        cartService.changeCount(productId, delta);
    }

    @GetMapping("/delete")
    public void delete(@RequestParam Integer productId) {
        cartService.delete(productId);
    }

}
