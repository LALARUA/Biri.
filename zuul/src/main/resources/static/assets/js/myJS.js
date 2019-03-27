




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
$(document).on('click', '.qtybutton', function () {
    var total = $(this).parent().parent().parent().find(".total").eq(0);
    var price = $(this).parent().parent().parent().find(".bookPrice").eq(0).text();
    var allTotal = $("#allTotal");
    if ($(this).text() == '+'){
        total.text(parseInt(total.text()) + parseInt(price));
        allTotal.text(parseInt(allTotal.text()) + parseInt(price))
    }
    if ($(this).text() == '-'){
        total.text(parseInt(total.text()) - parseInt(price));
        allTotal.text(parseInt(allTotal.text()) - parseInt(price))
    }
})



