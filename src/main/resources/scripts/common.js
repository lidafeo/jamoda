//модальное окно выбора размера
$('#card-deck-id').on('click', ".but-buy", function (event) {
    event.preventDefault();
    //$(this).modal('hide');
    let button = $(this); // Button that triggered the modal
    clickedBut = button;
    let recipient = button.data('whatever'); // Extract info from data-* attributes;
    let sizesObj = getSizes(button, recipient);
    let select = "";
    for(let i = 40; i <= 52; i += 2) {
        if(!sizesObj["" + i] || sizesObj["" + i] == 0) {
            select += '<option value="' + i + '" class="disable-option" disabled>' + i + '</option>';
        }
        else {
            select += '<option value="' + i + '">' + i + '</option>';
        }
    }
    $("#size").html(select);
    let modal = $("#modal-size");
    console.log(recipient);
    modal.find('.modal-body #modal-article').val(recipient);
    $("#modal-size").modal('show');
});

function updateSizeAtPage() {
    let productCart = cartJS.getProductInCart($("#article").attr("value"));
    let first = false;
    let finded = false;
    $("#size option").each(function(index) {
        let countInCart = productCart[$(this).attr("value")];
        if(countInCart == null || countInCart == "") {
            countInCart = 0;
        }
        if(+$(this).data("count") - +countInCart <= 0) {
            finded = true;
            $(this).attr("disabled", true);
            $(this).addClass("disable-option");
            $(this).attr("selected", false);
        }
        else if(!first) {
            first = $(this);
        }
    });
    if(finded && first) {
        first.attr("selected", true);
        $("#size").html($("#size").html());
    }
    if(!first) {
        $("#add-in-cart").attr("disabled", true);
        $("#size-div").html('<div class="alert alert-danger col-sm-10" role="alert">К сожалению товара нет в наличии</div>')
    }
}


//$(document).ready(function() {
/*
    if ($("#order-modal") != null)
        $('#order-modal').modal('show');

 */

    //панель каталога
    $('#navbarDropdown').click(function (e) {
        if ($("#catalog").css('visibility') == "hidden") {
            $("#catalog").css("visibility", "visible");
        } else {
            $("#catalog").css("visibility", "hidden");
        }
    });

    //сортировка
    $('#sort').change(function (e) {
        let params = getUrlVars();
        sendRequest(params);
    });

    $('#clothes_div').on('click', '.but-buy', function (e) {
        e.preventDefault();
        let form = $(this).parents('form');
    });
