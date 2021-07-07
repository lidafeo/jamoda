let pageRender = `<h1>Корзина</h1>
<hr>
<% if(cart.length == 0) {%>
    <div>Корзина пуста</div>
<%} else {%>
<h4><span id="str-count"><%=count%></span> товар(а) на сумму <span id="str-price"><%=price%></span> руб.</h4>
<br>
<div class="card-deck">
    <% for (let i = 0; i < cart.length; i++) {%>

        <div class="col-sm-6" id="<%=cart[i].article_clothes%>-<%=cart[i].size%>">
            <div class="card mb-4" style="max-width: 430px;">
                <div class="row no-gutters">
                    <% if (cart[i].image) {%>
                        <div class="col-md-5">
                            <a href="/clothes?article=<%=cart[i].article_clothes%>">
                                <img src="/img/<%=cart[i].image%>" height="100%" class="card-img img-fluid"  alt="<%=cart[i].name%>">
                            </a>
                        </div>
                        <div class="col-md-7">
                    <%} else {%>
                         <div class="col-md-12">
                    <%}%>
                        <div class="card-body">
                            <p class="card-text text-right">
                                <button type="button" class="close drop" aria-label="Close" data-article="<%=cart[i].article_clothes%>" data-size="<%=cart[i].size%>">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </p>
                            <p class="card-text"><a href="/clothes?article=<%=cart[i].article_clothes%>"><%=cart[i].category %> <b><%=cart[i].name%></b></a></p>
                            <p class="card-text">Размер - <%=cart[i].size%></p>
                            <p class="card-text"><%=cart[i].price%> руб. X <b id="count-pr"><%=cart[i].count%></b> шт. 
                             <% if (cart[i].count < 10) {%>
                                <a href="#" data-article="<%=cart[i].article_clothes%>" data-size="<%=cart[i].size%>" data-price="<%=cart[i].price%>" class="badge btn-secondary plus badge">+</a>
                             <%} else {%>
                                <a href="#" data-article="<%=cart[i].article_clothes%>" data-size="<%=cart[i].size%>" data-price="<%=cart[i].price%>" class="badge btn-secondary plus hide-block">+</a>
                             <%}%>
                             <% if (cart[i].count > 1) {%>
                                <a href="#" data-article="<%=cart[i].article_clothes%>" data-size="<%=cart[i].size%>" data-price="<%=cart[i].price%>" class="badge btn-secondary minus badge">-</a>
                             <%} else {%>
                                <a href="#" data-article="<%=cart[i].article_clothes%>" data-size="<%=cart[i].size%>" data-price="<%=cart[i].price%>" class="badge btn-secondary minus hide-block">-</a>
                             <%}%>
                                <hr><h3 id="price-sum"><%=(+cart[i].price) * (+cart[i].count)%> руб.</h3></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    <%}%>
</div>
<div class="row">
    <div class="col-md-5">
        <button type="button" id="in-order" class="btn btn-primary btn-lg">Перейти к оформлению</button>
    </div>
    <div class="col-md-4">
        <a href="/cart/clean" class="btn btn-primary btn-lg" id="clean-cart">Очистить корзину</a>
    </div>
</div>
<%}%>
<br>`;

let myCart = cartJS.getCart();
let rendered = ejs.render(pageRender, {
    cart: myCart,
    count: cartJS.getCommonCount(),
    price: cartJS.getCommonPrice()
});
let el = document.getElementById("cart-div");
el.innerHTML = rendered;

    /*
    ejs.renderAsync(
        '/templates/index.ejs',
        {
            cart: myCart,
            count: cartJS.getCommonCount(),
            price: cartJS.getCommonPrice()
        },
        function (err, rendered) {
            if (!err) {
                console.log(rendered);
                let el = document.getElementById("cart-div");
                el.innerHTML = rendered;
            } else {
                throw new Error('EJS template error!');
            }
        }
    );
}
 */

$("#cart-div").on('click', "#clean-cart", function (e) {
    e.preventDefault();
    cartJS.clearCart();
    window.location.href = "/cart";
});

$('#cart-div').on('click', "#in-order", function (e) {
    e.preventDefault();
    $.ajax({
        url: '/order_page',
        method: 'post',
        dataType: 'html',
        data: {count: cartJS.getCommonCount(),
                price: cartJS.getCommonPrice(),
                _csrf: $("#_csrf").val()},
        success: function(data){
            $('#cart-div').html(data);
        },
        error: function (err) {
            console.log(err);
            location.reload();
        }
    });
});

