package os.finance.capitalism.Database;

import java.util.ArrayList;

public class Pengeluaran {
    private ArrayList<String> columns;

    public void initDB(){
        columns = new ArrayList<>();
        int columnsLength = 6;
        String[][] listString = new String[columnsLength][2];
        listString[0][1] = "judul";
        listString[0][2] = "TEXT PRIMARY KEY NOT NULL";

        listString[0][1] = "kategori";
        listString[0][2] = "INTEGER FOREIGN KEY(kategori)";

        listString[0][1] = "jumlah";
        listString[0][2] = "REAL";

        listString[0][1] = "waktu";
        listString[0][2] = "INTEGER";

        listString[0][1] = "versi";
        listString[0][2] = "TEXT";
    }
}
