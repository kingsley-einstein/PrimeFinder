package com.project.prime;

import javafx.scene.layout.GridPane;
import javafx.scene.control.*;
import javafx.concurrent.Worker;
import javafx.collections.*;
import javafx.beans.binding.When;

public class PrimeGUI extends GridPane {
    
    private final Label titleLabel = new Label("");
    //private final Label messageLabel = new Label("");
    private final Label progressLabel = new Label("");
    private final Label stateLabel = new Label("");
    private final ProgressBar progress = new ProgressBar();
    private final TextArea valueArea = new TextArea();
    private final TextArea messageArea = new TextArea();
    
    public PrimeGUI(Worker<ObservableList<Long>> worker) {
        addGUI();
        bindToWorker(worker);
    }
        //Set layout
        public final void addGUI() {
        addRow(0, new Label("Title: "), titleLabel);
        addRow(2, new Label("State: "), stateLabel);
        addRow(4, new Label("Progress: "), progress, progressLabel);
        addRow(6, new Label("Message: "), messageArea);
        addRow(8, new Label("Values: "), valueArea);
        
        valueArea
                .setPrefRowCount(3);
        valueArea
                .setPrefColumnCount(20);
        
        messageArea
                .setPrefRowCount(3);
        messageArea
                .setPrefColumnCount(20);
    }
    //Bind values to worker
    public final void bindToWorker(final Worker<ObservableList<Long>> worker) {
        titleLabel
                .textProperty()
                .bind(worker
                      .titleProperty());
        
        stateLabel
                .textProperty()
                .bind(worker
                .stateProperty()
                .asString());
        
        progress
                .progressProperty()
                .bind(worker
                .progressProperty());
        
        progressLabel
                .textProperty()
                .bind(new When(worker
                .progressProperty()
                .isEqualTo(-1))
                .then("Unknown")
                .otherwise(worker
                        .progressProperty()
                        .multiply(100.0)
                .asString("%.0f%%")));
        
        valueArea
                .textProperty()
                .bind(worker
                .valueProperty()
                .asString());
        
        messageArea
                .textProperty()
                .bind(worker
                .messageProperty());
        worker
                .exceptionProperty()
                .addListener((p, oldvalue, newvalue) -> {
                    if (newvalue != null) {
                        messageArea
                                .setText(newvalue
                                .getMessage());
                    }
                });
    }
    
}