$(document).ready(function() {
    if ($("modal") != null)
        $('#modal').modal('show');

    $('#navbarDropdown').click(function (e) {
        if ($("#catalog").css('visibility') == "hidden") {
            $("#catalog").css("visibility", "visible");
        } else {
            $("#catalog").css("visibility", "hidden");
        }
    });
    $('#sort').change(function (e) {
        console.log($(this).val());
        sendRequest();
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