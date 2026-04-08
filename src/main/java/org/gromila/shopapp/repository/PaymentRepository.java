package org.gromila.shopapp.repository;

import lombok.RequiredArgsConstructor;
import org.gromila.shopapp.entity.Payment;
import org.gromila.shopapp.exception.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PaymentRepository {
    public static final String SELECT_BY_ID = "SELECT p FROM Payment p LEFT JOIN FETCH p.order o WHERE p.id = :id AND o.id = :orderId AND o.user.id = :userId";
    public static final String SELECT_ALL = "SELECT p FROM Payment p LEFT JOIN FETCH p.order o WHERE o.id = :orderId AND o.user.id = :userId";
    private final SessionFactory sessionFactory;

    public Long create(Payment payment) {
        Transaction tx = null;

        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.save(payment);
            session.getTransaction().commit();
            return payment.getId();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            throw new HibernateException("Payment is can not created");
        }
    }

    public Payment findById(Long userId, Long orderId, Long id) {
        try (Session session = sessionFactory.openSession()) {
            Payment payment = session.createQuery(SELECT_BY_ID, Payment.class)
                    .setParameter("id", id)
                    .setParameter("orderId", orderId)
                    .setParameter("userId", userId)
                    .uniqueResult();
            if (payment != null) {
                return payment;
            } else {
                throw new HibernateException("Payment is not found");
            }
        }
    }

    public List<Payment> findAll(Long userId, Long orderId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(SELECT_ALL, Payment.class)
                    .setParameter("orderId", orderId)
                    .setParameter("userId", userId)
                    .getResultList();
        }
    }

    public void update(Long id, String paymentStatus) {
        Transaction tx = null;

        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            Payment payment = session.get(Payment.class, id);

            if (payment != null) {
                payment.setPaymentStatus(paymentStatus);
                session.update(payment);
            } else {
                throw new HibernateException("Payment is not found");
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new HibernateException("Payment is not updated");
        }
    }

    public void delete(Long userId, Long orderId, Long id) {
        Transaction tx = null;

        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            Payment payment = findById(userId, orderId, id);

            if (payment != null) {
                session.delete(payment);
            } else {
                throw new HibernateException("Payment is not found");
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new HibernateException("Payment is not deleted");
        }
    }
}

