package csit.semit.nyr.webappsnyrlab21.daohbn;

import csit.semit.nyr.webappsnyrlab21.entity.NewPostTTN;
import csit.semit.nyr.webappsnyrlab21.hbnutils.HibernateUtil;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class DAONewPostTTN {

    public static List<NewPostTTN> getAllTTNs() {
        List<NewPostTTN> ttns = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<NewPostTTN> cq = cb.createQuery(NewPostTTN.class);
            Root<NewPostTTN> root = cq.from(NewPostTTN.class);
            cq.select(root);
            TypedQuery<NewPostTTN> query = session.createQuery(cq);
            ttns = query.getResultList();
        }
        return ttns;
    }

    public static NewPostTTN getTTNById(Long id) {
        NewPostTTN ttn = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            ttn = session.find(NewPostTTN.class, id);
        }
        return ttn;
    }

    public static String insertTTN(NewPostTTN ttn) {
        String message = "TTN added successfully!";
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(ttn);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            message = "Error adding TTN.";
        }
        return message;
    }

    public static String updateTTN(NewPostTTN ttn, Long id) {
        String message = "TTN updated successfully!";
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            NewPostTTN existingTTN = getTTNById(id);
            if (existingTTN != null) {
                transaction = session.beginTransaction();
                ttn.setId(id); // Retain ID for the update
                session.merge(ttn);
                transaction.commit();
            } else {
                message = "TTN not found.";
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            message = "Error updating TTN.";
        }
        return message;
    }

    public static String deleteTTN(Long id) {
        String message = "TTN deleted successfully!";
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            NewPostTTN ttn = getTTNById(id);
            if (ttn != null) {
                transaction = session.beginTransaction();
                session.remove(ttn);
                transaction.commit();
            } else {
                message = "TTN not found.";
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            message = "Error deleting TTN.";
        }
        return message;
    }
}
