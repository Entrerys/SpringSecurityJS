function createUser() {

    var selObj = document.getElementById("rolesCreate");

    let userCreate = {
        username: document.getElementById("username").value,
        firstname: document.getElementById("firstName").value,
        lastname: document.getElementById("lastName").value,
        age: document.getElementById("ageCreate").value,
        email: document.getElementById("email").value,
        password: document.getElementById("password").value,
        role: selObj.options[selObj.selectedIndex].value
    }

    fetch('http://localhost:8080/api/admin/create/', {
        method: "POST",
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify(userCreate)
    })
        .then(() => {
            window.location = "http://localhost:8080/admin/"
        })

}


