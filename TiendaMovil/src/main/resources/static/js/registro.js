//Validación al registrar.
$("#register").click(function () {
    var name = $("#nombre").val();
    var email = $("#correo").val();
    var cemail = $("#repetCorreo").val();
    var password = $("#contrasenya").val();
    var cpassword = $("#repetContrasenya").val();
    var emailRegex = /^[-\w.%+]{1,64}@(?:[A-Z0-9-]{1,63}\.){1,125}[A-Z]{2,63}$/i;
    if (name == '' || email == '' || cemail == '' || password == '' || cpassword == '') {
        toastr["error"]("Por favor, rellena todos los campos.");
        return false;
    } else if (!emailRegex.test(email)) {
        toastr["error"]("El email no tiene el formato adecuado.");
        return false;
    } else if (!(email).match(cemail)) {
        toastr["error"]("Tus emails no coinciden.");
        return false;
    } else if (!(password).match(cpassword)) {
        toastr["error"]("Tus contraseñas no coinciden.");
        return false;
    }
});