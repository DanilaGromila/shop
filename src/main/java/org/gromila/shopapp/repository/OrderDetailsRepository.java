package org.gromila.shopapp.repository;

import lombok.RequiredArgsConstructor;
import org.gromila.shopapp.entity.OrderDetails;
import org.gromila.shopapp.exception.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderDetailsRepository {
    public static final String SELECT_BY_ID = "SELECT od FROM OrderDetails od LEFT JOIN FETCH od.order o LEFT JOIN FETCH od.item WHERE od.id = :id AND o.id = :orderId AND o.user.id =:userId";
    public static final String SELECT_ALL = "SELECT od FROM OrderDetails od LEFT JOIN FETCH od.order o LEFT JOIN FETCH od.item WHERE o.id = :orderId AND o.user.id =:userId";
    private final SessionFactory sessionFactory;

    public Long create(OrderDetails orderDetails) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.save(orderDetails);
            session.getTransaction().commit();
            return orderDetails.getId();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            throw new HibernateException("Order details is can not created");
        }
    }

    public OrderDetails findById(Long userId, Long orderId, Long id) {
        try (Session session = sessionFactory.openSession()) {
            OrderDetails orderDetails = session.createQuery(SELECT_BY_ID, OrderDetails.class)
                    .setParameter("id", id)
                    .setParameter("orderId", orderId)
                    .setParameter("userId", userId)
                    .uniqueResult();
            if (orderDetails != null) {
                return orderDetails;
            } else {
                throw new HibernateException("Order details is not found");
            }
        }
    }

    public List<OrderDetails> findAll(Long userId, Long orderId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(SELECT_ALL, OrderDetails.class)
                    .setParameter("orderId", orderId)
                    .setParameter("userId", userId)
                    .getResultList();
        }
    }

    public void update(Long id, Integer quantity) {
        Transaction tx = null;

        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            OrderDetails orderDetails = session.get(OrderDetails.class, id);

            if (orderDetails != null) {

                orderDetails.setQuantity(quantity);
                session.update(orderDetails);
            } else {
                throw new HibernateException("Order details is not found");
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new HibernateException("Order details is not updated");
        }
    }

    public void delete(Long userId, Long orderId, Long id) {
        Transaction tx = null;

        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            OrderDetails orderDetails = findById(userId, orderId, id);

            if (orderDetails != null) {
                session.delete(orderDetails);
            } else {
                throw new HibernateException("Order details is not found");
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new HibernateException("Order details is not deleted");
        }
    }
}