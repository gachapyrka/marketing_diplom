<#include "partitials/security.ftl">
<#import "partitials/common.ftl" as c>
<#import "partitials/login.ftl" as l>
<#import "partitials/default-container.ftl" as k>
<@c.page>
    <@k.page_default>
        <div class="row" style="margin-top: 3%">
            <div class="col s6 offset-s3">
                <h4>Запросы:</h4>
            </div>
        </div>
        <div class="row" style="margin-top: 2%">
            <div class="col s8 offset-s2">
                <table class="highlight">
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
                                    <input type="submit" class="aves-effect waves-light btn red lighten-2" value="Удалить">
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
                                <input type="submit" class="aves-effect waves-light btn" value="Добавить">
                            </td>
                        </form>
                    </tr>
                </table>
            </div>
        </div>
    </@k.page_default>
</@c.page>