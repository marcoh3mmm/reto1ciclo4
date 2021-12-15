function formularioUsuario() {
    let myData = {
        name: $("#name").val(),
        email: $("#email").val(),
        password: $("#password1").val(),
    };

    let passwordconfirm = $("#password2").val();

    if (
        myData.email == "" ||
        myData.name == "" ||
        myData.password == "" ||
        passwordconfirm == ""
    ) {
        let idalert = document.getElementById('alertvacia');
        let alertHTML = `
        <div class="alert alert-primary" role="alert">
            Llene todos los campos, no pueden estar vacios
        </div>
        `;
        idalert.innerHTML = alertHTML;
        return
    } else {
        let dataToSend = JSON.stringify(myData);

        if (myData.password == passwordconfirm) {
            consultarCorreo(dataToSend);
        } else {
            let idalert2 = document.getElementById('alertpass2');
            let idalert = document.getElementById('alertpass');
            let alertHTML = `
            <div class="alert alert-primary" role="alert">
                LAS CONTRASEÑAS NO COINCIDEN
            </div>
            `;
            idalert.innerHTML = alertHTML;
            idalert2.innerHTML = alertHTML;
            return
        }
    }
}

function consultarCorreo(dataToSend) {
    let email = $("#email").val();

    $.ajax({
        url: "http://localhost:8080/api/Registro/" + email,
        type: "GET",
        dataType: "json",
        success: function (respuesta) {
            if (respuesta) {
                let idalert = document.getElementById('alertemail');
                let alertHTML = `
                <div class="alert alert-primary" role="alert">
                    EL CORREO YA SE ENCUENTRA REGISTRADO O ES INCORRECTO
                </div>
                `;
                idalert.innerHTML = alertHTML;
                return
            } else {
                crearUsuario(dataToSend);
            }
        },
        error: function (e) {
            alert("error" + e);
        },
    });
}

function crearUsuario(dataToSend) {
    $.ajax({
        url: "http://localhost:8080/api/Registro/save",
        type: "POST",
        data: dataToSend,
        dataType: "json",
        contentType: "application/json",
        complete: function (respuesta) {
            alert("CUENTA CREADA DE FORMA CORRECTA INICIE SESION");
            window.location.href = "/login.html";
        }
    });
}
