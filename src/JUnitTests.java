import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JUnitTests {
    private InMemoryDB db;

    @BeforeEach
    void setUp() {
        db = new InMemoryDBImpl();
    }

    @Test
    void testGetNonExistentKey() {
        assertNull(db.get("A"), "Expected null for non-existent key.");
    }

    @Test
    void testPutWithoutTransaction() {
        assertThrows(IllegalStateException.class, () -> db.put("A", 5),
                "Expected exception when putting value outside a transaction.");
    }

    @Test
    void testTransactionCommit() {
        db.begin_transaction();
        db.put("A", 5);
        assertNull(db.get("A"), "Value should not be visible before commit.");
        db.commit();
        assertEquals(5, db.get("A"), "Committed value should be visible.");
    }

    @Test
    void testRollbackTransaction() {
        db.begin_transaction();
        db.put("B", 10);
        db.rollback();
        assertNull(db.get("B"), "Rolled back value should not be visible.");
    }
}
