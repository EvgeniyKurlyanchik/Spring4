package ru.kurlyanchik.model.mapper;

import org.mapstruct.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kurlyanchik.model.User;
import ru.kurlyanchik.model.dto.UserDto;


@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserDtoMapper {

    @Mapping(target = "password", ignore = true)
<<<<<<< HEAD
    UserDto map(UserDto user);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "password", target = "password", qualifiedByName = "encode")
    User map(UserDto dto,@Context PasswordEncoder encoder);
=======
    UserDto map(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "password", target = "password", qualifiedByName = "encode")
    User map(UserDto dto, @Context PasswordEncoder encoder);
>>>>>>> origin/lesson12

    @Named("encode")
    default String encode(String password, @Context PasswordEncoder encoder) {
        return encoder.encode(password);
    }

}
