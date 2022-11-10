function productajaxfunc() {
    $(document).ready(function () {

            $.ajax({
                url: "/web_war/api/ProductService/getAsyncProducts",
                type: "GET",
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
                error: function (error) {

                }
            });
        }
    )
}