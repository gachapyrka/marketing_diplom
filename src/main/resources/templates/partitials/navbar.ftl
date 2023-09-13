<#include "security.ftl">
<#import "login.ftl" as l>

<#macro navbar>
    <nav class="teal">
        <div class="nav-wrapper">
            <a style="visibility:collapse" id="errorMessage"></a>
            <a href="/" style="margin-left: 10px" class="brand-logo">Образование и развитие</a>
            <ul id="nav-mobile" class="right hide-on-med-and-down">
                <#if known>
                    <#if isAdmin>
                        <li><a href="/users">Пользователи</a></li>
                        <li><a href="/marketers">Маркетологи</a></li>
                    <#else>
                        <#if isMarketer>
                            <li><a href="/info">Данные</a></li>
                            <li><a href="/orders">Запросы</a></li>
                        <#else>
                            <li><a href="/orders-view">Запросы</a></li>
                            <li><a href="/profile">Профиль</a></li>
                        </#if>
                    </#if>
                    <li><a href="/logout">Выход</a></li>
<#--                    <li><@l.logout></@l.logout></li>-->
                <#else>
                    <li><a href="/login">Вход</a></li>
                    <li><a href="/registration">Регистрация</a></li>
                </#if>
            </ul>
        </div>
    </nav>
</#macro>