$(document).ready(function() {

    $("#inpLoginEmail").keypress(function(e) {
        if (e.keyCode == 13)
            $("#inpLoginPassword").focus();
    });

    $("#inpLoginPassword").keypress(function(e){
        if(e.keyCode == 13)
            login();
    });

	$("#btnInput").click(function(){
        login();
    });

    $("#inpLoginEmail").focus(function(){ $("#errLog").attr("style", "display: none")});
    $("#inpLoginPassword").focus(function(){ $("#errLog").attr("style", "display: none")});
});

function LoginModel(email, password, keepMeLoggedIn) {
    this.email = email;
    this.password = password;
    this.keepMeLoggedIn = keepMeLoggedIn;
}

function login(){
    var wm = new LoginModel(
        $("#inpLoginEmail").val(),
        $("#inpLoginPassword").val(),
        $('#inpLoginKeep').prop("checked")
    );

    $.postJSON("login", wm, function(data) {
        if (data.value == "success" && !hasError(data)) {
            //alert("Success");
            window.location = data.url;
        } else if (data.value == "wrong"){
            $("#errLog").attr("style", "display: block");
        }
    });
};