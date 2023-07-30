package os.finance.capitalism.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBController {
    Connection connection;
    Statement statement;
    Pengeluaran dbPengeluaran;
    final String url = "jdbc:sqlite:database.db";

    public int initDB(){
        // Blok kode yang berfungsi untuk memeriksa file 'database.db' (0x1...)
        {
            try {
                connection = DriverManager.getConnection(url);
            } catch (SQLException e) {
                return 0x11;
            }
            try {
                this.statement = connection.createStatement();
            } catch (SQLException e) {
                try {
                    connection.close();
                } catch (SQLException ignore) {}
                return 0x12;
            }
        }

        // Blok kode yang memeriksa tabel 'pengeluaran' pada 'database.db' (0x2...)
        {
            dbPengeluaran = new Pengeluaran();
            int exitCode = dbPengeluaran.initDB(this.connection);
            switch (exitCode){
                case 0x1:
                    return 0x21;
            }
        }
        return 0x0;
    }

    public void closeDB(){
        try {
            statement.close();
            connection.close();
        } catch (SQLException ignored) {
        }
    }
}
