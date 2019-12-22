<#import "parts/common.ftl" as c>
<#import "parts/productCart.ftl" as card>
<@c.page "/render.js">
    <div class="container main-clothes">
        <div id="cart-div">
        </div>
        <form>
            <input type="hidden" name="_csrf" id="_csrf" value="${_csrf.token}">
        </form>
    </div>
</@c.page>