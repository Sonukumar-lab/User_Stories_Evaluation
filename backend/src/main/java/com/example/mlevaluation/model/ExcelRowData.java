package com.example.mlevaluation.model;

public class ExcelRowData {

    private String userStory;
    private String unique;
    private String conflictFree;
    private String uniform;
    private String independent;
    private String complete;

    public ExcelRowData() {
    }

    public String getUserStory() {
        return userStory;
    }

    public void setUserStory(String userStory) {
        this.userStory = userStory;
    }

    public String getUnique() {
        return unique;
    }

    public void setUnique(String unique) {
        this.unique = unique;
    }

    public String getConflictFree() {
        return conflictFree;
    }

    public void setConflictFree(String conflictFree) {
        this.conflictFree = conflictFree;
    }

    public String getUniform() {
        return uniform;
    }

    public void setUniform(String uniform) {
        this.uniform = uniform;
    }

    public String getIndependent() {
        return independent;
    }

    public void setIndependent(String independent) {
        this.independent = independent;
    }

    public String getComplete() {
        return complete;
    }

    public void setComplete(String complete) {
        this.complete = complete;
    }
}