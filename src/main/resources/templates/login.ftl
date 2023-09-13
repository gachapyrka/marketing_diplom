<#import "partitials/common.ftl" as c>
<#import "partitials/login.ftl" as l>
<#import "partitials/default-container.ftl" as k>

<@c.page>
    <@k.page_default>
        <div class="row" style="margin-top: 10%">
            <div class="col s6 offset-s3">
                <h4>Авторизация</h4>
            </div>
        </div>
        <form action="/login" method="post">
            <div class="row" style="margin-top: 2%">
                <div class="col s6 offset-s3">
                    <div><label> Логин : <input type="email" required="true" name="username"/> </label></div>
                    <div><label> Пароль: <input type="password" required="true" name="password"/> </label></div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
            </div>
            <div class="row">
                <div class="col s1 offset-s3">
                    <input class="aves-effect waves-light btn" type="submit" value="Вход"/>
                </div>
                <div class="col s1">
                    <button class="aves-effect waves-light btn" onclick="window.location.href = 'localhost:8080/registration'">Регистрация</button>
                </div>
            </div>
        </form>
    </@k.page_default>
</@c.page>