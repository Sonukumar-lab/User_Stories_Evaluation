package com.example.mlevaluation.controller;

import com.example.mlevaluation.model.MetricsRequest;
import com.example.mlevaluation.model.MetricsResponse;
import com.example.mlevaluation.service.MetricsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/metrics")
@CrossOrigin(origins = "*")
public class MetricsController {

    @Autowired
    private MetricsService metricsService;

    /**
     * Old API for manual JSON comparison
     * Endpoint: POST /api/metrics/evaluate
     */
    @PostMapping("/evaluate")
    public ResponseEntity<MetricsResponse> evaluate(
            @RequestBody MetricsRequest request
    ) {

        MetricsResponse response = metricsService.evaluate(
                request.getActualStories(),
                request.getPredictedStories()
        );

        return ResponseEntity.ok(response);
    }
}

