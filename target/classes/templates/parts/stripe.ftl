<form action="" method="POST" id="payment-form">
    <span class="payment-errors"></span>
    <label>Card Number</label>
    <input type="text" size="20" data-stripe="number">
    <label>Expiration (MM/YY)</label>
    <input type="text" size="2" data-stripe="exp_month">
    <input type="text" size="2" data-stripe="exp_year">
    <label>CVC</label>
    <input type="text" size="4" data-stripe="cvc">
    <input type="submit" class="submit" value="Submit">
</form>