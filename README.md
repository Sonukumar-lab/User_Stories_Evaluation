# 🚀 AI User Story Evaluation System

A full-stack AI-powered system that evaluates **User Stories** using **INVEST criteria** and provides:

* 📊 Metrics (Accuracy, Precision, Recall, F1)
* 📈 Visual Graphs (Overall + Criteria-wise)
* 🤖 Multi-LLM Evaluation (OpenAI, Gemini, Llama)
* 🔍 AI-based Reasoning (Why mismatch?)
* ✨ AI-based Correction (Improved User Story)

---

# 🧠 Key Features

### ✅ 1. Multi-LLM Evaluation

Supports multiple AI models:

* OpenAI (via OpenRouter)
* Gemini (Google)
* Llama (Local - Ollama)

---

### 📊 2. Metrics Calculation

* Accuracy
* Precision
* Recall
* F1 Score

✔ Also supports:

* Overall metrics
* Criteria-wise metrics (5 INVEST factors)

---

### 📈 3. Graph Visualization

* Bar charts for:

  * Overall performance
  * Individual INVEST criteria
* Clean UI using Chart.js

---

### 🔍 4. AI Reasoning (Why Button)

* Explains mismatch between:

  * Actual values
  * Predicted values
* Short, clean, human-readable explanation

---

### ✨ 5. AI Correction (Fix Button)

* Generates:

  * Improved User Story (rewritten)
  * Short explanation (why better)

✔ Ensures:

* INVEST compliance
* Better clarity & completeness

---

### 📁 6. Excel Upload Support

* Upload dataset in Excel format
* Automatically parsed and evaluated

---

# 📁 Project Structure

---

## 🔹 Backend (Spring Boot)

```
ml-evaluation-backend/
│
├── src/main/java/com/example/mlevaluation/
│
├── controller/
│   ├── EvaluationController.java
│   └── MetricsController.java
│
├── service/
│   ├── MetricsService.java
│   ├── ExcelService.java
│   ├── LLMService.java
│   ├── OpenAIService.java
│   ├── GeminiService.java
│   └── LlamaService.java
│
├── model/
│   ├── UserStoryData.java
│   ├── ComparisonRow.java
│   ├── MetricsRequest.java
│   ├── MetricsResponse.java
│   ├── ExcelRowData.java
│   └── LLMResponse.java
│
├── config/
│   ├── LLMConfig.java
│   └── CorsConfig.java
│
├── util/
│   ├── PromptBuilder.java
│   └── ExcelParser.java
│
├── exception/
│   └── GlobalExceptionHandler.java
│
├── resources/
│   ├── application.properties
│   └── .env
│
├── pom.xml
└── README.md
```

---

## 🔹 Frontend (Vanilla JS)

```
ml-evaluation-frontend/
│
├── index.html
│
├── css/
│   └── style.css
│
├── js/
│   ├── config.js
│   ├── api.js
│   ├── fileUpload.js
│   ├── llmSelector.js
│   ├── evaluation.js
│   └── tableRenderer.js
│
├── assets/
│   ├── logo.png
│   └── icons/
│
└── README.md
```

---

# ⚙️ Setup Instructions

---

## 🖥️ Backend Setup

### 1. Clone Repository

```
git clone <your-repo-url>
cd ml-evaluation-backend
```

---

### 2. Configure API Keys

Edit `application.properties`:

```
openai.api.key=YOUR_OPENAI_KEY
gemini.api.key=YOUR_GEMINI_KEY
```

---

### 3. Run Backend

```
mvn spring-boot:run
```

Server runs on:

```
http://localhost:8080
```

---

## 🌐 Frontend Setup

### 1. Open Frontend Folder

```
cd ml-evaluation-frontend
```

---

### 2. Update API URL

In `config.js`:

```
BASE_URL = "http://localhost:8080/api"
```

---

### 3. Run Frontend

Just open:

```
index.html
```

(or use Live Server)

---

# 📊 API Endpoints

---

### 🔹 Evaluate Excel

```
POST /api/evaluate-excel
```

**Request:**

* file (Excel)
* model (openai | gemini | llama)

---

### 🔹 Get Reason

```
POST /api/reason
```

---

### 🔹 Get Correction

```
POST /api/correction
```

---

# 🧪 Workflow

1. Upload Excel file
2. Select LLM model
3. Click **Evaluate**
4. System:

   * Calls LLM
   * Generates predictions
   * Calculates metrics
   * Displays charts & table
5. Use:

   * **Why** → See mismatch reason
   * **Fix** → Get improved user story

---

# 🧠 INVEST Criteria Used

1. **Unique** – No duplication
2. **Conflict-Free** – No contradiction
3. **Uniform** – Consistent format
4. **Independent** – No dependency
5. **Complete** – Fully clear

---

# 🎨 UI Features

* Dark modern theme 🌙
* Colored badges for TRUE/FALSE
* Tick / Cross indicators ✔ ✖
* Compact action buttons (Why / Fix)
* Popup-based explanations
* Responsive chart grid

---

# ⚠️ Important Notes

* Gemini responses may include extra text → handled via parsing
* Always ensure API keys are valid
* Llama requires local server (Ollama running)

---

# 🚀 Future Enhancements

* Download improved Excel
* Before vs After comparison
* Auto-update corrected stories
* User authentication
* Dashboard analytics

---

# 👨‍💻 Author

Developed as a **Research-Level AI Evaluation System** for analyzing and improving user stories using LLMs.

---

# ⭐ Final Thought

This project is not just evaluation —
👉 it is an **AI-powered improvement engine for Agile user stories**

---
