function editModal(id) {
    fetch('http://localhost:8080/api/admin/' + id)
        .then(res => res.json()
            .then(user => {
                document.getElementById("ided").value = `${user.id}`;
                document.getElementById("uname").value = `${user.username}`;
                document.getElementById('firsted').value = `${user.firstname}`;
                document.getElementById('lasted').value = `${user.lastname}`;
                document.getElementById('aged').value = `${user.age}`;
                document.getElementById('emailed').value = user.email;
                document.getElementById('passed').value = user.password;
            })
        );
}

var selObj = document.getElementById("rolesed");

function editUser() {

    let user = {
        id: document.getElementById("ided").value,
        username: document.getElementById("uname").value,
        firstname: document.getElementById("firsted").value,
        lastname: document.getElementById("lasted").value,
        age: document.getElementById("aged").value,
        email: document.getElementById("emailed").value,
        password: document.getElementById("passed").value,
        role: selObj.options[selObj.selectedIndex].value
    }

    fetch('http://localhost:8080/api/admin/edit/', {
        method: "PUT",
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify(user)
    })
        .then(getAllUsers)
        .then(() => {
            window.location = "http://localhost:8080/admin/"
        })

}