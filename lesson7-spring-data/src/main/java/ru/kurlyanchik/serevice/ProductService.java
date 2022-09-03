package ru.kurlyanchik.serevice;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import ru.kurlyanchik.model.Product;
import ru.kurlyanchik.model.dto.ProductDto;
import ru.kurlyanchik.model.mapper.ProductDtoMapper;
import ru.kurlyanchik.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private ProductDtoMapper mapper;


    @Query(value = """
            select * from products p
            where (:nameFilter is null or p.title like :nameFilter)
            and (:priceFilter is null or p.price like :priceFilter)
            """,
            nativeQuery = true)
    public List<Product> productsByFilter(String nameFilter, String priceFilter) {
        return null;
    }

    public Optional<Optional<ProductDto>> findProductById(Long id){
       return Optional.ofNullable(productRepository.findById(id).map(mapper::map));}

  /*  public void save(ProductDto dto) {
        productRepository.save(mapper.map(dto));
    }*/
    public ProductDto save (ProductDto productDto){
       return  mapper.map(productRepository.save(new Product(productDto.getId(),productDto.getTitle(),productDto.getPrice(),productDto.getPassword())));
    }
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
 /* countQuery = """
             select count(*) from products p
             where (:nameFilter is null or p.title like :nameFilter)
             and (:priceFilter is null or p.pricelike :priceFilter)
             """,*/