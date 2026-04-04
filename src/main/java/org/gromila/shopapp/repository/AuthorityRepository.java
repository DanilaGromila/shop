package org.gromila.shopapp.repository;

import lombok.RequiredArgsConstructor;
import org.gromila.shopapp.entity.Authority;
import org.gromila.shopapp.exception.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AuthorityRepository {
    public static final String SELECT_BY_ID = "SELECT a FROM Authority a LEFT JOIN FETCH a.roles WHERE a.id = :id";
    public static final String SELECT_ALL = "SELECT a FROM Authority a LEFT JOIN FETCH a.roles";
    private final SessionFactory sessionFactory;

    public Long create(String name) {
        Transaction tx = null;

        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Authority authority = new Authority();
            authority.setName(name);
            session.save(authority);
            session.getTransaction().commit();
            return authority.getId();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            throw new HibernateException("Authority is can not created");
        }
    }

    public Authority findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Authority authority = session.createQuery(SELECT_BY_ID, Authority.class)
                    .setParameter("id", id)
                    .uniqueResult();
            if (authority != null) {
                return authority;
            } else {
                throw new HibernateException("Authority is not found");
            }
        }
    }

    public List<Authority> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(SELECT_ALL, Authority.class)
                    .getResultList();
        }
    }

    public void update(Long id, String newName) {
        Transaction tx = null;

        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            Authority authority = session.get(Authority.class, id);

            if (authority != null) {
                authority.setName(newName);
                session.update(authority);
            } else {
                throw new HibernateException("Authority is not found");
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new HibernateException("Authority is not updated");
        }
    }

    public void delete(Long id) {
        Transaction tx = null;

        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            Authority authority = session.get(Authority.class, id);

            if (authority != null) {
                session.delete(authority);
            } else {
                throw new HibernateException("Authority is not found");
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new HibernateException("Authority is not deleted");
        }
    }
}
