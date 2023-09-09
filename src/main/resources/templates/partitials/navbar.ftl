<#include "security.ftl">
<#import "login.ftl" as l>

<#macro navbar>
    <nav class="blue lighten-4">
        <div class="nav-wrapper">
            <a style="visibility:collapse" id="errorMessage"></a>
            <a href="/" style="margin-left: 10px" class="brand-logo">Образование и развитие</a>
            <ul id="nav-mobile" class="right hide-on-med-and-down">
                <#if known>
                    <li><a href="/raises">Рейсы</a></li>
                    <li><a href="/tours">Путевки</a></li>
                    <#if isAdmin>
                        <li><a href="/users">Пользователи</a></li>
                        <li><a href="/statistics">Статистика</a></li>
                    <#else>
                        <li><a href="/tickets">Корзина</a></li>
                        <li><a href="/history">История покупок</a></li>
                    </#if>
                    <li><@l.logout></@l.logout></li>
                <#else>
                    <li><a href="/login">Вход</a></li>
                    <li><a href="/registration">Регистрация</a></li>
                </#if>
            </ul>
        </div>
    </nav>
</#macro>