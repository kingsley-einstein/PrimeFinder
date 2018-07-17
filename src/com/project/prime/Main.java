package com.project.prime;
import javafx.scene.control.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Worker.State;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import javafx.stage.StageStyle;

public class Main extends Application {
    
    PrimeFinderTask task = new PrimeFinderTask(100, 1);
    Button start = new Button("Start");
    Button cancel = new Button("Cancel");
    Button exit = new Button("Exit");
    
    
    @Override
    public void start(Stage stage) {
       
        PrimeGUI gui = new PrimeGUI(task); 
        HBox buttonBox = new HBox(start, cancel, exit);
        VBox main = new VBox(gui, buttonBox);
        
        start
                .disableProperty()
                .bind(task
                        .stateProperty()
                .isNotEqualTo(State
                .READY));
        
        cancel
                .disableProperty()
                .bind(task
                        .stateProperty()
                .isNotEqualTo(State
                .RUNNING));
        
        start
                .setOnAction(e -> startTask());
        cancel
                .setOnAction(e -> task.cancel(true));
        exit
                .setOnAction(e -> Platform.exit());
        
        Scene scene = new Scene(main, 400, 325);
        stage
                .setScene(scene);
        stage
                .setTitle("Prime Finder");
        stage
                .initStyle(StageStyle.UNDECORATED);
        stage
                .show();
    }
    public void startTask() {
        //Submit the task to a background executor service
        
        ExecutorService service = Executors.newSingleThreadExecutor();
        service
                .submit(task);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}