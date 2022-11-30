function getInfo() {
    fetch('http://localhost:8080/api/admin/users/')
        .then(res => res.json())
        .then(function getUserInfo(user) {
                let temp = '';
                temp += `<tr>
                    <td>${user.id}</td>
                    <td id=${'firstname' + user.id}>${user.firstname}</td>
                    <td id=${'lastname' + user.id}>${user.lastname}</td>
                    <td id=${'age' + user.id}>${user.age}</td>
                    <td id=${'email' + user.id}>${user.email}</td>
                    <td id=${'role' + user.id}>${user.role}</td>
                    </tr>`

                // Заливаем в DOM по айди
                document.getElementById("userInfo").innerHTML = temp;

            }
        )
}

getInfo();