




var initCartTotal=function () {
    var prices = $(".miniCartBookPrice");
    var bookNums = $(".miniCartBookNum")
    var total = 0;

    for (var i = 0;i<prices.length;i++){
        total+=parseInt(prices.eq(i).text())*bookNums.eq(i).text();
    }
    $("#miniCartTotal").text(total)
}
$(document).on('click', '.miniCartDeleteBook', function () {
   var bookNum = $(this).parent().find(".miniCartBookNum").eq(0).text();
   var bookPrice = $(this).parent().find(".miniCartBookPrice").eq(0).text();
   var total = $("#miniCartTotal").text();
   total = total - (bookPrice*bookNum);
   var cartId = $(this).attr("cartId");
   $(this).parent().remove()
    $("#miniCartTotal").text(total);
    $("#miniCartCount").eq(0).text( parseInt($("#miniCartCount").text())-1)

    $.ajax({
        url: "/orderCart/cart",
        type: 'delete', //
        async: true,    //或false,是否异步
        data: {
            cartId:cartId,
        },
        timeout: 50000,    //超时时间
        dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success: function (data) {

        },
    })
})




