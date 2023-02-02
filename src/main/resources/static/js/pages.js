

export function renderHomePage(){
    var script = document.getElementById("main-page-template").innerHTML;
    var template = Handlebars.compile(script);
    document.getElementById("middle").innerHTML = template();
}

export function renderAboutPage(){
    var script = document.getElementById("about-template").innerHTML;
    var template = Handlebars.compile(script);
    document.getElementById("middle").innerHTML = template();
}

export function renderLoginPage(){
    var script = document.getElementById("login-template").innerHTML;
    var template = Handlebars.compile(script);
    document.getElementById("middle").innerHTML = template();
}

export function renderCreateNewAccountPage(){
    var script = document.getElementById("new-account-template").innerHTML;
    var template = Handlebars.compile(script);
    document.getElementById("middle").innerHTML = template();
}

export function renderNewAccPage(){
    var script = document.getElementById("after-submission-template").innerHTML;
    var template = Handlebars.compile(script);
    document.getElementById("middle").innerHTML = template();
}