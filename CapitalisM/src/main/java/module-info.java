module os.finance.capitalism {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.xerial.sqlitejdbc;
    requires java.sql;
                            
    opens os.finance.capitalism to javafx.fxml;
    exports os.finance.capitalism;
}