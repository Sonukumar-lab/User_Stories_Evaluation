document.getElementById("evaluateBtn")
    .addEventListener("click", startEvaluation);

let charts = [];

async function startEvaluation() {
    try {
        let file = getSelectedFile();
        if (!file) return;

        let model = getSelectedModel();

        let result = await evaluateExcel(file, model);

        renderMetrics(result);
        renderTable(result.rows);

        window.currentRows = result.rows;

        drawAllCharts(result);

    } catch (error) {
        console.error(error);
        alert("Evaluation failed");
    }
}


// ======================
// METRICS
// ======================
function renderMetrics(data) {
    document.getElementById("accuracy").innerText = data.accuracy.toFixed(2);
    document.getElementById("precision").innerText = data.precision.toFixed(2);
    document.getElementById("recall").innerText = data.recall.toFixed(2);
    document.getElementById("f1").innerText = data.f1.toFixed(2);
}


// ======================
// CHARTS
// ======================
function drawAllCharts(data) {

    let container = document.getElementById("allCharts");

    container.innerHTML = "";
    charts.forEach(c => c.destroy());
    charts = [];

    // 🔹 Overall
    createChartCard(container, "Overall", [
        data.accuracy,
        data.precision,
        data.recall,
        data.f1
    ]);

    // 🔹 Criteria
    if (data.criteriaMetrics) {
        data.criteriaMetrics.forEach(metric => {
            createChartCard(container, metric.name, [
                metric.accuracy,
                metric.precision,
                metric.recall,
                metric.f1
            ]);
        });
    }
}

function createChartCard(container, title, dataValues) {

    let card = document.createElement("div");
    card.className = "chart-card";

    let heading = document.createElement("h3");
    heading.innerText = title;

    let canvas = document.createElement("canvas");

    card.appendChild(heading);
    card.appendChild(canvas);
    container.appendChild(card);

    let chart = new Chart(canvas.getContext("2d"), {
        type: "bar",
        data: {
            labels: ["Acc", "Pre", "Rec", "F1"],
            datasets: [{
                data: dataValues,
                backgroundColor: [
                    "#6366f1",
                    "#22c55e",
                    "#f59e0b",
                    "#ef4444"
                ]
            }]
        },
        options: {
            plugins: { legend: { display: false } },
            scales: { y: { beginAtZero: true, max: 1 } }
        }
    });

    charts.push(chart);
}


// ======================
//  REASON
// ======================
async function handleReason(index){

    let row = window.currentRows[index];
    let model = getSelectedModel();

    showPopup("Loading...");

    try{
        let result = await getReason({
            userStory: row.userStory,
            actual: row.actualValues,
            predicted: row.predictedValues,
            model: model
        });

        showPopup(result);

    }catch(e){
        showPopup("Failed to get reason");
    }
}


// ======================
//  CORRECTION
// ======================
async function handleCorrection(index){

    let row = window.currentRows[index];
    let model = getSelectedModel();

    showPopup("Loading...");

    try{
        let result = await getCorrection({
            userStory: row.userStory,
            predicted: row.predictedValues,
            model: model
        });

        showPopup(result);

    }catch(e){
        showPopup("Failed to get correction");
    }
}


// ======================
//  TEXT CLEANER
// ======================
function cleanText(text){

    if(!text) return "";

    //  remove markdown **
    text = text.replace(/\*\*/g, "");

    //  remove unwanted symbols
    text = text.replace(/[#`]/g, "");

    //  extra spaces fix
    text = text.replace(/\s+/g, " ");

    //  new line for numbering
    text = text.replace(/(\d+\.)/g, "<br><br>$1");

    return text.trim();
}


// ======================
//  POPUP (FINAL)
// ======================
function showPopup(text){

    let popup = document.getElementById("popup");
    let content = document.getElementById("popupContent");

    if(typeof text !== "string"){
        content.innerText = text;
    }

    // =========================
    //  CORRECTION CASE (NEW STORY)
    // =========================
    else if(text.includes("Corrected User Story:")){

        let storyPart = text.split("Corrected User Story:")[1];

        let parts = storyPart.split("Reason:");

        let story = cleanText(parts[0]);
        let reason = parts[1] ? cleanText(parts[1]) : "";

        content.innerHTML = `
            <div style="font-size:14px; line-height:1.6;">
                <b style="color:#22c55e;">✨ Improved User Story:</b><br>
                ${story}<br><br>

                <b style="color:#f59e0b;">💡 Why this is better:</b><br>
                ${reason}
            </div>
        `;
    }

    // =========================
    //  OLD VALUE + REASON (fallback)
    // =========================
    else if(text.includes("Reason:")){

        let parts = text.split("Reason:");

        let values = cleanText(parts[0]);
        let reason = cleanText(parts[1]);

        content.innerHTML = `
            <div style="font-size:14px;">
                <b style="color:#22c55e;">✔ Values:</b><br>
                ${values}<br><br>

                <b style="color:#f59e0b;">💡 Reason:</b><br>
                ${reason}
            </div>
        `;
    }

    // =========================
    //  PURE REASON
    // =========================
    else{

        let clean = cleanText(text);

        content.innerHTML = `
            <div style="font-size:14px; line-height:1.6;">
                ${clean}
            </div>
        `;
    }

    popup.style.display = "flex";
}


// ======================
// CLOSE POPUP
// ======================
function closePopup(){
    document.getElementById("popup").style.display = "none";
}