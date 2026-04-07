package com.example.mlevaluation.service;

import com.example.mlevaluation.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MetricsService {

    public MetricsResponse evaluate(List<UserStoryData> actualStories,
                                    List<UserStoryData> predictedStories) {

        int TP = 0, TN = 0, FP = 0, FN = 0;

        int[] tp = new int[5];
        int[] tn = new int[5];
        int[] fp = new int[5];
        int[] fn = new int[5];

        String[] criteriaNames = {
                "Unique",
                "Conflict-Free",
                "Uniform",
                "Independent",
                "Complete"
        };

        List<ComparisonRow> rows = new ArrayList<>();

        int n = Math.min(actualStories.size(), predictedStories.size());

        for (int i = 0; i < n; i++) {

            UserStoryData actual = actualStories.get(i);
            UserStoryData predicted = predictedStories.get(i);

            List<String> actualValues = actual.getValues();
            List<String> predictedValues = predicted.getValues();

            List<Boolean> matches = new ArrayList<>();

            for (int j = 0; j < actualValues.size(); j++) {

                String a = actualValues.get(j);
                String p = predictedValues.get(j);

                boolean match = a.equalsIgnoreCase(p);
                matches.add(match);

                int av = a.equalsIgnoreCase("TRUE") ? 1 : 0;
                int pv = p.equalsIgnoreCase("TRUE") ? 1 : 0;

                // overall
                if (av == 1 && pv == 1) TP++;
                else if (av == 0 && pv == 0) TN++;
                else if (av == 0 && pv == 1) FP++;
                else if (av == 1 && pv == 0) FN++;

                // criteria wise
                if (av == 1 && pv == 1) tp[j]++;
                else if (av == 0 && pv == 0) tn[j]++;
                else if (av == 0 && pv == 1) fp[j]++;
                else if (av == 1 && pv == 0) fn[j]++;
            }

            rows.add(new ComparisonRow(
                    actual.getText(),
                    actualValues,
                    predictedValues,
                    matches
            ));
        }

        // overall metrics
        double accuracy = (double) (TP + TN) / (TP + TN + FP + FN);
        double precision = TP + FP == 0 ? 0 : (double) TP / (TP + FP);
        double recall = TP + FN == 0 ? 0 : (double) TP / (TP + FN);
        double f1 = precision + recall == 0 ? 0 : (2 * precision * recall) / (precision + recall);

        // criteria metrics
        List<CriteriaMetric> criteriaMetrics = new ArrayList<>();

        for (int i = 0; i < 5; i++) {

            double cAccuracy = (double) (tp[i] + tn[i]) /
                    (tp[i] + tn[i] + fp[i] + fn[i]);

            double cPrecision = tp[i] + fp[i] == 0 ? 0 :
                    (double) tp[i] / (tp[i] + fp[i]);

            double cRecall = tp[i] + fn[i] == 0 ? 0 :
                    (double) tp[i] / (tp[i] + fn[i]);

            double cF1 = cPrecision + cRecall == 0 ? 0 :
                    (2 * cPrecision * cRecall) / (cPrecision + cRecall);

            criteriaMetrics.add(new CriteriaMetric(
                    criteriaNames[i],
                    cAccuracy,
                    cPrecision,
                    cRecall,
                    cF1
            ));
        }

        return new MetricsResponse(
                rows,
                accuracy,
                precision,
                recall,
                f1,
                criteriaMetrics
        );
    }
}
