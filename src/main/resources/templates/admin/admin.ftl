<#import "parts/commonAdmin.ftl" as c>
<@c.page_admin>
    <h2>Панель администратора</h2>
    <p>С помощью панели администратора Вы можете управлять настройками проекта и редактировать контент сайта jamoda</p>
    <h4>Пользователи</h4>
    <div><a class="nav-link" href="/admin/add_user">Добавить нового администратора</a></div>
    <h4>Товары</h4>
    <div><a class="nav-link" href="/admin/add_clothes">Добавить товар</a></div>
    <div><a class="nav-link" href="/admin/add_file">Добавить картинку к товару</a></div>
    <div><a class="nav-link" href="/admin/add_attribute_group">Добавить группу атрибутов на страницу товара</a></div>
    <div><a class="nav-link" href="/admin/add_attribute_value">Добавить значение атрибута к товару</a></div>
    <h4>Товары на складе</h4>
    <div><a class="nav-link" href="/admin/add_to_warehouse">Добавить поступление товаров на склад</a></div>
    <h4>Категории</h4>
    <div><a class="nav-link" href="/admin/add_category">Добавить категорию</a></div>
    <h4>Фильтры</h4>
    <div><a class="nav-link" href="/admin/add_filter">Добавить фильтр</a></div>
    <h4>Атрибуты</h4>
    <div><a class="nav-link" href="/admin/add_attribute">Создать новый атрибут</a></div>
    <div><a class="nav-link" href="/admin/add_group">Добавить группу атрибутов</a></div>
</@c.page_admin>