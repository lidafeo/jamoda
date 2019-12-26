$(function() {
    var $form = $('#payment-form');
    $form.submit(function(event) {
        // Отключим кнопку, чтобы предотвратить повторные клики
        $form.find('.submit').prop('disabled', true);

        // Запрашиваем token у Stripe
        Stripe.card.createToken($form, stripeResponseHandler);

        // Запретим форме submit
        return false;
    });
});

function stripeResponseHandler(status, response) {
    // Получим форму:
    var $form = $('#payment-form');

    if (response.error) { // Problem!

        // Показываем ошибки в форме:
        $form.find('.payment-errors').text(response.error.message);
        $form.find('.submit').prop('disabled', false); // Разрешим submit

    } else { // Token был создан

        // Получаем token id:
        var token = response.id;

        // Вставим token в форму, чтобы при submit он пришел на сервер:
        $form.append($('<input type="hidden" name="stripeToken">').val(token));

        // Сабмитим форму:
        $form.get(0).submit();
    }
};