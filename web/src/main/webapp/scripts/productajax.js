function productajaxfunc() {
    $(document).ready(function () {

            $.ajax({
                url: "/web_war/api/ProductService/getAsyncProducts",
                type: "GET",
                /*
                *   Ha sikeres a válasz felépítjük a táblázatot a termékekből
                */
                success: function (datas) {
                    $("#productsbody").empty();
                    $.each(datas, function(i, d) {
                        var row='<tr>';
                        $.each(d, function(j, e) {
                            row+='<td>'+e+'</td>';
                        });
                        row+='</tr>';
                        $('#productsbody').append(row);
                    });

                },
                /*
                *  Sikertelenség esetén jelezzük a hibát.
                */
                error: function (error) {
                    console.log("Error:");
                    console.log(error);
                }
            });
        }
    )
}