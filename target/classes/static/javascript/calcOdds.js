var yourOdds = document.getElementById("your-odds");
var oppOdds = document.getElementById("opponent-odds");
var yourOddsPercentage = document.getElementById("your-odds-percentage");

var yourAmount = document.getElementById("your-amount");
var oppAmount = document.getElementById("opponent-amount");

var submit = document.getElementById("pending-bet-button");

yourOdds.addEventListener("keyup", calcOdds);
yourAmount.addEventListener("keyup", calcAmount);


function calcOdds(e) {

    if (yourAmount.value == "" || yourOdds.value == "") {
        oppOdds.innerHTML = "";
        oppAmount.innerHTML = "";
        return;
    }

    //cant have odds less then 1
    if(parseFloat(yourOdds.value) <= 1.0){
        oppOdds.innerHTML = "";
        oppAmount.innerHTML = "";
        return
    }



    var odds = e.target.value;
    //todo if browser not support number in input

    var likur = Math.floor((1 / parseFloat(odds)) * 100 * 100) / 100;
    var oOdds = Math.floor((1 / (100.0 - likur)) * 100 * 100) / 100;

    //console.log("you have " + likur + "% chance of winning");
    //yourOddsPercentage.innerHTML = "you have " + likur + "% chance of winning";
    //oppOdds.innerHTML = oOdds + " (" + (100 - likur) + "%)";
    oppOdds.innerHTML = oOdds;
    oppAmount.innerHTML = Math.floor(parseFloat(yourAmount.value) * parseFloat(yourOdds.value) / parseFloat(oppOdds.innerHTML) * 100) /100;
}

function calcAmount(e) {

    if (yourAmount.value == "" || yourOdds.value == "") {
        oppOdds.innerHTML = "";
        oppAmount.innerHTML = "";
        return;
    }

    //cant have odds less then 1
    if(parseFloat(yourOdds.value) <= 1.0){
        oppOdds.innerHTML = "";
        oppAmount.innerHTML = "";
        return
    }

    oppAmount.innerHTML =  Math.floor(parseFloat(yourAmount.value) * parseFloat(yourOdds.value) / parseFloat(oppOdds.innerHTML) *100)/100;
}
