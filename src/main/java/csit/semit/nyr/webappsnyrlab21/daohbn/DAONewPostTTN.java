package csit.semit.nyr.webappsnyrlab21.daohbn;

import csit.semit.nyr.webappsnyrlab21.entity.NewPostTTN;
import csit.semit.nyr.webappsnyrlab21.hbnutils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class DAONewPostTTN {

//    public static List<NewPostTTN> getAllTTNs() {
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            return session.createQuery("from NewPostTTN", NewPostTTN.class).list();
//        }
//    }

    public static List<NewPostTTN> getAllTTNs() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from NewPostTTN", NewPostTTN.class).list();
        }
    }

    public static NewPostTTN getTTNById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(NewPostTTN.class, id);
        }
    }

    public static String insertTTN(NewPostTTN ttn) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(ttn);
            transaction.commit();
            return "TTN added successfully!";
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return "Error adding TTN.";
        }
    }

    public static String updateTTN(NewPostTTN ttn) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(ttn);
            transaction.commit();
            return "TTN updated successfully!";
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return "Error updating TTN.";
        }
    }

    public static String deleteTTN(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            NewPostTTN ttn = getTTNById(id);
            if (ttn != null) {
                session.remove(ttn);
                transaction.commit();
                return "TTN deleted successfully!";
            }
            return "TTN not found.";
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return "Error deleting TTN.";
        }
    }

    public List<NewPostTTN> getFilteredTTNs(String receiver, String manager, String numPoint, String kodTTN, String status) {
        StringBuilder queryBuilder = new StringBuilder("FROM NewPostTTN WHERE 1=1");

        if (receiver != null && !receiver.isEmpty()) {
            queryBuilder.append(" AND receiver.firstName LIKE :receiver");
        }
        if (manager != null && !manager.isEmpty()) {
            queryBuilder.append(" AND manager LIKE :manager");
        }
        if (numPoint != null && !numPoint.isEmpty()) {
            queryBuilder.append(" AND numPoint = :numPoint");
        }
        if (kodTTN != null && !kodTTN.isEmpty()) {
            queryBuilder.append(" AND kodTTN LIKE :kodTTN");
        }
        if (status != null && !status.isEmpty()) {
            queryBuilder.append(" AND status = :status");
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<NewPostTTN> query = session.createQuery(queryBuilder.toString(), NewPostTTN.class);

            if (receiver != null && !receiver.isEmpty()) {
                query.setParameter("receiver", "%" + receiver + "%");
            }
            if (manager != null && !manager.isEmpty()) {
                query.setParameter("manager", "%" + manager + "%");
            }
            if (numPoint != null && !numPoint.isEmpty()) {
                query.setParameter("numPoint", Integer.parseInt(numPoint));
            }
            if (kodTTN != null && !kodTTN.isEmpty()) {
                query.setParameter("kodTTN", "%" + kodTTN + "%");
            }
            if (status != null && !status.isEmpty()) {
                query.setParameter("status", status);
            }

            return query.getResultList();
        }
    }
}
