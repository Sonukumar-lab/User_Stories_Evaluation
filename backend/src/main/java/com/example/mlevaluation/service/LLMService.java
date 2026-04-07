package com.example.mlevaluation.service;

import com.example.mlevaluation.model.UserStoryData;

import java.util.List;

public interface LLMService {

    // 🔵 EXISTING METHOD
    List<UserStoryData> evaluateStories(List<UserStoryData> stories, String model) throws Exception;


    // 🟡 REASON METHOD
    String generateReason(String story,
                          List<String> actual,
                          List<String> predicted,
                          String model) throws Exception;


    // 🟢 CORRECTION METHOD
    String generateCorrection(String story,
                              List<String> predicted,
                              String model) throws Exception;

}