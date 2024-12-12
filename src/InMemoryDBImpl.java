import java.util.HashMap;
import java.util.Map;

// Enum for transaction state
enum TransactionState {
    INACTIVE,
    ACTIVE
}

public class InMemoryDBImpl implements InMemoryDB {
    private final Map<String, Integer> mainStore = new HashMap<>();
    private final Map<String, Integer> transactionStore = new HashMap<>();
    private TransactionState transactionState = TransactionState.INACTIVE;

    @Override
    public Integer get(String key) {
        if (transactionState == TransactionState.ACTIVE && transactionStore.containsKey(key)) {
            return null; // Uncommitted changes should not be visible
        }
        return mainStore.get(key);
    }

    @Override
    public void put(String key, int val) {
        if (transactionState != TransactionState.ACTIVE) {
            throw new IllegalStateException("No active transaction - begin a transaction first.");
        }
        transactionStore.put(key, val);
    }

    @Override
    public void begin_transaction() {
        if (transactionState == TransactionState.ACTIVE) {
            throw new IllegalStateException("A transaction is already active.");
        }
        // Set TransactionState to ACTIVE to start transaction and clear the transactionStore hash map
        transactionState = TransactionState.ACTIVE;
        transactionStore.clear();
    }

    @Override
    public void commit() {
        if (transactionState != TransactionState.ACTIVE) {
            throw new IllegalStateException("No active transaction to commit.");
        }
        mainStore.putAll(transactionStore); // Replace mainStore key-value pairs with all updated transactionStore pairs
        transactionStore.clear(); // Reset transactionStore hashmap
        transactionState = TransactionState.INACTIVE; // Reset TransactionState to INACTIVE
    }

    @Override
    public void rollback() {
        if (transactionState != TransactionState.ACTIVE) {
            throw new IllegalStateException("No active transaction to rollback.");
        }
        // Clear the transactionStore hash map and set TransactionState to INACTIVE to exit transaction
        transactionStore.clear();
        transactionState = TransactionState.INACTIVE;
    }
}


