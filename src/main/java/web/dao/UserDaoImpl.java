package web.dao;


import org.springframework.stereotype.Repository;

import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;
    public UserDaoImpl() {}

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteUserById(int id) {
        User userFromDB = entityManager.find(User.class, id);
        entityManager.remove(userFromDB);
    }

    @Override
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        return entityManager
                .createQuery("FROM User", User.class)
                .getResultList();
    }

    @Override
    public void updateUser(User user, int id) {
        entityManager.merge(user);
    }

    @Override
    public void createUser(User user) {
        entityManager.persist(user);
    }

}
