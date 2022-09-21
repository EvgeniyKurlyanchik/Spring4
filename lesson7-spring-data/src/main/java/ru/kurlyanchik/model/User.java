package ru.kurlyanchik.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;

@Entity
@Data
@Table(name ="users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, length = 1024)
    private String password;
    @JsonIgnore
    private String matchingPassword;
    @Email
    @Column(name = "email",length = 1024)
    private String email;

    @ManyToMany
    private Set<Role> roles;
    public User(String username) {
        this.username = username;
    }


}
