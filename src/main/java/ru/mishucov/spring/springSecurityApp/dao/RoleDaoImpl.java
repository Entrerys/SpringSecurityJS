package ru.mishucov.spring.springSecurityApp.dao;

import org.springframework.stereotype.Repository;
import ru.mishucov.spring.springSecurityApp.model.Role;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void addRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public List<Role> getRoles() {
        return entityManager.createQuery("from Role", Role.class).getResultList();
    }

    @Override
    public Role getRoleByName(String name) {
        return entityManager.createQuery("select u from Role u where u.name = :id", Role.class)
                .setParameter("id", name)
                .getResultList().stream().findAny().orElse(null);
    }

    public List<Role> getListByName(List<String> name) {
        return entityManager.createQuery("select u from Role u where u.name in (:id)", Role.class)
                .setParameter("id", name)
                .getResultList();
    }


}
