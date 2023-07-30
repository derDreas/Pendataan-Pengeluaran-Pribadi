package os.finance.capitalism.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Pengeluaran {
    private final String tableName = "pengeluaran";
    private String[][] columns;

    public int initDB(Connection connection) {
        StringBuilder createTableSql = new StringBuilder();
        createTableSql.append("CREATE TABLE IF NOT EXISTS ").append(this.tableName).append(" (");

        for (int i = 0; i < this.columns.length; i++) {
            createTableSql.append(columns[i][0]).append(" ").append(columns[i][1]);

            if (i < this.columns.length - 1)
                createTableSql.append(",\n");
        }

        createTableSql.append(");");

        try {
            Statement statement = connection.createStatement();
            statement.execute(createTableSql.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            return 0x1;
        }

        return 0x0;
    }
}
