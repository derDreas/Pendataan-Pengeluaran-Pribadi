package os.finance.capitalism.Database;

import java.util.UUID;

public class Util {
    public static String generateUUIDv4() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
