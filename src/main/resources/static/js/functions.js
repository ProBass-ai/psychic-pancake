function objectId () {

    function hex (value) {
        return Math.floor(value).toString(16);
    }

    return hex(Date.now() / 1000) +
      ' '.repeat(16).replace(/./g, () => hex(Math.random() * 16));
}
  


async function createNewAccount(){

    return await fetch("http://localhost:8080/new-account", {
    method: "POST",
    headers: {
        "Content-Type": "application/json"
    },
    body: JSON.stringify(formData)
})
.then(res => res.json())
.then(response => {
    console.log("Success:", JSON.stringify(response));
    return JSON.stringify(response);
});

}

export {createNewAccount}