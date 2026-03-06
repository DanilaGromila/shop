package org.gromila.shopapp.repository;

import lombok.RequiredArgsConstructor;
import org.gromila.shopapp.entity.OrderDetails;
import org.gromila.shopapp.exception.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

@RequiredArgsConstructor
public class OrderDetailsRepository {
    public static final String SELECT_BY_ID = "SELECT od FROM OrderDetails od LEFT JOIN FETCH od.order LEFT JOIN FETCH od.item WHERE od.id = :id";
    public static final String SELECT_ALL = "SELECT od FROM OrderDetails od LEFT JOIN FETCH od.order LEFT JOIN FETCH od.item";
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

    public OrderDetails findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            OrderDetails orderDetails = session.createQuery(SELECT_BY_ID, OrderDetails.class)
                    .setParameter("id", id)
                    .uniqueResult();
            if (orderDetails != null) {
                return orderDetails;
            } else {
                throw new HibernateException("Order details is not found");
            }
        }
    }

    public List<OrderDetails> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(SELECT_ALL, OrderDetails.class)
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

    public void delete(Long id) {
        Transaction tx = null;

        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            OrderDetails orderDetails = session.get(OrderDetails.class, id);

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