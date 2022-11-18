package ru.mishucov.spring.SpringSecurityApp.Dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.mishucov.spring.SpringSecurityApp.Model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

@Repository
public class UsersDaoImpl implements UsersDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void removeUserById(int id) {
        entityManager.remove(getUserById(id));
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = entityManager.createQuery("from User", User.class).getResultList();
        return list;
    }

    @Override
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    @Override
    public void updateUser(int id, User user) {
        entityManager.merge(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return entityManager.createQuery("select u from User u  where u.username = :username", User.class)
                .setParameter("username", username)
                .getResultList().stream().findAny().orElse(null);
    }

    @Override
    public User getUserByEmail(String email) {
        return entityManager.createQuery("select u from User u  where u.email = :email", User.class)
                .setParameter("email", email)
                .getResultList().stream().findAny().orElse(null);
    }

    @Transactional
    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

}
