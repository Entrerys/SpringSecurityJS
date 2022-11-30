function userAuthInfo() {
    fetch('http://localhost:8080/api/user')
        .then(res => res.json())
        .then(function getInfo(user) {
            document.getElementById("userInfoMain").innerHTML =
                `<tr>
            <td>${user.id}</td>
            <td>${user.firstname}</td>
            <td>${user.lastname}</td>
            <td>${user.age}</td>
            <td>${user.email}</td>
            <td>${user.role}</td>
            </tr>`;
            document.getElementById("userInfo").innerHTML = `<b>${user.email} with roles: ${user.role}</b>`
        });
}

userAuthInfo()

