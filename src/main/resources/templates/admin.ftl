<#import "parts/commonAdmin.ftl" as c>
<@c.page_admin>
    <h2>Панель администратора</h2>
    <p>С помощью панели администратора Вы можете управлять настройками проекта и редактировать контент сайта jamoda</p>
    <h4>Пользователи</h4>
    <div><a href="/admin/add_user">Добавить пользователя</a></div>
    <h4>Товары</h4>
    <div><a href="/admin/add_clothes">Добавить товар</a></div>
    <div><a href="/admin/add_file">Добавить картинку к товару</a></div>
    <div><a href="/admin/add_attribute_value">Добавить значение атрибута к товару</a></div>
    <h4>Категории</h4>
    <div><a href="/admin/add_category">Добавить категорию</a></div>
    <h4>Атрибуты</h4>
    <div><a href="/admin/add_attribute">Создать новый атрибут</a></div>
    <div><a href="/admin/add_group">Добавить группу атрибутов</a></div>
</@c.page_admin>