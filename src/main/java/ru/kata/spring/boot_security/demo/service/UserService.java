package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {

    List<Role> listRoles();
    List<Role> listByRole(List<String> name);
    void save(User user);
    Set<User> setUsers();
    void delete(int id);
    void update(User user);
    User findById(int id);
    User getByUsername(String userName);

}