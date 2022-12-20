package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.RoleDAO;
import ru.kata.spring.boot_security.demo.models.Role;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RoleServiceImplem  implements RoleService{
    private final RoleDAO roleDAO;

    @Autowired
    public RoleServiceImplem(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    @Transactional
    public void add(Role role) {
        roleDAO.add(role);
    }

    @Override
    public List<Role> ListRoles() {
        return roleDAO.getListRoles();
    }

    @Override
    public List<Role> getRolesListById(List<Long> id) {
        return roleDAO.getRolesListById(id);
    }

    @Override
    public Role getRoleById(int id) {
        return roleDAO.getByIdRole(id);
    }
}
