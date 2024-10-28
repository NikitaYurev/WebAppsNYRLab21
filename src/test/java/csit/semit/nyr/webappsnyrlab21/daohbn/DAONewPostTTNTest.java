package csit.semit.nyr.webappsnyrlab21.daohbn;

import csit.semit.nyr.webappsnyrlab21.daohbn.DAONewPostTTN;
import csit.semit.nyr.webappsnyrlab21.daohbn.DAOClients;
import csit.semit.nyr.webappsnyrlab21.entity.Client;
import csit.semit.nyr.webappsnyrlab21.entity.NewPostTTN;
import csit.semit.nyr.webappsnyrlab21.entity.NewPostTTN.DeliveryStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class DAONewPostTTNTest {

    private Client testClient;
    private NewPostTTN testTTN;

    @BeforeEach
    public void setUp() {
        // Set up a client required for the TTN entry
        testClient = new Client(null, "John", "Doe", "Region", "City", "123456789", "johndoe@example.com");
        DAOClients.insertClient(testClient);

        // Initialize a new NewPostTTN instance
        testTTN = new NewPostTTN(null, testClient, "Manager", 1, "ABC123", LocalDateTime.now(), DeliveryStatus.SENT);
    }

    @Test
    public void testCreateFindUpdateDeleteTTN() {
        // 1) Insert the TTN into the database
        String result = DAONewPostTTN.insertTTN(testTTN);
        assertFalse(result.contains("Error"), "Failed to insert TTN");

        // 2) Find the TTN by ID
        NewPostTTN foundTTN = DAONewPostTTN.getTTNById(testTTN.getId());
        assertNotNull(foundTTN, "TTN not found");
        assertEquals("ABC123", foundTTN.getKodTTN());

        // 3) Update the TTN
        foundTTN.setManager("UpdatedManager");
        String updateResult = DAONewPostTTN.updateTTN(foundTTN);
        assertFalse(updateResult.contains("Error"), "Failed to update TTN");

        // Verify the update
        NewPostTTN updatedTTN = DAONewPostTTN.getTTNById(foundTTN.getId());
        assertEquals("UpdatedManager", updatedTTN.getManager(), "TTN update not successful");

        // 4) Delete the TTN
        String deleteResult = DAONewPostTTN.deleteTTN(foundTTN.getId());
        assertFalse(deleteResult.contains("Error"), "Failed to delete TTN");

        // Verify deletion
        assertNull(DAONewPostTTN.getTTNById(foundTTN.getId()), "TTN not deleted");
    }

    @AfterEach
    public void tearDown() {
        // Clean up any leftover test data to ensure no side effects
        if (testTTN.getId() != null) {
            DAONewPostTTN.deleteTTN(testTTN.getId());
        }
        if (testClient.getId() != null) {
            DAOClients.deleteClient(testClient.getId());
        }
    }
}
