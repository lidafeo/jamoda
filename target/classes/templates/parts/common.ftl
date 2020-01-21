<#macro page script="">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="/plugins/chosen.min.css" />
    <link rel="stylesheet" href="/styles/common.css">

    <title>Jamoda</title>
</head>
<body>
    <#include "navbar.ftl">
    <div class="container-fluid">
        <#nested>
    </div>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script type="text/javascript" src="/plugins/jquery/jquery-3.4.1.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script type="text/javascript" src="/plugins/chosen.jquery.min.js"></script>
    <script type="text/javascript" src="/scripts/cart.js"></script>
    <script type="text/javascript" src="/scripts/common.js"></script>

        <!-- <script type="text/javascript" src="https://js.stripe.com/v2/"></script>
    <script type="text/javascript">
        Stripe.setPublishableKey('pk_test_FwHH4sTFfVJN0k4sxn6fFAtC00Y4VmeRCk');
    </script>-->

    <script src="/plugins/ejs-2.4.2.min.js" type="text/javascript"></script>
    <script src="/plugins/ejs-browser-async.min.js" type="text/javascript"></script>

    <#if script != "">
        <script src="/scripts${script}"></script>
    </#if>
</body>
</html>
</#macro>