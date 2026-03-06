package org.gromila.shopapp.repository;

import org.gromila.shopapp.entity.Order;
import org.gromila.shopapp.entity.User;
import org.gromila.shopapp.exception.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class OrderRepository {
    public static final String SELECT_ALL = "SELECT o FROM Order o LEFT JOIN FETCH o.orderDetails";
    public static final String SELECT_BY_ID = "SELECT o FROM Order o LEFT JOIN FETCH o.orderDetails WHERE o.id = :id";
    private final SessionFactory sessionFactory;

    public OrderRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Long create(Long userId) throws HibernateException {
        Transaction tx = null;

        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            User user = new User();
            user.setId(userId);

            Order order = new Order();
            order.setUser(user);
            session.save(order);

            session.getTransaction().commit();// подтвержжаем
            return order.getId();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            throw new HibernateException("User is can not created");
        }
    }

    public Order findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Order order = session.createQuery(SELECT_BY_ID, Order.class)
                    .setParameter("id", id)
                    .uniqueResult();
            if (order != null) {
                return order;
            } else {
                throw new HibernateException("Order is not found");
            }
        }
    }

    public List<Order> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(SELECT_ALL, Order.class)
                    .getResultList();
        }
    }


    public void delete(Long id) {
        Transaction tx = null;

        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            Order order = session.get(Order.class, id);

            if (order != null) {
                session.delete(order);
            } else {
                throw new HibernateException("Order is not found");
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new HibernateException("Order is not deleted");
        }
    }
}