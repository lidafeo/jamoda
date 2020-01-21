<#macro page_admin script="">
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="/plugins/chosen.min.css" />
        <link rel="stylesheet" href="/styles/commonAdmin.css">
        <title>Admin</title>
    </head>
    <body>
        <#include "navbarAdmin.ftl">
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-3 bd-sidebar pl-0">
                    <#include "menu.ftl">
                </div>
                <div class="col-sm-9 mt-3">
                    <#nested>
                </div>
            </div>
        </div>
        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script type="text/javascript" src="/plugins/jquery/jquery-3.4.1.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script type="text/javascript" src="/plugins/jquery/bs-custom-file-input.min.js"></script>
        <script type="text/javascript" src="/plugins/chosen.jquery.min.js"></script>
        <script type="text/javascript" src="/scripts/commonAdmin.js"></script>

        <#if script != "">
            <script src="/scripts${script}"></script>
        </#if>
    </body>
</html>
</#macro>