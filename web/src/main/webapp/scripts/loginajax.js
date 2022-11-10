function loginajaxfunc() {

    $(document).ready(function () {

            console.log("valami");
            /*
            *Lekérdezzük a username id-val és a password id-val rendelkező label értékét.
            */
            var username = $("#username").val();
            var userpassword = $("#password").val();
            /*
            * Elküldjük az login-async servletnek az adatokat,
            */
            $.ajax({
                url: "/web_war/login-async",
                type: "POST",
                dataType: "json",
                data: {name: username, password: userpassword},
                /*
                * Ha sikeres a válasz átirányitunk a dashboardra.
                */
                success: function (data) {
                    //$.redirect("/web_war/secured", { name: username, password: userpassword, authenticated: true })
                    window.location.href="/web_war/dashboard";

                },
                /*
                * Sikertelenség esetén jelezzük a hibát.
                */
                error: function (error) {
                    console.log("Error:");
                    console.log(error);
                }
            });
        }
    )
}