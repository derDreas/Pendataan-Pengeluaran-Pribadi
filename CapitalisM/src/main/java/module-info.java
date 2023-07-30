module os.finance.capitalism {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens os.finance.capitalism to javafx.fxml;
    exports os.finance.capitalism;
}