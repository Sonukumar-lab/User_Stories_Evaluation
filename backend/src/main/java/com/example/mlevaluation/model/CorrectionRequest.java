package com.example.mlevaluation.model;

import java.util.List;

public class CorrectionRequest {

    public String getUserStory() {
        return userStory;
    }

    public void setUserStory(String userStory) {
        this.userStory = userStory;
    }

    private String userStory;

    public List<String> getPredicted() {
        return predicted;
    }

    public void setPredicted(List<String> predicted) {
        this.predicted = predicted;
    }

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