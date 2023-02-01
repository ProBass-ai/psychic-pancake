import * as pages from "./pages.js"


pages.renderHomePage();


export function logEventListeners() {

    document.getElementById("about-button").addEventListener("click", pages.renderAboutPage);

    document.getElementById("login-button").addEventListener("click", pages.renderLoginPage);

    document.getElementById("new-account-button").addEventListener("click", pages.renderCreateNewAccountPage);
}
