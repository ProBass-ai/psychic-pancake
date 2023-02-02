import * as pages from "./pages.js"
import * as functions from "./functions.js"


pages.renderHomePage();


export function logEventListeners() {

    document.getElementById("about-button").addEventListener("click", pages.renderAboutPage);

    document.getElementById("login-button").addEventListener("click", pages.renderLoginPage);

    document.getElementById("new-account-button").addEventListener("click",

     function(){
      
      pages.renderCreateNewAccountPage();

      let createNewAccountForm = document.getElementById("create-new-account-form");
      if (createNewAccountForm) {
        createNewAccountForm.addEventListener("click", function(event) {
          event.preventDefault();
          functions.createNewAccount();
          pages.renderNewAccPage();
        });
      }

    });

    
}


