<#include "partitials/security.ftl">
<#import "partitials/common.ftl" as c>
<#import "partitials/login.ftl" as l>
<#import "partitials/default-container.ftl" as k>
<@c.page>
    <@k.page_default>
        <h2>Запросы:</h2>
        <table>
            <thead>
            <th>Текст</th>
            <th></th>
            </thead>
            <tbody>
            <#list orders as order>
                <tr>
                    <td>${order.getText()}</td>
                    <td>
                        <form method="post" action="/order-delete/${order.id}">
                            <input type="hidden" name="_csrf" value="${_csrf.token}" />
                            <input type="submit" value="Удалить">
                        </form>
                    </td>
                </tr>
            </#list>
            </tbody>
            <tr>
                <form method="post" action="/add-order">
                    <td>
                        <input type="text" required="true" name="text" placeholder="Введите текст запроса">
                    </td>
                    <td>
                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                        <input type="submit" value="Добавить">
                    </td>
                </form>
            </tr>
        </table>
    </@k.page_default>
</@c.page>