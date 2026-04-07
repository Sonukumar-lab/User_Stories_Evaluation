function getSelectedFile(){

let input = document.getElementById("excelFile");

if(input.files.length === 0){

alert("Please upload Excel file");
return null;

}

return input.files[0];

}