package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleDAOImplem implements RoleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean add(Role user) {
        entityManager.persist(user);
        return true;
    }

    @Override
    public Role getByIdRole(int id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public List<Role> getListRoles() {
        return entityManager.createQuery("select s from Role s", Role.class).getResultList();
    }

    @Override
    public Role getByName(String name) {
        return entityManager.createQuery("select u from Role u where u.role = :id", Role.class)
                .setParameter("id", name)
                .getResultList().stream().findAny().orElse(null);
    }

    @Override
    public List<Role> getListByName(List<String> name) {
        return  entityManager.createQuery("select u from Role u where u.role in (:id)", Role.class)
                .setParameter("id", name)
                .getResultList();
    }

    @Override
    public List<Role> getRolesListById(List<Long> roles) {
        TypedQuery<Role> query = entityManager.createQuery("select r from Role r where r.id in :role", Role.class);
        query.setParameter("role", roles);
        return new ArrayList<>(query.getResultList());
    }
}