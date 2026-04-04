package org.gromila.shopapp.repository;

import lombok.RequiredArgsConstructor;
import org.gromila.shopapp.entity.Role;
import org.gromila.shopapp.entity.User;
import org.gromila.shopapp.exception.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    public static final String SELECT_BY_ID = "SELECT u FROM User u LEFT JOIN FETCH u.orders LEFT JOIN FETCH u.roles WHERE u.id = :id";
    public static final String SELECT_ALL = "SELECT u FROM User u LEFT JOIN FETCH u.orders LEFT JOIN FETCH u.roles";
    private final SessionFactory sessionFactory;

    public Long create(User user) {
        Transaction tx = null;

        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            return user.getId();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            throw new HibernateException("User is can not created");
        }
    }

    public User findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            User user = session.createQuery(SELECT_BY_ID, User.class)
                    .setParameter("id", id)
                    .uniqueResult();
            if (user != null) {
                return user;
            } else {
                throw new HibernateException("User is not found");
            }
        }
    }

    public List<User> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(SELECT_ALL, User.class)
                    .getResultList();
        }
    }

    public void update(Long id, String newName, String newSurname, String newLogin, String newPassword) {
        Transaction tx = null;

        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            User user = session.get(User.class, id);

            if (user != null) {
                user.setName(newName);
                user.setSurname(newSurname);
                user.setLogin(newLogin);
                user.setPassword(newPassword);

                session.update(user);
            } else {
                throw new HibernateException("User is not found");
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new HibernateException("User is not updated");
        }
    }

    public void addRole(User user, Long roleId) {
        Transaction tx = null;

        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Role role = new Role();
            role.setId(roleId);

            user.getRoles().add(role);
            session.update(user);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new HibernateException("Role is not added");
        }
    }

    public void delete(Long id) {
        Transaction tx = null;

        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            User user = session.get(User.class, id);

            if (user != null) {
                session.delete(user);
            } else {
                throw new HibernateException("User is not found");
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new HibernateException("User is not deleted");
        }
    }
}