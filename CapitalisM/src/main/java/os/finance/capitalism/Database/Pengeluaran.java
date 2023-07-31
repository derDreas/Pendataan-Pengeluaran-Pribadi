package os.finance.capitalism.Database;

import java.sql.*;
import java.util.ArrayList;

public class Pengeluaran {
    private Kategori dbKategori;
    private ArrayList<Pengeluaran.Data> dataArrayPengeluaran;
    private Connection  connectionPointer;
    public static class Data{
        String id;
        String judul;
        long waktu;
        int idKategori;
        double jumlah;
        String versiData;

        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }

        public String getJudul() {
            return judul;
        }
        public void setJudul(String judul) {
            this.judul = judul;
        }

        public long getWaktu() {
            return waktu;
        }
        public void setWaktu(long waktu) {
            this.waktu = waktu;
        }

        public int getIdKategori() {
            return idKategori;
        }
        public void setIdKategori(int idKategori) {
            this.idKategori = idKategori;
        }

        public double getJumlah() {
            return jumlah;
        }
        public void setJumlah(double jumlah) {
            this.jumlah = jumlah;
        }

        public String getVersiData() {
            return versiData;
        }
        public void setVersiData(String versiData) {
            this.versiData = versiData;
        }
    }

    public int initDB(Connection connection) {
        // Blok kode inisialisasi tabel 'kategori'
        {
            this.dbKategori = new Kategori();
            this.dbKategori.initDB(connection);
            this.connectionPointer = connection;
        }
        this.dataArrayPengeluaran = new ArrayList<>();

        String[][] columns = new String[][]{
                {"id", "TEXT PRIMARY KEY NOT NULL"},
                {"judul", "TEXT NOT NULL"},
                {"deskripsi", "TEXT"},
                {"kategori", "INTEGER NOT NULL REFERENCES kategori(id)"},
                {"jumlah", "REAL CHECK (jumlah >= 0) NOT NULL"},
                {"waktu", "INTEGER NOT NULL"},
                {"versi", "TEXT NOT NULL"}
        };

        StringBuilder createTableSql = new StringBuilder();
        String tableName = "pengeluaran";
        createTableSql.append("CREATE TABLE IF NOT EXISTS ").append(tableName).append(" (");

        for (int i = 0; i < columns.length; i++) {
            createTableSql.append(columns[i][0]).append(" ").append(columns[i][1]);
            if (i < columns.length - 1)
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

    public int readAllData(){

        String query = "SELECT * FROM pengeluaran;";
        try {
            this.dataArrayPengeluaran.clear();
            ResultSet resultSet;
            Statement statement;

            statement = this.connectionPointer.createStatement();
            statement.execute(query);
            resultSet = statement.getResultSet();

            while (resultSet.next()) {
                Pengeluaran.Data pengeluaranData = new Pengeluaran.Data();

                pengeluaranData.setId(resultSet.getString("id"));
                pengeluaranData.setJudul(resultSet.getString("judul"));
                pengeluaranData.setWaktu(resultSet.getLong("waktu"));
                pengeluaranData.setIdKategori(resultSet.getInt("kategori"));
                pengeluaranData.setJumlah(resultSet.getDouble("jumlah"));
                pengeluaranData.setVersiData(resultSet.getString("versi"));
                dataArrayPengeluaran.add(pengeluaranData);
            }

        }catch (SQLException e){
            return 0x1;
        }
        return 0x0;
    }

    public int createData(Pengeluaran.Data dataBaru) {
        String insertSql = "INSERT INTO pengeluaran (id, judul, waktu, kategori, jumlah, versi) VALUES (?, ?, ?, ?, ?, ?);";

        try {
            PreparedStatement preparedStatement = this.connectionPointer.prepareStatement(insertSql);

            // Set values for the placeholders in the prepared statement
            preparedStatement.setString(1, dataBaru.getId());
            preparedStatement.setString(2, dataBaru.getJudul());
            preparedStatement.setLong(3, dataBaru.getWaktu());
            preparedStatement.setInt(4, dataBaru.getIdKategori());
            preparedStatement.setDouble(5, dataBaru.getJumlah());
            preparedStatement.setString(6, Metadata.VERSION);

            // Execute the INSERT statement
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0x1;
        }
        return 0x0;
    }

}
