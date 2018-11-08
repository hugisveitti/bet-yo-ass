var yourOdds = document.getElementById("your-odds");
var oppOdds = document.getElementById("opponent-odds");

var yourAmount = document.getElementById("your-amount");
var oppAmount = document.getElementById("opponent-amount");


yourOdds.addEventListener("keyup", calcOdds);
yourAmount.addEventListener("keyup", calcOdds);


function calcOdds(e) {

    if (yourAmount.value === "" || yourOdds.value === "") {
        oppOdds.innerHTML = "";
        oppAmount.innerHTML = "";
        return;
    }

    //cant have odds less then 1
    if(parseFloat(yourOdds.value) <= 1.0 || parseFloat(yourAmount.value) <= 0){
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

    if(Math.ceil(oOdds) - oOdds <= 0.01){
        oOdds = Math.ceil(oOdds);
    }


    var oAmount  = Math.floor(parseFloat(yourAmount.value) * parseFloat(yourOdds.value) / parseFloat(oOdds) * 100) /100;


    oppOdds.innerHTML = oOdds;
    oppAmount.innerHTML = oAmount;
}
