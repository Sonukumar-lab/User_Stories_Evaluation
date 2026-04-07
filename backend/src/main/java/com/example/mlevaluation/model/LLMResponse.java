package com.example.mlevaluation.model;

public class LLMResponse {

    private boolean unique;
    private boolean conflictFree;
    private boolean uniform;
    private boolean independent;
    private boolean complete;

    public LLMResponse() {
    }

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public boolean isConflictFree() {
        return conflictFree;
    }

    public void setConflictFree(boolean conflictFree) {
        this.conflictFree = conflictFree;
    }

    public boolean isUniform() {
        return uniform;
    }

    public void setUniform(boolean uniform) {
        this.uniform = uniform;
    }

    public boolean isIndependent() {
        return independent;
    }

    public void setIndependent(boolean independent) {
        this.independent = independent;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}