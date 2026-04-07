package com.example.mlevaluation.service;

import com.example.mlevaluation.model.UserStoryData;
import com.example.mlevaluation.util.PromptBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.regex.*;

@Service
public class LlamaService {

    private final String API_URL = "http://localhost:11434/api/generate";

    // =========================
    // MAIN EVALUATION
    // =========================
    public UserStoryData evaluate(String story){

        String prompt = PromptBuilder.buildPrompt(story);
        String response = callAPI(prompt);

        List<String> values = extractFiveBooleans(response);

        return new UserStoryData(story, values);
    }


    // =========================
    // GENERIC ASK
    // =========================
    public String ask(String prompt){
        return callAPI(prompt);
    }


    // =========================
    // API CALL
    // =========================
    private String callAPI(String prompt){

        try{

            String body = """
            {
             "model":"llama3",
             "prompt":"%s",
             "stream":false
            }
            """.formatted(prompt);

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<>(body, headers);

            ResponseEntity<String> response =
                    restTemplate.exchange(API_URL, HttpMethod.POST, entity, String.class);

            return response.getBody();

        }catch(Exception e){
            throw new RuntimeException("Llama API failed");
        }
    }


    // =========================
    // EXTRACT VALUES
    // =========================
    private List<String> extractFiveBooleans(String text){

        List<String> result = new ArrayList<>();

        Matcher m = Pattern.compile("TRUE|FALSE", Pattern.CASE_INSENSITIVE).matcher(text);

        while(m.find()){
            result.add(m.group().toUpperCase());
            if(result.size() == 5) break;
        }

        while(result.size() < 5){
            result.add("FALSE");
        }

        return result;
    }
}