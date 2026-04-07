package com.example.mlevaluation.controller;

import com.example.mlevaluation.model.*;
import com.example.mlevaluation.service.ExcelService;
import com.example.mlevaluation.service.LLMService;
import com.example.mlevaluation.service.MetricsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class EvaluationController {

    @Autowired
    private ExcelService excelService;

    @Autowired
    private LLMService llmService;

    @Autowired
    private MetricsService metricsService;

    /**
     * MAIN EVALUATION
     */
    @PostMapping("/evaluate-excel")
    public ResponseEntity<MetricsResponse> evaluateExcel(
            @RequestParam("file") MultipartFile file,
            @RequestParam("model") String model
    ) {

        try {

            List<UserStoryData> actualStories = excelService.readExcel(file);

            List<UserStoryData> predictedStories =
                    llmService.evaluateStories(actualStories, model);

            MetricsResponse response =
                    metricsService.evaluate(actualStories, predictedStories);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }


    /**
     * 🟡 GET REASON
     */
    @PostMapping("/reason")
    public ResponseEntity<String> getReason(
            @RequestBody ReasonRequest request
    ){
        try {

            String result = llmService.generateReason(
                    request.getUserStory(),
                    request.getActual(),
                    request.getPredicted(),
                    request.getModel()
            );

            return ResponseEntity.ok(result);

        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }


    /**
     * 🟢 GET CORRECTION
     */
    @PostMapping("/correction")
    public ResponseEntity<String> getCorrection(
            @RequestBody CorrectionRequest request
    ){
        try {

            String result = llmService.generateCorrection(
                    request.getUserStory(),
                    request.getPredicted(),
                    request.getModel()
            );

            return ResponseEntity.ok(result);

        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

}