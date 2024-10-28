package csit.semit.nyr.webappsnyrlab21.daohbn;

import csit.semit.nyr.webappsnyrlab21.daohbn.DAOClients;
import csit.semit.nyr.webappsnyrlab21.entity.Client;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DAOClientsTest {

    private Client testClient;

    @BeforeEach
    public void setUp() {
        // Initialize a new client instance
        testClient = new Client(null, "John", "Doe", "Region", "City", "123456789", "johndoe@example.com");
    }

    @Test
    public void testCreateFindUpdateDeleteClient() {
        // 1) Insert the client into the database
        String result = DAOClients.insertClient(testClient);
        assertFalse(result.contains("Error"), "Failed to insert client");

        // 2) Find the client by key fields
        Client foundClient = DAOClients.getClientById(testClient.getId());
        assertNotNull(foundClient, "Client not found");
        assertEquals("John", foundClient.getFirstName());

        // 3) Update the client
        foundClient.setFirstName("Jane");
        String updateResult = DAOClients.updateClient(foundClient, foundClient.getId());
        assertFalse(updateResult.contains("Error"), "Failed to update client");

        // Verify the update
        Client updatedClient = DAOClients.getClientById(foundClient.getId());
        assertEquals("Jane", updatedClient.getFirstName(), "Client update not successful");

        // 4) Delete the client
        String deleteResult = DAOClients.deleteClient(foundClient.getId());
        assertFalse(deleteResult.contains("Error"), "Failed to delete client");

        // Verify deletion
        assertNull(DAOClients.getClientById(foundClient.getId()), "Client not deleted");
    }

    @AfterEach
    public void tearDown() {
        // Clean up any leftover test data to ensure no side effects
        if (testClient.getId() != null) {
            DAOClients.deleteClient(testClient.getId());
        }
    }
}
