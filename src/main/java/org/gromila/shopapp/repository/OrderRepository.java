package org.gromila.shopapp.repository;

import lombok.RequiredArgsConstructor;
import org.gromila.shopapp.entity.Order;
import org.gromila.shopapp.entity.User;
import org.gromila.shopapp.exception.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    public static final String SELECT_BY_ID = "SELECT o FROM Order o LEFT JOIN FETCH o.orderDetails LEFT JOIN FETCH o.payments WHERE o.id = :id AND o.user.id = :userId";
    public static final String SELECT_ALL = "SELECT o FROM Order o LEFT JOIN FETCH o.orderDetails LEFT JOIN FETCH o.payments WHERE o.user.id = :userId";
    private final SessionFactory sessionFactory;

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

    public Order findById(Long userId, Long id) {
        try (Session session = sessionFactory.openSession()) {
            Order order = session.createQuery(SELECT_BY_ID, Order.class)
                    .setParameter("id", id)
                    .setParameter("userId", userId)
                    .uniqueResult();
            if (order != null) {
                return order;
            } else {
                throw new HibernateException("Order is not found");
            }
        }
    }

    public List<Order> findAll(Long userId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(SELECT_ALL, Order.class)
                    .setParameter("userId", userId)
                    .getResultList();
        }
    }


    public void delete(Long userId, Long id) {
        Transaction tx = null;

        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            Order order = findById(userId, id);

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