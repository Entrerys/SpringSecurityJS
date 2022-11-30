function adminInfo() {
    fetch("/api/admin/info")
        .then(res => res.json())
        .then((user) => {
            document.getElementById("adminInfo").innerHTML =
                `<b>${user.email} with roles: ${user.role}</b>`
        })
}

adminInfo()

function getAllUsers() {
    fetch('http://localhost:8080/api/admin/users/')
        .then(res => res.json())  //Преобразуем формат
        .then(function getInfo(users) { //Вытаскиваем инфо через функцию
                let temp = '';
                users.forEach((user) => {
                    temp += `<tr>
                                <td>${user.id}</td>
                                <td id=${'firstname' + user.id}>${user.firstname}</td>
                                <td id=${'lastname' + user.id}>${user.lastname}</td>
                                <td id=${'age' + user.id}>${user.age}</td>
                                <td id=${'email' + user.id}>${user.email}</td>
                                <td id=${'role' + user.id}>${user.role}</td>
                                <td>
                                <button class="btn btn-info" data-bs-toggle="modal" 
                                data-bs-target="#modalEdit"
                                onclick="editModal(${user.id})">Edit</button></td>
                                <td>
                                <button class="btn btn-danger" data-bs-toggle="modal" 
                                data-bs-target="#modalDelete"
                                onclick="deleteModal(${user.id})">Delete</button>
                                </td>
                                </tr>`
                })
                // Заливаем в DOM по айди
                document.getElementById("allUsers").innerHTML = temp;
            }
        )
}

getAllUsers();










