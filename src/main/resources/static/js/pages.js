

export function renderHomePage(){
    let script = document.getElementById("main-page-template").innerHTML;
    let template = Handlebars.compile(script);
    document.getElementById("middle").innerHTML = template();
}

export function renderAboutPage(){
    let script = document.getElementById("about-template").innerHTML;
    let template = Handlebars.compile(script);
    document.getElementById("middle").innerHTML = template();
}

export function renderLoginPage(){
    let script = document.getElementById("login-template").innerHTML;
    let template = Handlebars.compile(script);
    document.getElementById("middle").innerHTML = template();
}

export function renderCreateNewAccountPage(){
    let script = document.getElementById("new-account-template").innerHTML;
    let template = Handlebars.compile(script);
    document.getElementById("middle").innerHTML = template();
}

export function renderAfterLoginPage(data){

    let script = document.getElementById("after-login-template").innerHTML;

    let template = Handlebars.compile(script);

    let compiledHtml = template(data);

    document.getElementById("middle").innerHTML = compiledHtml;

  }


  