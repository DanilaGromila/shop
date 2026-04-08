package org.gromila.shopapp.repository;

import lombok.RequiredArgsConstructor;
import org.gromila.shopapp.entity.Feedback;
import org.gromila.shopapp.entity.Item;
import org.gromila.shopapp.exception.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FeedbackRepository {
    public static final String SELECT_BY_ID = "SELECT f FROM Feedback f LEFT JOIN FETCH f.item i WHERE f.id = :id AND i.id = :itemId";
    public static final String SELECT_ALL = "SELECT f FROM Feedback f LEFT JOIN FETCH f.item i WHERE i.id = :itemId";
    private final SessionFactory sessionFactory;

    public Long create(Feedback feedback) {
        Transaction tx = null;

        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.save(feedback);
            session.getTransaction().commit();
            return feedback.getId();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            throw new HibernateException("Feedback is can not created");
        }
    }

    public Feedback findById(Long itemId, Long id) {
        try (Session session = sessionFactory.openSession()) {
            Feedback feedback = session.createQuery(SELECT_BY_ID, Feedback.class)
                    .setParameter("id", id)
                    .setParameter("itemId", itemId)
                    .uniqueResult();
            if (feedback != null) {
                return feedback;
            } else {
                throw new HibernateException("Feedback is not found");
            }
        }
    }

    public List<Feedback> findAll(Long itemId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(SELECT_ALL, Feedback.class)
                    .setParameter("itemId", itemId)
                    .getResultList();
        }
    }

    public void update(Long id, String newText) {
        Transaction tx = null;

        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            Feedback feedback = session.get(Feedback.class, id);

            if (feedback != null) {
                feedback.setText(newText);
                session.update(feedback);
            } else {
                throw new HibernateException("Feedback is not found");
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new HibernateException("Feedback is not updated");
        }
    }

    public void delete(Long itemId, Long id) {
        Transaction tx = null;

        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            Feedback feedback = findById(itemId, id);

            if (feedback != null) {
                session.delete(feedback);
            } else {
                throw new HibernateException("Feedback is not found");
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new HibernateException("Feedback is not deleted");
        }
    }
}
