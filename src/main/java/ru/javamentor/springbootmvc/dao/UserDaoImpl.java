package ru.javamentor.springbootmvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javamentor.springbootmvc.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
//        return entityManager.createNativeQuery("select * from users", User.class).getResultList();
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public User show(int id) {
        TypedQuery<User> query = entityManager.createQuery(
                "select u from User u where u.id = :id", User.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void update(int id, User updateUser) {
        User user = show(id);
        user.setEmail(updateUser.getEmail());
        user.setName(updateUser.getName());
        user.setLastName(updateUser.getLastName());
        entityManager.merge(user);
    }

    @Override
    public void delete(int id) {
        User user = show(id);
        entityManager.remove(user);
    }

    @Override
    public User isExistById(User user) {
        if(entityManager.contains(user)) {
            entityManager.remove(user);
        } else {
            entityManager.remove(entityManager.merge(user));
        }
        return user;
    }
}