package com.example.mlevaluation.model;

import java.util.List;

public class ReasonRequest {

    private String userStory;

    public String getUserStory() {
        return userStory;
    }

    public void setUserStory(String userStory) {
        this.userStory = userStory;
    }

    public List<String> getActual() {
        return actual;
    }

    public void setActual(List<String> actual) {
        this.actual = actual;
    }

    public List<String> getPredicted() {
        return predicted;
    }

    public void setPredicted(List<String> predicted) {
        this.predicted = predicted;
    }

    private List<String> actual;
    private List<String> predicted;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    private String model;

    // getters & setters
}