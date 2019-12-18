$(document).ready(function() {

    if ($("#order-modal") != null)
        $('#order-modal').modal('show');

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

    //добавление в корзину
    $('#add-in-cart').click(function (e) {
        e.preventDefault();
        let form = $("#modal-form").serializeArray();
        $("#modal-size").modal('hide');
        $.ajax({
            url: '/clothes_json',
            method: 'post',
            dataType: 'json',
            data: $.param(form),
            success: function(data){
                console.log(+data['message']);
                console.log(+$('#count-in-cart').text());
                let countOld = +$('#count-in-cart').text();
                let count = +data['message'] + +$('#count-in-cart').text();
                if(countOld == 0) {
                    $('#count-in-cart-b').html(' Корзина (<span id="count-in-cart">' + count + '</span>)');
                }
                else {
                    $('#count-in-cart').html(count);
                }
                $('#modal').modal('show');
            },
            error: function (err) {
                console.log(err);
            }
        });
    });

    //модальное окно выбора размера
    $('#modal-size').on('show.bs.modal', function (event) {
        let button = $(event.relatedTarget); // Button that triggered the modal
        let recipient = button.data('whatever'); // Extract info from data-* attributes;
        let modal = $(this);
        modal.find('.modal-body #modal-article').val(recipient);
    });

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
});