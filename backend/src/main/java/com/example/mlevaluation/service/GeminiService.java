package com.example.mlevaluation.service;

import com.example.mlevaluation.model.UserStoryData;
import com.example.mlevaluation.util.PromptBuilder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    public UserStoryData evaluate(String story){

        String prompt = PromptBuilder.buildPrompt(story);
        String content = extractContent(callAPI(prompt));

        List<String> values = parseValues(content);

        return new UserStoryData(story, values);
    }

    public String ask(String prompt){
        return extractContent(callAPI(prompt));
    }

    private String callAPI(String prompt){

        String url =
                "https://generativelanguage.googleapis.com/v1/models/gemini-2.5-flash:generateContent?key=" + apiKey;

        String body = """
        {
          "contents":[
            {"parts":[{"text":"%s"}]}
          ]
        }
        """.formatted(prompt);

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response =
                restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        return response.getBody();
    }

    //  FIX: Correct Gemini parsing
    private String extractContent(String json){

        try{
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(json);

            return root
                    .path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText();

        }catch(Exception e){
            return json;
        }
    }

    private List<String> parseValues(String text){

        String firstLine = text.split("\\n")[0].trim();

        String[] arr = firstLine.split(",");

        List<String> result = new ArrayList<>();

        for(String s : arr){
            result.add(s.trim().toUpperCase());
        }

        while(result.size() < 5){
            result.add("FALSE");
        }

        return result.subList(0,5);
    }
}