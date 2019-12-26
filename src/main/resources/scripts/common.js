//модальное окно выбора размера
let modalBody = $('#modal-size-body').html();
let ajaxSend = false;

$('#card-deck-id').on('click', ".but-buy", function (event) {
    event.preventDefault();
    $('#add-in-cart').show();
    $('#modal-size-body').html(modalBody);
    let button = $(this); // Button that triggered the modal
    clickedBut = button;
    let recipient = button.data('whatever'); // Extract info from data-* attributes;
    let sizesObj = getSizes(button, recipient);
    let select = "";
    let findRemains = false;
    for(let i = 40; i <= 52; i += 2) {
        if(!sizesObj["" + i] || sizesObj["" + i] == 0) {
            select += '<option value="' + i + '" class="disable-option" ' + 'data-count="0" ' + 'disabled>' + i + '</option>';
        }
        else {
            select += '<option value="' + i + '" ' + 'data-count="' + sizesObj["" + i] + '" ' + '>' + i + '</option>';
            findRemains = true;
        }
    }
    if(findRemains) {
        $("#size").html(select);
        let modal = $("#modal-size");
        modal.find('.modal-body #article').val(recipient);
    }
    else {
        $('#modal-size-body').html('<div class="alert alert-danger col-sm-10" role="alert">К сожалению товар закончился</div>');
        $('#add-in-cart').hide();
    }
    $("#modal-size").modal('show');
});

function updateSizeAtPage() {
    let productCart = cartJS.getProductInCart($("#article").attr("value"));
    let first = false;
    let finded = false;
    $("#size option").each(function(index) {
        let countInCart = productCart[$(this).attr("value")];
        if(countInCart == null || countInCart == "" || $('#add-in-cart').data("but") == null) {
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
        if($('#add-in-cart').data("but") != null) {
            $("#add-in-cart").attr("disabled", true);
            $("#size-div").html('<div class="alert alert-danger col-sm-10" role="alert">К сожалению товара нет в наличии</div>');
            //$('#modal .modal-body').html('<div class="alert alert-danger col-sm-10" role="alert">К сожалению товара нет в наличии</div>');
        }
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
        sendRequest(params, 0, -1);
    });

    //выбор другой страницы
    $("#clothes_div").on('click', '.pages-a', function (e) {
        if(!ajaxSend) {
            return;
        }
        e.preventDefault();
        let params = getUrlVars();
        sendRequest(params, $(this).data('number'), -1);
    });

    //выбор другого количества элементов на странице
    $("#clothes_div").on('click', '.size-a', function (e) {
        if(!ajaxSend) {
            return;
        }
        e.preventDefault();
        console.log("выбрали количество элементов на странице");
        let params = getUrlVars();
        sendRequest(params, 0, $(this).data('number'));
    });

    //покупка товара(перемещене в корзину)
    $('#clothes_div').on('click', '.but-buy', function (e) {
        e.preventDefault();
        let form = $(this).parents('form');
    });

    let clickedBut = null;

    //добавление в корзину
    $('#add-in-cart').click(function (e) {
        e.preventDefault();

        if($('#add-in-cart').data("but") != null) {
            clickedBut = $('#add-in-cart');
            $('#modal .modal-body').html('Товар добавлен в корзину!');
        }

        let product = {};

        product['article_clothes'] = $("#article").val();

        product['size'] = $("#size").val();
        product['image'] =  clickedBut.data('image');
        product['category'] =  clickedBut.data('category');
        product['name'] =  clickedBut.data('name');
        product['price'] =  +((clickedBut.data('price') + "").replace(/\D+/g,""));

        if(product['article_clothes'] == null || product['size'] == null ||
            product['article_clothes'] == "" || product['size'] == "") {
            $('#modal .modal-body').html('<div class="alert alert-danger col-sm-10 align-middle" role="alert">К сожалению товара нет в наличии</div>');
            $('#modal').modal('show');
            return;
        }

        cartJS.addProductInCart(product);

        updateSizeAtPage();

        cartJS.setCountInNavbar();

        $("#modal-size").modal('hide');
        $('#modal').modal('show');
    });

    //получение размеров выбранной одежды
    function getSizes(button, article) {
        let sizesInCart = cartJS.getObjCart();
        let sizes = button.data('sizes').split(",");
        let sizesObj = {};
        for(let i = 0; i < sizes.length; i++) {
            let parse = sizes[i].split(":");
            sizesObj[parse[0]] = +parse[1];
            if(sizesInCart[article] != null && sizesInCart[article][parse[0]] != null) {
                sizesObj[parse[0]] = sizesObj[parse[0]] - +sizesInCart[article][parse[0]];
            }
        }
        return sizesObj;
    }

    //применение фильтров
    $('#apply_filter').click(function (e) {
        e.preventDefault();
        let params = getUrlVars();
        sendRequest(params, 0, -1);
    });

    function getUrlVars() {
        let vars = [], hash;
        if(window.location.href.indexOf('?') == -1) {
            return -1;
        }
        let hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
        for(let i = 0; i < hashes.length; i++) {
            hash = hashes[i].split('=');
            if(hash[0] != 'page' && hash[0] != 'size') {
                vars.push(hash[0]);
                vars[hash[0]] = hash[1];
            }
        }
        return vars;
       // return window.location.href.slice(window.location.href.indexOf('?')).split(/[&?]{1}[\w\d]+=/);
    }

    function sendRequest(params, page, size) {
        ajaxSend = true;
        let data = $('#form_filter').serializeArray(); // convert form to array
        let names = [];
        let newDate = [];
        if(page == -1) {
            page = +$(".pagination-page .active a").data("number");
        }
        if(size == -1) {
            size = +$(".pagination-size .active a").data("number");
        }
        console.log(page);
        console.log(size);

        debugger;
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
        newDate.push({name: 'sorting', value: $('#sort').val()});
        newDate.push({name: 'page', value: page});
        newDate.push({name: 'size', value: size});
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