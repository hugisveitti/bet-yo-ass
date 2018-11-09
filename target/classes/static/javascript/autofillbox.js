
//dropdown fyrir opponent users
var getUsers = document.getElementsByClassName("get-users");
var opponent = document.getElementById("opponent");
opponent.addEventListener("keyup", opponentDropdown);
opponent.style.marginBottom = "0";
opponent.style.paddingBottom = "0";

var usersDropdown = document.getElementById("users-dropdown");


var users = [];
for(var i=0;i<getUsers.length;i++){
    users.push(getUsers[i].innerHTML);
}
function opponentDropdown(e){
    closeDropdown();

    var a = document.createElement("DIV");
    a.setAttribute("id", "autocomplete-list");
    a.setAttribute("class", "autocomplete-items");
    this.parentNode.appendChild(a);

    //users that match the input
    var matchUsers = [];




    for(var i=0; i<users.length; i++){
        if(e.target.value.toUpperCase() === users[i].substring(0, e.target.value.length).toUpperCase() && e.target.value.length > 0){
            matchUsers.push(users[i]);
        }
    }
    for(var i=0; i<matchUsers.length; i++){
        var item = document.createElement("DIV");
        item.setAttribute("class", "autocomplete-text")

        item.innerHTML = "<b>" + matchUsers[i].substr(0,e.target.value.length) + "</b>";
        item.innerHTML += matchUsers[i].substr(e.target.value.length);
        //item.innerHTML = matchUsers[i];

        //item.value = matchUsers[i];
        item.innerHTML += "<input type='hidden' value='" + matchUsers[i] + "'>";
        item.addEventListener("click", function(e) {

            console.log(e);
            console.log(this)
            opponent.value = this.getElementsByTagName("input")[0].value;
            closeDropdown();
        });
        a.appendChild(item);
    }
}

function closeDropdown(e){
    var x = document.getElementsByClassName("autocomplete-items");
    for(var i=0; i<x.length;i++){
        x[i].parentNode.removeChild(x[i]);
    }
}