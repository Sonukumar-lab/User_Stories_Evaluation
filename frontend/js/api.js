async function evaluateExcel(file, model){

let formData = new FormData();
formData.append("file", file);
formData.append("model", model);

let response = await fetch(
CONFIG.BASE_URL + CONFIG.ENDPOINTS.EVALUATE,
{
method: "POST",
body: formData
}
);

if(!response.ok) throw new Error("Server Error");

return await response.json();
}


//  NEW APIs

async function getReason(data){

let res = await fetch(
CONFIG.BASE_URL + "/reason",
{
method:"POST",
headers:{ "Content-Type":"application/json" },
body: JSON.stringify(data)
}
);

return await res.text();
}


async function getCorrection(data){

let res = await fetch(
CONFIG.BASE_URL + "/correction",
{
method:"POST",
headers:{ "Content-Type":"application/json" },
body: JSON.stringify(data)
}
);

return await res.text();
}