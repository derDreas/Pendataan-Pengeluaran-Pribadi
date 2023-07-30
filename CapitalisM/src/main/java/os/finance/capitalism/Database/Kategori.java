package os.finance.capitalism.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Kategori {
    private static final String tableName = "kategori";
    private Connection connectionPointer;

    public int initDB(Connection connection){
        this.connectionPointer = connection;
        String[][] columns = new String[][]{
                {"id", "INTEGER PRIMARY KEY NOT NULL"},
                {"judul", "TEXT NOT NULL"},
                {"versi", "TEXT"}
        };

        StringBuilder createTableSql = new StringBuilder();
        createTableSql.append("CREATE TABLE IF NOT EXISTS ").append(Kategori.tableName).append(" (");

        for (int i = 0; i < columns.length; i++) {
            createTableSql.append(columns[i][0]).append(" ").append(columns[i][1]);

            if (i < columns.length - 1)
                createTableSql.append(",\n");
        }

        createTableSql.append(");");
        try {
            Statement statement = this.connectionPointer.createStatement();
            statement.execute(createTableSql.toString());
        } catch (SQLException e) {
            return 0x1;
        }
        return 0x0;
    }
    public String readJudulKategori(int idKategori){
        String query = "SELECT judul FROM kategori WHERE id = " + String.valueOf(idKategori) + ";";
        try {
            Statement statement = this.connectionPointer.createStatement();
            statement.execute(query);
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next())
                return resultSet.getString("judul");
        } catch (SQLException e){

        }
        return null;
    }
}
