import repository.DataStore;
import repository.file.FileDataStore;
import repository.jdbc.JDBCDataStore;
import repository.memory.InMemoryDataStore;

public class DataStoreFactory {
    public static DataStore createDataStore(int choice) {
        switch (choice) {
            case 1:
                return new InMemoryDataStore();
            case 2:
                return new FileDataStore();
            case 3:
                return (DataStore) new JDBCDataStore();
            default:
                throw new IllegalArgumentException("Invalid choice");
        }
    }
}