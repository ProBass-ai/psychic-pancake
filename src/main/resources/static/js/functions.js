function objectId () {

    function hex (value) {
        return Math.floor(value).toString(16);
    }

    return hex(Date.now() / 1000) +
      ' '.repeat(16).replace(/./g, () => hex(Math.random() * 16));
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


export {createNewAccount}