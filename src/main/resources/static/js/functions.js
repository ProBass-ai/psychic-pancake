function objectId () {

    function hex (value) {
        return Math.floor(value).toString(16);
    }

    return hex(Date.now() / 1000) +
      ' '.repeat(16).replace(/./g, () => hex(Math.random() * 16));
}


async function cancelBooking(){

    //send a POST request to server with booking details in the body
    fetch("http://localhost:8080/cancel-booking", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(bookingDetails)
    })
    .then(res => res.json())
    .then(response => {
        console.log("Success:", JSON.stringify(response));
        alert("Booking cancelled successfully!");
    })
    .catch(error => {
        console.error("Error:", error);
        alert("Failed to cancel booking. Please try again later.");
    });


}


async function logUserIn(){
    const loginCredentials = {
        email : document.querySelector("#email").value,
        password : document.querySelector("#password").value
    }

    try {
        const response = await fetch("http://localhost:8080/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(loginCredentials)
        });
        const jsonResponse = await response.json();
        return jsonResponse;
    } catch (error) {
        console.error("Error:", error);
        throw error;
    }
}


async function createNewAccount() {

    const formData = {
        _id : objectId(),
        name: document.querySelector("#fname").value,
        surname: document.querySelector("#lname").value,
        email: document.querySelector("#new-user-email").value,
        phoneNumber: document.querySelector("#new-user-phone-number").value,
        idNumber: document.querySelector("#new-user-id-number").value
    };

    try {
        const response = await fetch("http://localhost:8080/new-account", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(formData)
        });
        const jsonResponse = await response.json();
        return jsonResponse;
    } catch (error) {
        console.error("Error:", error);
        throw error;
    }

}

function sendImageToServer(imageData) {
    const xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/upload", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send(`imageData=${encodeURIComponent(imageData)}`);
  }


export {createNewAccount, sendImageToServer, logUserIn}