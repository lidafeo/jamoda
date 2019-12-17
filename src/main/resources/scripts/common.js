$(document).ready(function() {

    if ($("#order-modal") != null)
        $('#order-modal').modal('show');

    $('#navbarDropdown').click(function (e) {
        if ($("#catalog").css('visibility') == "hidden") {
            $("#catalog").css("visibility", "visible");
        } else {
            $("#catalog").css("visibility", "hidden");
        }
    });
    $('#sort').change(function (e) {
        sendRequest();
    });

    $('#clothes_div').on('click', '.but-buy', function (e) {
        e.preventDefault();
        let form = $(this).parents('form');
        console.log(form);
    });

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
                let count = +data['message'] + +$('#count-in-cart').text();
                $('#count-in-cart').html(count);
                $('#modal').modal('show');
            },
            error: function (err) {
                console.log(err);
            }
        });
    });

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
    $('#apply_filter').click(function (e) {
        e.preventDefault();
        sendRequest();
    });
    
    function sendRequest() {
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