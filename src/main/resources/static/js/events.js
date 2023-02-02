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


        var userDetails = JSON.parse(functions.createNewAccount());

          const context = {
            name : userDetails.name,
            surname : userDetails.surname,
            email : userDetails.email,
            phoneNumber : userDetails.phoneNumber,
            idNumber : userDetails.idNumber
    
        };

      //   const context = {
      //     name : "Tebogo",
      //     surname : "Lefatola",
      //     email : "tb@gmail.com",
      //     phoneNumber : "1234568",
      //     idNumber : "12345678"
  
      // };

          // const context = functions.createNewAccount();

          pages.renderNewAccPage(context);
        });
      }

    });

    
}


