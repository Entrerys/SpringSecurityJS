function deleteModal(id) {
    fetch('http://localhost:8080/api/admin/' + id)
        .then(res => res.json()
            .then(user => {
                document.getElementById("firstdel").value = user.firstname;
                idForDelete = document.getElementById("firstdel").value = user.id;
                document.getElementById('lastdel').value = user.lastname;
                document.getElementById('agedel').value = user.age;
                document.getElementById('emaildel').value = user.email;
            })
        );
}

function deleteUser() {
    fetch('http://localhost:8080/api/admin/' + idForDelete, {
        method: "DELETE",
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify(document.getElementsByName('user.username'))
    })
        .then(getAllUsers)
    document.getElementById("delete").click();
}



