<#import "partitials/common.ftl" as c>
<#import "partitials/login.ftl" as l>
<#import "partitials/default-container.ftl" as k>

<@c.page>
    <@k.page_default>
        <h4>Подтверждение</h4>
        <h3>${message?ifExists}</h3>
        <form id="confirmation" action="/confirmation" method="post">
            <p>Вам на почту был отправлен код для подтверждения подлинности</p>
            <div><input type="text" placeholder="Введите код здесь" required="true" name="key"/></div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div><input type="submit" class="aves-effect waves-light btn col s2 offset-s5" value="Зарегистрироваться"/></div>
        </form>
    </@k.page_default>
</@c.page>