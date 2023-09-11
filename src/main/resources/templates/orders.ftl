<#include "partitials/security.ftl">
<#import "partitials/common.ftl" as c>
<#import "partitials/login.ftl" as l>
<#import "partitials/default-container.ftl" as k>
<@c.page>
    <@k.page_default>
        <h2>Запросы:</h2>
        <table>
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
                            <input type="submit" value="Ответить">
                        </form>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </@k.page_default>
</@c.page>