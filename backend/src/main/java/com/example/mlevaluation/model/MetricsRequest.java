package com.example.mlevaluation.model;

import java.util.List;

public class MetricsRequest {

    private List<UserStoryData> actualStories;
    private List<UserStoryData> predictedStories;

    public MetricsRequest() {
    }

    public List<UserStoryData> getActualStories() {
        return actualStories;
    }

    public void setActualStories(List<UserStoryData> actualStories) {
        this.actualStories = actualStories;
    }

    public List<UserStoryData> getPredictedStories() {
        return predictedStories;
    }

    public void setPredictedStories(List<UserStoryData> predictedStories) {
        this.predictedStories = predictedStories;
    }
}