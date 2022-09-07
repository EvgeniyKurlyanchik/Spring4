package ru.kurlyanchik.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.kurlyanchik.model.dto.ProductDto;
import ru.kurlyanchik.model.mapper.ProductDtoMapper;
import ru.kurlyanchik.repositories.ProductRepository;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductDtoMapper mapper;

    public Optional<ProductDto> findProductById(Long id) {
        return productRepository.findById(id).map(mapper::map);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    public Page<ProductDto> findAllByFilter(String nameFilter, String priceFilter, int page, int size) {
        nameFilter = nameFilter == null || nameFilter.isBlank() ? null : "%" + nameFilter.trim() + "%";
        priceFilter = priceFilter == null || priceFilter.isBlank() ? null : "%" + priceFilter.trim() + "%";
        return productRepository.productsByFilter(nameFilter, priceFilter, PageRequest.of(page, size))
                .map(mapper::map);
    }

    public void save(ProductDto productDto) {
        productRepository.save(mapper.map(productDto));
    }

}
