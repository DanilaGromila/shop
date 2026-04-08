package org.gromila.shopapp.repository;

import lombok.RequiredArgsConstructor;
import org.gromila.shopapp.entity.Authority;
import org.gromila.shopapp.entity.Role;
import org.gromila.shopapp.exception.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RoleRepository {
    public static final String SELECT_BY_ID = "SELECT r FROM Role r LEFT JOIN FETCH r.users u LEFT JOIN FETCH r.authorities WHERE r.id = :id";
    public static final String SELECT_ALL = "SELECT r FROM Role r LEFT JOIN FETCH r.users u LEFT JOIN FETCH r.authorities";
    private final SessionFactory sessionFactory;

    public Long create(String name) {
        Transaction tx = null;

        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Role role = new Role();
            role.setName(name);
            session.save(role);
            session.getTransaction().commit();
            return role.getId();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            throw new HibernateException("Role is can not created");
        }
    }

    public Role findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Role role = session.createQuery(SELECT_BY_ID, Role.class)
                    .setParameter("id", id)
                    .uniqueResult();
            if (role != null) {
                return role;
            } else {
                throw new HibernateException("Role is not found");
            }
        }
    }

    public List<Role> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(SELECT_ALL, Role.class)
                    .getResultList();
        }
    }

    public void update(Long id, String newName) {
        Transaction tx = null;

        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            Role role = session.get(Role.class, id);

            if (role != null) {
                role.setName(newName);
                session.update(role);
            } else {
                throw new HibernateException("Role is not found");
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new HibernateException("Role is not updated");
        }
    }

    public void addAuthority(Role role, Long authorityId) {
        Transaction tx = null;

        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Authority authority = new Authority();
            authority.setId(authorityId);

            role.getAuthorities().add(authority);
            session.update(role);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new HibernateException("User is not deleted");
        }
    }

    public void delete(Long id) {
        Transaction tx = null;

        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            Role role = findById(id);

            if (role != null) {
                session.delete(role);
            } else {
                throw new HibernateException("Role is not found");
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new HibernateException("Role is not deleted");
        }
    }
}

