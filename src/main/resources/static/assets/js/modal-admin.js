var reason = document.querySelector("#reason");
var accId = document.querySelector("#send");
var afk = document.getElementById("afk-field");

$('.verify').click(function(){
    var fullRow = $(this).val();
    reason.value= "";
    afk.style.display="none";
    var activeStatus = document.getElementById(fullRow);

    if(activeStatus.innerText === "Active"){
        afk.style.display="block";
    }
    accId.value = fullRow;
});