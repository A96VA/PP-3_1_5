package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDAO;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImplem implements UserService {

    private final UserDAO userDAO;
    private final RoleDAO roleDAO;

    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Autowired
    public UserServiceImplem(RoleDAO roleDAO, UserDAO userDAO) {
        this.roleDAO = roleDAO;
        this.userDAO = userDAO;
    }

    @Override
    public List<Role> listRoles() {
        return roleDAO.getListRoles();
    }

    @Override
    public List<Role> listByRole(List<String> name) {
        return roleDAO.getListByName(name);
    }

    public Set<Role> setByRole(List<String> name) {
        Set<Role> rolesSet = new HashSet<>(roleDAO.getListByName(name));
        return rolesSet;
    }


    @Override
    @Transactional
    public void save(User user) {
        User oldUser = getByUsername(user.getUsername());
        if (oldUser != null) {
            return;
        }
        user.setPassword(bCryptPasswordEncoder().encode(user.getPassword()));
        userDAO.save(user);
    }

    @Override
    public Set<User> setUsers() {
        return userDAO.listUsers();
    }

    @Override
    public void delete(int id) {
        userDAO.delete(id);
    }

    @Override
    @Transactional
    public void update(User user) {
        User oldUser = findById(user.getId());
        if (oldUser.getPassword().equals(user.getPassword()) || "".equals(user.getPassword())) {
            user.setPassword(oldUser.getPassword());
        } else {
            user.setPassword(bCryptPasswordEncoder().encode(user.getPassword()));
        }
        userDAO.update(user);
    }
    @Override
    public User findById(int id) {
        return userDAO.findById(id);
    }
    @Override
    public User getByUsername(String username) {
        return userDAO.findByName(username);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        }
        return user;
    }
}