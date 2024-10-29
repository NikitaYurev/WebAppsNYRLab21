package csit.semit.nyr.webappsnyrlab21.daohbn;

import csit.semit.nyr.webappsnyrlab21.entity.Client;
import csit.semit.nyr.webappsnyrlab21.hbnutils.HibernateUtil;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
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

    public List<Client> getFilteredClients(String firstName, String lastName, String region, String city, String phone, String email) {
        List<Client> clients = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("FROM Client WHERE 1=1");

        if (firstName != null && !firstName.isEmpty()) {
            queryBuilder.append(" AND firstName LIKE :firstName");
        }
        if (lastName != null && !lastName.isEmpty()) {
            queryBuilder.append(" AND secondName LIKE :secondName");
        }
        if (region != null && !region.isEmpty()) {
            queryBuilder.append(" AND region LIKE :region");
        }
        if (city != null && !city.isEmpty()) {
            queryBuilder.append(" AND city LIKE :city");
        }
        if (phone != null && !phone.isEmpty()) {
            queryBuilder.append(" AND phone LIKE :phone");
        }
        if (email != null && !email.isEmpty()) {
            queryBuilder.append(" AND email LIKE :email");
        }

        // Execute query with set parameters
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Client> query = session.createQuery(queryBuilder.toString(), Client.class);

            if (firstName != null && !firstName.isEmpty()) {
                query.setParameter("firstName", "%" + firstName + "%");
            }
            if (lastName != null && !lastName.isEmpty()) {
                query.setParameter("secondName", "%" + lastName + "%");
            }
            if (region != null && !region.isEmpty()) {
                query.setParameter("region", "%" + region + "%");
            }
            if (city != null && !city.isEmpty()) {
                query.setParameter("city", "%" + city + "%");
            }
            if (phone != null && !phone.isEmpty()) {
                query.setParameter("phone", "%" + phone + "%");
            }
            if (email != null && !email.isEmpty()) {
                query.setParameter("email", "%" + email + "%");
            }

            clients = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return clients;
    }
}