$("#cart-div").on('click', ".minus", function (e) {
    e.preventDefault();
    let product = {};
    product['article_clothes'] = $(this).data("article");
    product['size'] = $(this).data("size");
    cartJS.minusProductFromCart(product);
    cartJS.setCountInNavbar();
    $("#str-count").html(cartJS.getCommonCount());
    $("#str-price").html(cartJS.getCommonPrice());
    let count = cartJS.getProductInCart($(this).data("article"))[$(this).data("size")];
    $("#" + product['article_clothes'] + "-" + product['size'] + " #count-pr").html(count);
    $("#" + product['article_clothes'] + "-" + product['size'] + " #price-sum").html(($(this).data("price") * count) + " руб.");
    if(count == 1) {
        $(this).css("visibility", "hidden");
    }
    if(count <= 10) {
        $("#" + product['article_clothes'] + "-" + product['size'] + " .plus").css("visibility", "visible");
    }
});

$("#cart-div").on('click', ".plus", function (e) {
    e.preventDefault();
    let product = {};
    product['article_clothes'] = $(this).data("article");
    product['size'] = $(this).data("size");
    cartJS.plusProductFromCart(product);
    cartJS.setCountInNavbar();
    $("#str-count").html(cartJS.getCommonCount());
    $("#str-price").html(cartJS.getCommonPrice());
    let count = cartJS.getProductInCart($(this).data("article"))[$(this).data("size")];
    $("#" + product['article_clothes'] + "-" + product['size'] + " #count-pr").html(count);
    $("#" + product['article_clothes'] + "-" + product['size'] + " #price-sum").html(($(this).data("price") * count) + " руб.");
    if(count > 1) {
        $("#" + product['article_clothes'] + "-" + product['size'] + " .minus").css("visibility", "visible");
    }
    if(count >= 10) {
        $(this).css("visibility", "hidden");
    }
});

$("#cart-div").on('submit', "#order-form", function (e) {
    e.preventDefault();
    if($("#payment").val() == 'online') {
        return showPay();
    }
    $("#form-pay input").each(function(index) {
        $(this).attr("required", false);
    });
    sendFormPay();
});

$("#cart-div").on("click", ".drop", function (e) {
    let product = {};
    product['article_clothes'] = $(this).data("article");
    product['size'] = $(this).data("size");
    cartJS.deleteProductFromCart(product);
    if(cartJS.getCommonCount() == 0) {
        $('#cart-div').html("<h1>Корзина</h1><hr><div>Корзина пуста</div>");
    }
    else {
        $("#" + product['article_clothes'] + "-" + product['size']).remove();
        $("#str-count").html(cartJS.getCommonCount());
        $("#str-price").html(cartJS.getCommonPrice());
    }
    cartJS.setCountInNavbar();
});

$("#cart-div").on('click', "#clean-cart", function (e) {
    e.preventDefault();
    cartJS.clearCart();
    window.location.href = "/cart";
});

$(document).on("click", "#do_pay", function(e) {
    e.preventDefault();
    $("#form-pay").submit();
});

$(document).on("submit", "#form-pay", function(e) {
    e.preventDefault();
    if($("#form-pay")[0].checkValidity()) {
        $("#stripe-modal").modal('hide');
        sendFormPay();
    }
    else {
        $("#mess-pay").html("Введите данные");
    }
});

function showPay() {
    $("#mess-pay").html("");
    $("#form-pay input").each(function(index) {
        $(this).attr("required", true);
    });
    $("#stripe-modal").modal('show');
}

function sendFormPay() {
    debugger;
    console.log("jkj");
    $("#do-order").attr("disabled", true);
    let form = $("#order-form").serializeArray();
    let cart = cartJS.getCart();
    let cartArr = [];
    for(let i = 0; i < cart.length; i++) {
        //form.push({"name": "carts[" + "]", "value": JSON.stringify(cart[i])});
        cartArr.push({"article": cart[i]["article_clothes"],
            "size": cart[i]['size'],
            "count": cart[i]['count'],
            "price": cart[i]['price']});
    }
    let obj = {cart: cartArr, price: cartJS.getCommonPrice(), count: cartJS.getCommonCount()};

    form.push({"name": "mycart", "value": JSON.stringify(obj)});
    form.push({'name': "count", "value": cart.length + ""});
    $.ajax({
        url: '/order',
        method: 'post',
        dataType: 'json',
        data: $.param(form),
        success: function(data){
            console.log(data);
            let message = "";
            if(data['error'] != null && data['error'] != "") {
                message = data['error'];
                console.log(data['error'])
            }
            else if(data['message'] != null) {
                console.log(data['message']);
                message = '<div>Заказ успешно оформлен.</div>' +
                    '<div>Ваш номер заказа : <b>Z-' + data['message'] + '</b></div>' +
                    '<div>Скоро с Вами свяжется оператор.</div>';
                cartJS.clearCart();
            }
            $('#order-modal #message').html(message);
            $('#order-modal').modal("show");
        },
        error: function (err) {
            console.log(err);
        }
    });
}