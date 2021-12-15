function loginUsuario() {
    let email = $("#emailLog").val();
    let contrasena = $("#passwordLog").val();
    if (email == "" || contrasena == "") {
        // alert("Llene todos los campos, no pueden estar vacios");
        let idalert = document.getElementById('alertvacia');
        let alertHTML = `
        <div class="alert alert-primary" role="alert">
            Llene todos los campos, no pueden estar vacios
        </div>
        `;
        idalert.innerHTML = alertHTML;
        return
    } else {
        $.ajax({
            url: "http://localhost:8080/api/Registro/" + email + "/" + contrasena,
            type: "GET",
            dataType: "json",
            success: function (respuesta) {
                
                if (respuesta.id != null) {
                    alert(`Bienvenido ${respuesta.name}`);
                } else {
                    let idalert = document.getElementById('alertvacia');
                    let alertHTML = `
                    <div class="alert alert-primary" role="alert">
                    El usuario no se encuentra, intente de nuevo o cree una cuenta si no la tiene
                    </div>
                    `;
                    idalert.innerHTML = alertHTML;
                    return
                }
            }
        });
    }
}
