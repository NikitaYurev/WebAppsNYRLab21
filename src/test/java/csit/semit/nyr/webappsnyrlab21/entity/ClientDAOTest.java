package csit.semit.nyr.webappsnyrlab21.entity;

import csit.semit.nyr.webappsnyrlab21.entity.Client;
import csit.semit.nyr.webappsnyrlab21.hibernate.HibernateUtil;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class ClientDAOTest {

    private static ClientDAO clientDAO;

    @BeforeAll
    public static void setUp() {
        clientDAO = new ClientDAO();
    }

    @BeforeEach
    public void clearDatabaseBeforeTest() {
        for (Client client : clientDAO.getAllList()) {
            clientDAO.delete(client);
        }
    }

    @Test
    public void testInsertClient() {
        Client client = new Client("John", "Doe", "Kyiv", "Kyiv", "+380123456789", "john.doe@example.com");
        boolean result = clientDAO.insert(client);
        assertTrue(result, "Client should be inserted successfully.");
    }

    @Test
    public void testFindById() {
        Client client = new Client("Jane", "Smith", "Lviv", "Lviv", "+380987654321", "jane.smith@example.com");
        clientDAO.insert(client);
        Client foundClient = clientDAO.findById(client.getId());
        assertNotNull(foundClient, "Client should be found by ID.");
        assertEquals(client.getFirstName(), foundClient.getFirstName());
    }

    @Test
    public void testUpdateClient() {
        Client client = new Client("Alice", "Brown", "Dnipro", "Dnipro", "+380111223344", "alice.brown@example.com");
        clientDAO.insert(client);

        client.setCity("Odesa");
        boolean result = clientDAO.update(client.getId(), client);
        assertTrue(result, "Client should be updated successfully.");

        Client updatedClient = clientDAO.findById(client.getId());
        assertEquals("Odesa", updatedClient.getCity(), "City should be updated to Odesa.");
    }

    @Test
    public void testDeleteClient() {
        Client client = new Client("Bob", "White", "Kharkiv", "Kharkiv", "+380555667788", "bob.white@example.com");
        clientDAO.insert(client);

        boolean result = clientDAO.delete(client);
        assertTrue(result, "Client should be deleted successfully.");

        Client deletedClient = clientDAO.findById(client.getId());
        assertNull(deletedClient, "Client should no longer exist in the database.");
    }

    @AfterEach
    public void clearDatabaseAfterTest() {
        for (Client client : clientDAO.getAllList()) {
            clientDAO.delete(client);
        }
    }

    @AfterAll
    public static void tearDown() {
        HibernateUtil.shutdown();
    }
}