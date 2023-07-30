package os.finance.capitalism;

import javafx.application.Application;
import javafx.stage.Stage;
import os.finance.capitalism.Database.DBController;

public class Main extends Application {
    public static DBController dbController;
    @Override
    public void start(Stage stage) throws Exception {
        dbController = new DBController();
        int exitCode = dbController.initDB();
        if (exitCode != 0)
            System.exit(exitCode);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
