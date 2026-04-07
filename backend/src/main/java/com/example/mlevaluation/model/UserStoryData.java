package com.example.mlevaluation.model;

import java.util.List;

public class UserStoryData {

    private String text;
    private List<String> values;

    public UserStoryData() {
    }

    public UserStoryData(String text, List<String> values) {
        this.text = text;
        this.values = values;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}