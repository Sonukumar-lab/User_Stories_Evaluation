package com.example.mlevaluation.model;

import java.util.List;

public class ComparisonRow {

    private String userStory;
    private List<String> actualValues;
    private List<String> predictedValues;
    private List<Boolean> matches;

    public ComparisonRow() {
    }

    public ComparisonRow(String userStory, List<String> actualValues,
                         List<String> predictedValues, List<Boolean> matches) {
        this.userStory = userStory;
        this.actualValues = actualValues;
        this.predictedValues = predictedValues;
        this.matches = matches;
    }

    public String getUserStory() {
        return userStory;
    }

    public void setUserStory(String userStory) {
        this.userStory = userStory;
    }

    public List<String> getActualValues() {
        return actualValues;
    }

    public void setActualValues(List<String> actualValues) {
        this.actualValues = actualValues;
    }

    public List<String> getPredictedValues() {
        return predictedValues;
    }

    public void setPredictedValues(List<String> predictedValues) {
        this.predictedValues = predictedValues;
    }

    public List<Boolean> getMatches() {
        return matches;
    }

    public void setMatches(List<Boolean> matches) {
        this.matches = matches;
    }
}