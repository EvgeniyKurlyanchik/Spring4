package ru.kurlyanchik.service;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.kurlyanchik.model.QProduct;
import ru.kurlyanchik.model.dto.ProductDto;
import ru.kurlyanchik.model.mapper.ProductDtoMapper;
import ru.kurlyanchik.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ProductService {

   private  final ProductRepository productRepository;
    private final ProductDtoMapper mapper;


   /* public List<ProductDto> findAllByFilter(String nameFilter, String priceFilter ){
        QProduct product = QProduct.product;
        BooleanBuilder predicate = new BooleanBuilder();
        if (nameFilter != null && !nameFilter.isBlank()){
            predicate.and(product.title.contains(nameFilter.trim()));
        }
        if(priceFilter!= null && !priceFilter.isBlank()){
            predicate.and(product.price.like(priceFilter.trim()));
        }

        return StreamSupport.stream(productRepository.findAll(predicate).spliterator(),true)
                .map(mapper::map)
                .collect(Collectors.toList());

    }*/
    public Optional<ProductDto> findProductById(Long id){
        return productRepository.findById(id).map(mapper::map);
    }
/*    public static ProductDto productToDto(Product product){
    return new   ProductDto(product.getId(),product.getTitle(),product.getPrice());

    }*/
   /* public void save(ProductDto productDto){
       productRepository.save(mapper.map(productDto));
    }*/

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
    public Page<ProductDto> findAllByFilter(String nameFilter, String priceFilter, int page, int size) {
        nameFilter = nameFilter == null || nameFilter.isBlank() ? null : "%" + nameFilter.trim() + "%";
        priceFilter = priceFilter == null ||priceFilter.isBlank() ? null : "%" + priceFilter.trim() + "%";
        return productRepository.productsByFilter(nameFilter, priceFilter, PageRequest.of(page, size))
                .map(mapper::map);
    }

   /* public Optional<ProductDto> findProductById(Long id){
       return productRepository.findById(id).map(mapper::map);}*/
   /* priceFilter = priceFilter == null || priceFilter.describeConstable().isEmpty() ? null :  priceFilter ;*/
   public void save(ProductDto productDto) {
        productRepository.save(mapper.map(productDto));
    }
   /*public ProductDto save (Product product){
       return  mapper.map(productRepository.save(new Product(productDto.getId(),productDto.getTitle(),productDto.getPrice(),productDto.getPassword())));*/


}
