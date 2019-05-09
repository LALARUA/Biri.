




$(document).on('click', '.addBookToCart', function () {
    var bookId = $(this).attr("bookId");
    var price = $(this).attr("price");
    var title = $(this).attr("bookName");
    var imagePath = $(this).attr("imagePath");
    var stock = $(this).attr("stock");
    var cartBookIds = $(".miniCartBookId");
    for (var i = 0;i < cartBookIds.length;i++){
        if (bookId == cartBookIds.eq(i).val()){
            alert("已加入购物车");
            return;
        }
    }
    $.ajax({
        url: "/orderCart/cart",
        type: 'post', //
        async: true,    //或false,是否异步
        data: JSON.stringify({
            id:bookId,
            // price:price,
            // title:title,
            // imagePath:imagePath,
            // stock:stock,
            bookNum:1,
        }),
        contentType:"application/json;charset=utf-8",
        timeout: 50000,    //超时时间
        dataType: 'text',    //返回的数据格式：json/xml/html/script/jsonp/text
        success: function (data) {
            if (data=='mustLogin'){
                window.location.href = '/Biri/login';
            }
            else if (data='success'){
                $("#miniCart .mini-cart__list").eq(0).append(" <li  class=\"mini-cart__product\">\n" +
                    "                        <a  cartId="+data+" href=\"#\" class=\"miniCartDeleteBook remove-from-cart remove\">\n" +
                    "                            <i class=\"dl-icon-close\"></i>\n" +
                    "                        </a>\n" +
                    "                        <div class=\"mini-cart__product__image\">\n" +
                    "                            <img src=\"/bookImg/"+imagePath+"\" alt=\"products\">\n" +
                    "                        </div>\n" +
                    "                        <div class=\"mini-cart__product__content\">\n" +
                    "                            <a class=\"mini-cart__product__title\" href=\"'/Biri/book/detail/'"+bookId+"\">"+title+" </a>\n" +
                    "                            <span class=\"mini-cart__product__quantity\" ><span  class=\"miniCartBookNum\">1</span> x ￥<span class=\"miniCartBookPrice\">"+price+"</span></span>\n" +
                    "                        </div>\n" +
                    "                    </li>")
                $("#miniCartCount").eq(0).text( parseInt($("#miniCartCount").text())+1);
                alert("已加入购物车")
            }


        },
        error: function (data) {

        }
    })

})
$(document).on('click', '.addBookToWishList', function () {
    var bookId = $(this).attr("bookId");
    $.ajax({
        url: "/orderCart/cart",
        type: 'post', //
        async: true,    //或false,是否异步
        data: JSON.stringify({
            bookId:bookId,
        }),
        contentType:"application/json;charset=utf-8",
        timeout: 50000,    //超时时间
        dataType: 'text',    //返回的数据格式：json/xml/html/script/jsonp/text
        success: function (data) {
            if (data=='mustLogin'){
                window.location.href = '/Biri/login';
            }
            else if (data='success'){
                alert("已加入愿望清单")
            }
        },
        error: function (data) {

        }
    })


})
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
$(document).on('click', '#loadCart', function () {
    alert(5)
    var flag = $("#isLoadCart").val();
    if (flag==1)
        return false

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




