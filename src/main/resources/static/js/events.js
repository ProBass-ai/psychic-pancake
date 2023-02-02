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

          functions.createNewAccount().then(userDetails => {

            console.log(userDetails);

            // var userDetails = functions.createNewAccount();

            const context = {
              name : userDetails.name,
              surname : userDetails.surname,
              email : userDetails.email,
              phoneNumber : userDetails.phoneNumber,
              idNumber : userDetails.idNumber
            };

            pages.renderNewAccPage(context);

          });
          
        });
      }

    });


    // const inputImage = document.getElementById("inputImage");

    // inputImage.addEventListener("change", () => {
    //   const reader = new FileReader();
    //   reader.readAsDataURL(inputImage.files[0]);
    //   reader.onload = function () {
    //     const imageData = reader.result;
    //     functions.sendImageToServer(imageData);
    //   };

    //   });

    
}


