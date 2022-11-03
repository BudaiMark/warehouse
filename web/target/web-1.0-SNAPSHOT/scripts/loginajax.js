function loginajaxfunc() {
    $(document).ready(function () {

            console.log("valami");

            var username = $("#username").val();
            var userpassword = $("#password").val();


            $.ajax({
                url: "/web_war/login-async",
                type: "POST",
                dataType: "json",
                data: {name: username, password: userpassword},
                success: function (data) {
                    console.log("eleje")
                    //$.redirect("/web_war/secured", { name: username, password: userpassword, authenticated: true })
                    window.location.href="/web_war/dashboard";
                    console.log("vege")

                },
                error: function (error) {
                    console.log("Error:");
                    console.log(error);
                }
            });
        }
    )
}