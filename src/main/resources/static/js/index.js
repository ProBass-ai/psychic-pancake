import * as event from "./events.js"
import * as functions from "./functions.js"
import * as pages from "./pages.js"


event.logEventListeners();


$(document).ready(
    function()
    {
        document.getElementById("create-new-account-form").addEventListener("click", function(event) {
            event.preventDefault();
            functions.createNewAccount();
            pages.renderNewAccPage();
          });
    }
);



