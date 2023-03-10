package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;

public interface RoleService {

    void add(Role role);
    List<Role> ListRoles();
    List<Role> getRolesListById(List<Long> id);
    Role getRoleById(int id);
}
