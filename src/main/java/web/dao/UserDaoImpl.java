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

//    @Autowired
//    private SessionFactory sessionFactory;
//
//
//    public UserDaoImpl() {}
//
//
//    @Override
//    public void addUser(User user) {
//        sessionFactory.getCurrentSession().save(user);
//    }
//
//    @Override
//    public void deleteUserById(int id) {
//        User userFromDB = sessionFactory.getCurrentSession().get(User.class, id);
//        sessionFactory.getCurrentSession().delete(userFromDB);
//    }
//
//    @Override
//    public User getUserById(int id) {
//        return sessionFactory.getCurrentSession().get(User.class, id);
//    }
//
//    @Override
//    @SuppressWarnings("unchecked")
//    public List<User> getAllUsers() {
//        return sessionFactory
//                .getCurrentSession()
//                .createQuery("from User")
//                .getResultList();
//    }
//
//    @Override
//    public void updateUser(User user, int id) {
//        User userFromDB = sessionFactory.getCurrentSession().get(User.class, id);
//        userFromDB.setName(user.getName());
//        userFromDB.setLastName(user.getLastName());
//        userFromDB.setDepartment(user.getDepartment());
//        userFromDB.setSalary(user.getSalary());
//        sessionFactory.getCurrentSession().save(userFromDB);
//    }
//
//    @Override
//    public void createUser(User user) {
//        sessionFactory.getCurrentSession().save(user);
//    }
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
