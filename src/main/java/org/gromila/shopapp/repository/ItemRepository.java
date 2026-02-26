package org.gromila.shopapp.repository;

import org.gromila.shopapp.entity.Item;
import org.gromila.shopapp.exception.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ItemRepository {
    public static final String SELECT_BY_ID = "SELECT i FROM Item i LEFT JOIN FETCH i.feedbacks WHERE i.id = :id";
    public static final String SELECT_ALL = "SELECT i FROM Item i LEFT JOIN FETCH i.feedbacks";
    private final SessionFactory sessionFactory;

    public ItemRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Long create(Item item) {
        Transaction tx = null;

        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
            return item.getId();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            throw new HibernateException("Item is can not created");
        }
    }

    public Item findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Item item = session.createQuery(SELECT_BY_ID, Item.class)
                    .setParameter("id", id)
                    .uniqueResult();
            if (item != null) {
                return item;
            } else {
                throw new HibernateException("Item is not found");
            }
        }
    }

    public List<Item> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(SELECT_ALL, Item.class)
                    .getResultList();
        }
    }

    public void update(Long id, String newName) {
        Transaction tx = null;

        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            Item item = session.get(Item.class, id);

            if (item != null) {
                item.setName(newName);
                session.update(item);
            } else {
                throw new HibernateException("Item is not found");
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new HibernateException("Item is not updated");
        }
    }

    public void delete(Long id) {
        Transaction tx = null;

        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            Item item = session.get(Item.class, id);

            if (item != null) {
                session.delete(item);
            } else {
                throw new HibernateException("Item is not found");
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new HibernateException("Item is not deleted");
        }
    }
}
