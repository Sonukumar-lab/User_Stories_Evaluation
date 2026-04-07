package com.example.mlevaluation.model;

public class CriteriaMetric {

    private String name;
    private double accuracy;
    private double precision;
    private double recall;
    private double f1;

    public CriteriaMetric() {
    }

    public CriteriaMetric(String name, double accuracy, double precision,
                          double recall, double f1) {
        this.name = name;
        this.accuracy = accuracy;
        this.precision = precision;
        this.recall = recall;
        this.f1 = f1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public double getPrecision() {
        return precision;
    }

    public void setPrecision(double precision) {
        this.precision = precision;
    }

    public double getRecall() {
        return recall;
    }

    public void setRecall(double recall) {
        this.recall = recall;
    }

    public double getF1() {
        return f1;
    }

    public void setF1(double f1) {
        this.f1 = f1;
    }
}
