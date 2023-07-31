package os.finance.capitalism.front;

import javafx.stage.Stage;

public class Window {
    Stage windowStage;

    public void initStage(Stage stage){
        this.windowStage = stage;
    }

    public Stage getStage(){
        return this.windowStage;
    }
}
