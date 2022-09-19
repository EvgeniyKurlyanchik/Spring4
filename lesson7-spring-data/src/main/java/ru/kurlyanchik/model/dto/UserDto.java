package ru.kurlyanchik.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.kurlyanchik.model.Role;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;

    @NotBlank(message = "can not be empty!!!")
    private String username;

    @NotBlank
    @Email
    private String email;


    private String password;

    @JsonIgnore
    private String matchingPassword;

    private Set<Role> roles;

    public UserDto(Long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
