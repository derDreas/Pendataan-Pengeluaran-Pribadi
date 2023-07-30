package os.finance.capitalism.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Kategori {
    private static final String tableName = "kategori";
    private Connection connectionPointer;

    public static class Data {
        int id;
        String judul;
        String versi;

        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }

        public String getJudul() {
            return judul;
        }
        public void setJudul(String judul) {
            this.judul = judul;
        }

        public String getVersi() {
            return versi;
        }
        public void setVersi(String versi) {
            this.versi = versi;
        }
    }

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
        } catch (SQLException ignored){

        }
        return null;
    }

    public Kategori.Data readDataKategori(int idKategori){
        String query = "SELECT * from pengeluaran";
        Kategori.Data bufferData = new Kategori.Data();
        try {
            Statement statement = this.connectionPointer.createStatement();
            statement.execute(query);
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()){
                bufferData.setId(resultSet.getInt("id"));
                bufferData.setJudul(resultSet.getString("judul"));
                bufferData.setVersi(resultSet.getString("versi"));
            }
            return bufferData;
        } catch (SQLException ignored) {

        }
        return null;
    }
}
