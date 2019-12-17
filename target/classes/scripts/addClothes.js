$(document).ready(function() {
    //$(".chzn-select").chosen();

    $("#atr1").hide();
    let n = 0; let m = 0;
    let but = '<div class="col-sm-2"><button id="delete" class="btn btn-primary">Удалить</button></div>';
    let select = {};
    let chosen = {};

    $('#attribute').find('option').each(function() {
        select[$(this).val()] = $(this).text();
        m++;
    });
    $("#add_attr").click(function(e) {
        e.preventDefault();
        if ($.isEmptyObject(select)) {
            if ($("#mes_err").length == 0)
                $("#add_attr").after('<p id="mes_err"><font color="red">Нет доступных атрибутов. Для добавления воспользуйтесь панелью администратора</font></p>');
            return;
        }
        if (n == 0) {
            $('#atr1').show();
            $('#value').attr('required', 'required');
            $('#atr1 #atr-div').after(but);
        } else {
            $('#atr' + n).after('<div class="form-group row" id="atr' + (n + 1) + '">' + $('#atr' + n).html() + '</div>');
            $("#atr" + (n + 1) + " #arrtibute").attr("id", " #arrtibute")
            $('#atr' + n + ' #attribute').attr("disabled", "disabled");
            $('#atr' + n + ' #attribute').after('<input type="hidden" name="attribute" id="inp' + n + '" value="' + $('#atr' + n + ' #attribute').val() + '" />');
            $('#atr' + (n + 1) + ' #attribute [value="' + $('#atr' + n + " #attribute").val() + '"]').remove();
            $('#delete').detach();
        }
        chosen[n + 1] = $('#atr' + (n + 1) + " #attribute").val();
        n++;
        if (n == m)
            $('#add_attr').hide();
    });
    /*
    $('.attributes').on('change', '#attribute', function () {
        console.log($(this).val());
        let idPar = $(this).parent().parent().attr("id").slice(3);

        //for(let i = idPar + 1; i <= n; i++) {
        for(let i = 1; i <= n; i++) {
            if(i != idPar) {
                $('#atr' + i + ' #attribute [value="' + $(this).val() + '"]').remove();
                $('#atr' + i + ' #attribute').append($('<option value="' + chosen[idPar] + '">' + select[chosen[idPar]] + '</option>'));
            }
        }
        console.log(select[chosen[idPar]]);
        console.log(chosen);
        console.log(select);
    });
     */
    /*
    $('#attribute').change(function () {
        console.log("er");

    });*/

    $('.attributes').on('click', '#delete', function(e){
        e.preventDefault();
        if(n == m)
            $('#add_attr').show();
        if(n == 1) {
            $('#atr1').hide();
            $("#value").removeAttr("required");
            $("#delete").remove();
        }
        else {
            $('#delete').detach().insertAfter('#atr' + (n - 1) + ' #atr-div');
            $('#atr' + n).remove();
            $("#atr" + (n - 1) + ' #attribute').removeAttr("disabled");
            $("#inp" + (n - 1)).remove();
        }
        n--;
    });
});