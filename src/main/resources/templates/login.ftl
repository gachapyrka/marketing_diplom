<#import "partitials/common.ftl" as c>
<#import "partitials/login.ftl" as l>
<#import "partitials/default-container.ftl" as k>

<@c.page>
    <@k.page_default>
        <div class="row">
            <div class="col s6 offset-s3">
                <h4>Авторизация</h4>
            </div>
        </div>
        <div class="row" style="margin-top: 5%">
            <@l.login "/login" />
        </div>
        <div class="row">
            <div class="col s2 offset-s5">
                <p><a href="/registration">Регистрация</a></p>
            </div>
        </div>
    </@k.page_default>
</@c.page>