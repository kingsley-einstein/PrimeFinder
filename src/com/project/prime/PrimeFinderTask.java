package com.project.prime;

import javafx.concurrent.Task;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class PrimeFinderTask extends Task<ObservableList<Long>> {
    
    private final long upperLimit;
    private final long lowerLimit;
    
    public PrimeFinderTask(long upperLimit, long lowerLimit) {
        
        this.upperLimit = upperLimit;
        this.lowerLimit = lowerLimit;
    }
//Provide implementation for abstract call() method
    
    @Override
    protected ObservableList<Long> call() {
        
        ObservableList<Long> results = FXCollections.<Long>observableArrayList();
        updateTitle("Prime Finder Task");
        long count = this.upperLimit - this.lowerLimit+1;
        long counter = 0;
        for (long i = 0; i <= upperLimit; i++) {
            if (this.isCancelled()) {
                updateMessage("Task Cancelled");
                break;
            }
            counter++;
            try {
                Thread
                        .sleep(2000);
            }
            catch(InterruptedException e) {
                if (this.isCancelled()) {
                    updateMessage("Task Cancelled");
                }
            }
            updateMessage("Checking "+i+" for prime");
            
            if (PrimeUtility.isPrime(i)) {
                results
                        .add(i);
            }
            updateValue(FXCollections.<Long>unmodifiableObservableList(results));
            updateProgress(counter, count);
        }
        return results;
    }
    
    @Override
    public void cancelled() {
            super
                .cancelled();
        updateMessage("Task Cancelled");
    }
    
    @Override
    public void succeeded() {
        super
                .succeeded();
        updateMessage("Task Succeeded");
    }
    
    @Override
    public void failed() {
        super
                .failed();
        updateMessage("Task Failed");
    }
}