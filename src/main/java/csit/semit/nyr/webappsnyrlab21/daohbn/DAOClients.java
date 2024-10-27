package csit.semit.nyr.webappsnyrlab21.daohbn;

import csit.semit.nyr.webappsnyrlab21.entity.Client;
import csit.semit.nyr.webappsnyrlab21.hbnutils.HibernateUtil;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class DAOClients {

    public static List<Client> getAllClients() {
        List<Client> clients = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Client> cq = cb.createQuery(Client.class);
            Root<Client> root = cq.from(Client.class);
            cq.select(root);
            TypedQuery<Client> query = session.createQuery(cq);
            clients = query.getResultList();
        }
        return clients;
    }

    public static Client getClientById(Long id) {
        Client client = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            client = session.find(Client.class, id);
        }
        return client;
    }

    public static String insertClient(Client client) {
        String message = "Client added successfully!";
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(client);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            message = "Error adding client.";
        }
        return message;
    }

    public static String updateClient(Client client, Long id) {
        String message = "Client updated successfully!";
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Client existingClient = getClientById(id);
            if (existingClient != null) {
                transaction = session.beginTransaction();
                client.setId(id); // Retain ID for the update
                session.merge(client);
                transaction.commit();
            } else {
                message = "Client not found.";
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            message = "Error updating client.";
        }
        return message;
    }

    public static String deleteClient(Long id) {
        String message = "Client deleted successfully!";
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Client client = getClientById(id);
            if (client != null) {
                transaction = session.beginTransaction();
                session.remove(client);
                transaction.commit();
            } else {
                message = "Client not found.";
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            message = "Error deleting client.";
        }
        return message;
    }
}
