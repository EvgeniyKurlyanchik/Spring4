package ru.kurlyanchik.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kurlyanchik.model.Role;
import ru.kurlyanchik.repositories.RoleRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
