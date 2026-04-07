package com.example.mlevaluation.util;

import java.util.List;

public class PromptBuilder {

    // ========================
    // MAIN EVALUATION
    // ========================
    public static String buildPrompt(String story){

        return """
You are a strict AI evaluator for INVEST user story quality.

Evaluate the given user story on EXACTLY these 5 criteria:
1. Unique → No duplication
2. Conflict-Free → No contradictions
3. Uniform → Consistent format/style
4. Independent → No dependency on other stories
5. Complete → Fully clear and actionable

⚠️ RULES (VERY STRICT):
- Return EXACTLY 5 values
- Only TRUE or FALSE
- Comma separated
- NO explanation
- NO extra words
- NO newline

✅ Output format ONLY:
TRUE,FALSE,TRUE,TRUE,FALSE

User Story:
""" + story;
    }


    // ========================
    // 🟡 REASON PROMPT (WHY DIFFERENCE)
    // ========================
    public static String buildReasonPrompt(String story,
                                           List<String> actual,
                                           List<String> predicted){

        return """
Explain differences between actual and predicted INVEST values.

Rules:
- Max 4-5 short lines
- No markdown (no **, no symbols)
- Simple plain text
- Each point in new line

User Story:
""" + story +

                "\nActual: " + actual +
                "\nPredicted: " + predicted;
    }


    // ========================
    // 🟢 CORRECTION PROMPT (FIX + EXPLANATION)
    // ========================
    public static String buildCorrectionPrompt(String story,
                                               List<String> predicted){

        return """
You are a senior product owner and Agile expert.

Your task:
Rewrite the given user story into a HIGH QUALITY INVEST-compliant story.

Goal:
- The new user story MUST satisfy all INVEST criteria
- So that predicted evaluation becomes correct and consistent

INVEST Criteria:
1. Unique
2. Conflict-Free
3. Uniform
4. Independent
5. Complete

RULES:
- Generate a NEW improved user story
- Make it clear, complete, and well-structured
- Do NOT return TRUE/FALSE
- Keep explanation short (1-2 lines only)

OUTPUT FORMAT:

Corrected User Story:
<write improved user story here>

Reason:
<short explanation why this version is better>

User Story:
""" + story +

                "\nCurrent Predicted Issues: " + predicted;
    }

}