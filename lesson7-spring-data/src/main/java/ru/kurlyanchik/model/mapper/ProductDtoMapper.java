package ru.kurlyanchik.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import ru.kurlyanchik.model.Product;
import ru.kurlyanchik.model.QProduct;
import ru.kurlyanchik.model.dto.ProductDto;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ProductDtoMapper {
    @Mapping(target = "password", ignore = true)
    ProductDto map(Product product);

    @Mapping(target = "id", ignore = true)
    Product map(ProductDto productDto);
}
