public class Main {
    public static void main(String[] args) {
        // Instantiate a database object
        InMemoryDB db = new InMemoryDBImpl();

        // Test case: get non-existent key
        System.out.println(db.get("A")); // Expected Output: null

        // Test case: put without transaction
        try {
            db.put("A", 5);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage()); // Expected Output: No active transaction - begin a transaction first
        }

        // Test case: begin transaction and put
        db.begin_transaction();
        db.put("A", 5);
        System.out.println(db.get("A")); // Expected Output: null

        db.put("A", 6);
        db.commit();
        System.out.println(db.get("A")); // Expected Output: 6

        // Test case: commit without transaction
        try {
            db.commit();
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage()); // Expected Output: No active transaction to commit.
        }

        // Test case: rollback without transaction
        try {
            db.rollback();
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage()); // Expected Output: No active transaction to rollback.
        }

        // Test case: rollback changes
        System.out.println(db.get("B")); // Expected Output: null
        db.begin_transaction();
        db.put("B", 10);
        db.rollback();
        System.out.println(db.get("B")); // Expected Output: null
    }
}