/*
    //переход в корзину
    $('#cart-li').on('click', '#cart', function (e) {
        e.preventDefault();
        let cart = cartJS.getCart();
        console.log("Заходим в корзину");
        console.log(cart);
        $.ajax({
            url: '/cart',
            method: 'post',
            dataType: 'json',
            data: {cart: cart, _csrf: $("#_csrf-modal").val()},
            success: function(data){
                console.log(data);
                window.location.href = "/cart";
            },
            error: function (err) {
                console.log(err);
            }
        });
    });*/

    let clickedBut = null;

    //добавление в корзину
    $('#add-in-cart').click(function (e) {

        e.preventDefault();

        if($('#add-in-cart').data("but") != null) {
            clickedBut = $('#add-in-cart');
        }

        //let form = $("#modal-form").serializeArray();
        let product = {};
        if($('#add-in-cart').data("but") != null) {
            product['article_clothes'] = $("#article").val();
        }
        else {
            product['article_clothes'] = $("#modal-article").val();
        }
        product['size'] = $("#size").val();
        product['image'] =  clickedBut.data('image');
        product['category'] =  clickedBut.data('category');
        product['name'] =  clickedBut.data('name');

        product['price'] =  +((clickedBut.data('price') + "").replace(/\D+/g,""));

        if(product['article_clothes'] == null || product['size'] == null ||
            product['article_clothes'] == "" || product['size'] == "") {
            return;
        }

        /*
        for(let i = 0; i < form.length; i++) {
            if(form[i].name != "_csrf") {
                product[form[i].name] = form[i].value;
            }
        }
        */
        cartJS.addProductInCart(product);

        if($('#add-in-cart').data("but") != null) {
            updateSizeAtPage();
        }
        cartJS.setCountInNavbar();

        $("#modal-size").modal('hide');
        $('#modal').modal('show');
        /*
        $.ajax({
            url: '/clothes_json',
            method: 'post',
            dataType: 'json',
            data: $.param(form),
            success: function(data){
                let countOld = +$('#count-in-cart').text();
                let count = +data['message'] + +$('#count-in-cart').text();
                if(countOld == 0) {
                    $('#count-in-cart-b').html(' Корзина (<span id="count-in-cart">' + count + '</span>)');
                }
                else {
                    $('#count-in-cart').html(count);
                }
                let sizes = getSizes(clickedBut);
                let chooseSize = "";
                for (let i = 0; i < form.length; i++) {
                    if(form[i].name == 'size') {
                        console.log(form[i].value);
                        chooseSize = form[i].value;
                    }
                }
                if(chooseSize != "" && sizes[chooseSize]) {
                    sizes[chooseSize] --;
                }
                let newSizes = "";
                for (let key in sizes) {
                    newSizes += key + ":" + sizes[key] + ",";
                }
                newSizes = newSizes.substring(0, newSizes.length - 1);
                clickedBut.data("sizes", newSizes);
                console.log(newSizes);
                $('#modal').modal('show');
            },
            error: function (err) {
                console.log(err);
            }
        });
         */
    });

    //получение размеров выбранной одежды
    function getSizes(button, article) {
        let sizesInCart = cartJS.getObjCart();
        console.log("Получили");
        console.log(sizesInCart);
        let sizes = button.data('sizes').split(",");
        let sizesObj = {};
        for(let i = 0; i < sizes.length; i++) {
            let parse = sizes[i].split(":");
            sizesObj[parse[0]] = +parse[1];
            if(sizesInCart[article] != null && sizesInCart[article][parse[0]] != null) {
                sizesObj[parse[0]] = sizesObj[parse[0]] - +sizesInCart[article][parse[0]];
            }
        }
        console.log(sizesObj);
        return sizesObj;
    }

    /*
    $('#apply_filter').click(function (e) {
        e.preventDefault();
        $.post('/filter', $("#form_filter").serialize(), function (data) {
            alert(data);
        });
    });
     */
    //применение фильтров
    $('#apply_filter').click(function (e) {
        e.preventDefault();
        let params = getUrlVars();
        sendRequest(params);
    });

    function getUrlVars() {
        let vars = [], hash;
        if(window.location.href.indexOf('?') == -1) {
            return -1;
        }
        let hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
        for(let i = 0; i < hashes.length; i++) {
            hash = hashes[i].split('=');
            vars.push(hash[0]);
            vars[hash[0]] = hash[1];
        }
        return vars;
       // return window.location.href.slice(window.location.href.indexOf('?')).split(/[&?]{1}[\w\d]+=/);
    }

    function sendRequest(params) {
        let data = $('#form_filter').serializeArray(); // convert form to array
        let names = [];
        let newDate = [];
        for(let i = 0; i < data.length; i++) {
            if(names.indexOf(data[i].name) == -1) {
                names.push(data[i].name);
                newDate.push({name: data[i].name, value: [data[i].value]});
            }
            else {
                newDate[names.indexOf(data[i].name)].value.push(data[i].value);
            }
        }
        if(params != null && params != -1) {
            for(let key in params) {
                newDate.push({name: key, value: params[key]});
            }
        }
        newDate.push({name: 'sort', value: $('#sort').val()});
        $.ajax({
            url: '/filter',
            method: 'post',
            dataType: 'html',
            data: $.param(newDate),
            success: function(data){
                $('#clothes_div').html(data);
            },
            error: function (err) {
                console.log(err);
            }
        });
    }
//});