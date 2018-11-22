/*
 Browsers like firefox do not support date-time-local html5 input
 This makes the user type in the correct format himself.
 We do this so that the time resolved has no problem with checking.
 */

if(!browserSupportsDatetimeLocal()){
    var notSupportMsg = document.getElementById("date-time-not-supported");
    notSupportMsg.hidden = false;
    var dtLocal = document.getElementById("datetime-local");
    var submitBtn = document.getElementById("submit-btn");
    dtLocal.addEventListener("input", function(e){
        //yyyy-mm-ddThh:mm regular expression
        var reg = /^[0-9][0-9][0-9][0-9]-(0[1-9]|1[12])-(0[1-9]|[12][0-9]|3[01])T([0-1][0-9]|2[0-4]):[0-5][0-9]$/;
        // var reg = /^(0[1-9]|[12][0-9]|3[01])-(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)-[0-9][0-9][0-9][0-9]T([0-1][0-9]|2[0-4]):[0-5][0-9]$/;
        var patt = new RegExp(reg);
        if(!patt.test(this.value)){
            submitBtn.disabled = true;
            submitBtn.style.backgroundColor = "gray";
        } else {
            submitBtn.disabled = false;
            submitBtn.style.backgroundColor = "rgba(82, 29, 29, 0.8)";

        }
    })
}
function browserSupportsDatetimeLocal() {
    var input = document.createElement('input');
    input.setAttribute('type','datetime-local');

    var notADateValue = 'not-a-date';
    input.setAttribute('value', notADateValue);

    return (input.value !== notADateValue);
}