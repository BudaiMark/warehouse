<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <title>Warehouse</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/login.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

</head>
<body>
<div class="block">
    <h2><i class="fa fa-user icon"></i> User Login </h2>
    <form action="login" method="post">
        <div class="username_input">
            <label for="username"></label><br>
            <i class='fa fa-user icon' id="username_icon"></i>
            <input type="text" id="username" name="username" placeholder="user name" onfocus="visibleIcon(this)"
                   onkeyup="visibleIcon(this)" onfocusout="makeIconVisible(this)"><br>
        </div>
        <div class="password_input">
            <label for="password"></label><br>
            <i class="fa fa-key icon" id="password_icon"></i>
            <input type="password" id="password" name="password" placeholder="password" onfocus="visibleIcon(this)"
                   onkeyup="visibleIcon(this)" onfocusout="makeIconVisible(this)"><br><br>
        </div>
        <c:if test = "${requestScope.error}">
            <p class="errormessage">Ervenytelen belepesi adatok!</p>
        </c:if>
        <div class="buttons">
            <input class="button" type="submit" value="Login">
            <input class="forgot_password_button" type="button" value="forgot password">
        </div>
    </form>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
<script type="text/javascript">
    function visibleIcon(e) {
        console.log(e);
        console.log(e.value);
        var id = e.id;
        var element = document.getElementById(id + "_icon");
        if (document.activeElement.id == id) {
            element.style.visibility = "hidden";
        } else {
            if (e.value == null || e.value.length == 0) {
                element.style.visibility = "visible";
                console.log("Add")
            } else {
                console.log("Remove")
                element.style.visibility = "hidden";
            }
        }


    }

    function makeIconVisible(e) {
        var id = e.id;
        console.log("itt");
        console.log(e.id);
        console.log(e.value.length);
        if (e.value.length > 0) {
            document.getElementById(id + "_icon").style.visibility = "hidden";
            console.log("elrejtem")
        }else{
            document.getElementById(id + "_icon").style.visibility = "visible";
            console.log("megjelenitem")
        }
    }


</script>
</body>
</html>