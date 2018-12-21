package ru.strict.fx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import ru.strict.patterns.mvc.views.IView;

public class Main extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void init() throws Exception {

    }

    @Override
    public void start(Stage stage) throws Exception {
        FrameBase myStage = new FrameBase(null);
        myStage.launch();
    }

    @Override
    public void stop() throws Exception {

    }
}
