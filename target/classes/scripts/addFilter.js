$(document).ready(function() {

    //$("#all_values").hide();
    //$('#helpBlock').hide();
    check($('input[type=radio][name=searchAll]'));

    let n = 0;
    let but = '<div class="col-sm-3"><button id="add" class="btn btn-primary">Добавить значение</button></div>';

    $("#type").change(function (e) {
        let type = $('#type').val();
        if((type == "0" || type == "1") && $('input[type=radio][name=searchAll]').val() == '0') {
            $("#all_values").hide();
            $("#values1 #value").prop('required', false);
            $("#radio2").prop('disabled', false);
            $('#helpBlock').hide();
        }
        else if(type == "2") {
            $("#all_values").hide();
            $("#values1 #value").prop('required', false);
            $("#radio2").prop('disabled', true);
            $("#radio1").prop('checked', true);
            $('#helpBlock').show();
        }
        else {
            $("#radio2").prop('disabled', false);
            $('#helpBlock').hide();
        }
    });

    $('input[type=radio][name=searchAll]').change(function() {
        check($(this));
    });

    $('#all_values').on('click', '#add', function(e){
        e.preventDefault();
        n++;
        $("#values" + n).after('<div class="form-group row" id="values' + (n + 1) + '">' + $('#values' + n).html() + '</div>')
        $("#values" + (n + 1) + " #value").prop('required', false);
        $("#values" + n + ' #add').remove();//detach().insertAfter($("#values" + (n + 1) + " #val"));
        //$("#delete").remove();
        //$("#values" + (n + 1) + " #val").after($('#but').html());
    });

    function check(radio) {
        if (radio.val() == '1') {
            $("#all_values").hide();
            $("#values1 #value").prop('required', false);
        }
        else if (radio.val() == '0' && $('#type').val() != "2") {
            $("#all_values").show();
            $("#values1 #value").prop('required', true);
        }
    }
});