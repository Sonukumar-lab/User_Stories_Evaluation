package com.example.mlevaluation.model;

import java.util.List;

public class MetricsResponse {

    private List<ComparisonRow> rows;

    private double accuracy;
    private double precision;
    private double recall;
    private double f1;

    private List<CriteriaMetric> criteriaMetrics;   // NEW

    public MetricsResponse() {
    }

    public MetricsResponse(List<ComparisonRow> rows,
                           double accuracy,
                           double precision,
                           double recall,
                           double f1,
                           List<CriteriaMetric> criteriaMetrics) {

        this.rows = rows;
        this.accuracy = accuracy;
        this.precision = precision;
        this.recall = recall;
        this.f1 = f1;
        this.criteriaMetrics = criteriaMetrics;
    }

    public List<ComparisonRow> getRows() {
        return rows;
    }

    public void setRows(List<ComparisonRow> rows) {
        this.rows = rows;
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

    public List<CriteriaMetric> getCriteriaMetrics() {
        return criteriaMetrics;
    }

    public void setCriteriaMetrics(List<CriteriaMetric> criteriaMetrics) {
        this.criteriaMetrics = criteriaMetrics;
    }
}
