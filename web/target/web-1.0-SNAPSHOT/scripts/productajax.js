function productajaxfunc() {
    $(document).ready(function () {

            $.ajax({
                url: "/web_war/api/ProductService/getAsyncProducts",
                type: "GET",
                success: function (datas) {
                    console.log("eleje")
                    $("#productsbody").empty();
                    console.log(datas)
                    console.log("vege")
                    $.each(datas, function(i, d) {
                        var row='<tr>';
                        $.each(d, function(j, e) {
                            row+='<td>'+e+'</td>';
                        });
                        row+='</tr>';
                        $('#productsbody').append(row);
                    });

                    console.log("visszatöltött")
                },
                error: function (error) {

                }
            });
        }
    )
}