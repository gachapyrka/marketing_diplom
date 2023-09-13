<#include "partitials/security.ftl">
<#import "partitials/common.ftl" as c>
<#import "partitials/login.ftl" as l>
<#import "partitials/default-container.ftl" as k>
<@c.page>
    <@k.page_default>
        <div class="row" style="margin-top: 3%">
            <div class="col s6 offset-s2">
                <h4>Запросы:</h4>
            </div>
        </div>
        <div class="row" style="margin-top: 2%">
            <div class="col s8 offset-s2">
                <table class="highlight">
                    <thead>
                    <th>Сотрудник</th>
                    <th>Email</th>
                    <th>Текст</th>
                    <th></th>
                    </thead>
                    <tbody>
                    <#list orders as order>
                        <tr>
                            <td>${order.getProfile().getCredentials()}</td>
                            <td>${order.getProfile().getUserInfo().getUsername()}</td>
                            <td>${order.getText()}</td>
                            <td>
                                <form method="get" action="/report/${order.id}">
                                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                    <input type="submit" class="aves-effect waves-light btn" value="Ответить">
                                </form>
                            </td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </@k.page_default>
</@c.page>