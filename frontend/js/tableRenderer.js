function renderTable(rows){

let tbody = document.querySelector("#resultTable tbody");
tbody.innerHTML = "";

rows.forEach((row, index)=>{

let tr = document.createElement("tr");

let actual = row.actualValues.map(v=>
`<span class="${v==='TRUE'?'badge-true':'badge-false'}">${v}</span>`
).join(" ");

let predicted = row.predictedValues.map(v=>
`<span class="${v==='TRUE'?'badge-true':'badge-false'}">${v}</span>`
).join(" ");

let match = row.matches.map(v=>
v ? `<span class="tick">✔</span>` : `<span class="cross">✖</span>`
).join("");

//  compact buttons (clean UI)
let actions = `
<div class="action-buttons">
<button class="reason-btn" onclick="handleReason(${index})">Reason</button>
<button class="fix-btn" onclick="handleCorrection(${index})">Correct</button>
</div>
`;

tr.innerHTML = `
<td>${row.userStory}</td>
<td>${actual}</td>
<td>${predicted}</td>
<td>${match}</td>
<td>${actions}</td>
`;

tbody.appendChild(tr);

});

window.currentRows = rows;
}