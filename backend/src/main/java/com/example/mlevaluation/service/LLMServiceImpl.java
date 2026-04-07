package com.example.mlevaluation.service;

import com.example.mlevaluation.model.UserStoryData;
import com.example.mlevaluation.util.PromptBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LLMServiceImpl implements LLMService {

    @Autowired
    private OpenAIService openAIService;

    @Autowired
    private GeminiService geminiService;

    @Autowired
    private LlamaService llamaService;

    @Override
    public List<UserStoryData> evaluateStories(List<UserStoryData> stories, String model) {

        List<UserStoryData> predicted = new ArrayList<>();

        for (UserStoryData story : stories) {

            UserStoryData result;

            switch(model.toLowerCase()){

                case "openai":
                    result = openAIService.evaluate(story.getText());
                    break;

                case "gemini":
                    result = geminiService.evaluate(story.getText());
                    break;

                case "llama":
                    result = llamaService.evaluate(story.getText());
                    break;

                default:
                    throw new RuntimeException("Unknown model");
            }

            predicted.add(result);
        }

        return predicted;
    }


    // =========================
    // 🟡 REASON
    // =========================
    public String generateReason(String story, List<String> actual,
                                 List<String> predicted, String model){

        String prompt = PromptBuilder.buildReasonPrompt(story, actual, predicted);

        switch(model.toLowerCase()){

            case "openai":
                return openAIService.ask(prompt);

            case "gemini":
                return geminiService.ask(prompt);

            case "llama":
                return llamaService.ask(prompt);

            default:
                throw new RuntimeException("Unknown model");
        }
    }


    // =========================
    // 🟢 CORRECTION
    // =========================
    public String generateCorrection(String story,
                                     List<String> predicted,
                                     String model){

        String prompt = PromptBuilder.buildCorrectionPrompt(story, predicted);

        switch(model.toLowerCase()){

            case "openai":
                return openAIService.ask(prompt);

            case "gemini":
                return geminiService.ask(prompt);

            case "llama":
                return llamaService.ask(prompt);

            default:
                throw new RuntimeException("Unknown model");
        }
    }

}