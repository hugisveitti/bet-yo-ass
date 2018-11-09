var yourOdds = document.getElementById("your-odds");
var oppOdds = document.getElementById("opponent-odds");

var yourAmount = document.getElementById("your-amount");
var oppAmount = document.getElementById("opponent-amount");

var userCredits = document.getElementById("user-credits").innerHTML;
var creditsErrors = document.getElementsByClassName("credits-error");
var submitBtns = document.getElementsByClassName("submit");

console.log(submitBtns);
yourOdds.addEventListener("keyup", calcOdds);
yourAmount.addEventListener("keyup", calcOdds);

var yourPendingAmount = document.getElementsByClassName("your-pending-amount");
console.log(yourPendingAmount);
for(var i=0;i<yourPendingAmount.length; i++){
    if(parseFloat(yourPendingAmount[i].innerHTML) > parseFloat(userCredits)){
        var creErr = yourPendingAmount[i].nextElementSibling;
        console.log(creErr);
        console.log(yourPendingAmount[i]);
        creErr.innerHTML = "You don't have enough credits to accept this bet.";
        var accBtn = creErr.nextElementSibling;
        accBtn.setAttribute("disabled", true);
        accBtn.style.backgroundColor = "gray";
    }
}


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

    console.log(userCredits);
    console.log(yourAmount.value);
    if(parseFloat(yourAmount.value) > parseFloat(userCredits)){
        console.log('credtis errors' + creditsErrors);
        console.log(this.parentElement);
        for(var i=0; i<creditsErrors.length;i++){
            if(this.parentElement === creditsErrors[i].parentElement){
                creditsErrors[i].innerHTML = "Not enough credits to make this bet";
            }
        }

        for(var i=0; i<submitBtns.length; i++){
            console.log(submitBtns[i].parentElement);
            if(this.parentElement === submitBtns[i].parentElement){

                // submitBtns[i].setAttribute("disabled", true);
                submitBtns[i].disabled= true;
                // console.log(submitBtns[i]);
                // console.log(submitBtns[i].style["backgroundColor"]);
                // console.log(submitBtns[i].style["background-color"]);
                //kannski einhvern veginn hægt að geyma gamle litinn, því ef við viljum breyta litnum þá þurfum við að breyta líka hér
                submitBtns[i].oldColor = "red";
                submitBtns[i].style.backgroundColor = "Gray";
            }
        }

        return;
    } else {

        for(var i=0; i<creditsErrors.length;i++){
            if(this.parentElement === creditsErrors[i].parentElement){
                creditsErrors[i].innerHTML = "";
            }
        }


        for(var i=0; i<submitBtns.length; i++){
            if(this.parentElement === submitBtns[i].parentElement){
                // submitBtns[i].setAttribute("disabled", false);
                submitBtns[i].disabled= false;
                submitBtns[i].style.backgroundColor = "rgba(82, 29, 29, 0.8)";
            }

        }
    }

    //todo if browser not support number in input

    var likur = Math.round((1 / parseFloat(yourOdds.value)) * 100 * 100) / 100;
//    var oOdds = Math.floor((1 / (100.0 - likur)) * 100 * 100) / 100;
    var oOdds = Math.round((1 / (100.0 - likur)) * 100 * 100) / 100;

    console.log("oOdss pre " + oOdds);


    console.log(oOdds);

    var oAmount  = Math.floor(parseFloat(yourAmount.value) * parseFloat(yourOdds.value) / parseFloat(oOdds) * 100) /100;


    oppOdds.innerHTML = oOdds;
    oppAmount.innerHTML = oAmount;
}
