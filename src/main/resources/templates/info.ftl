<#include "partitials/security.ftl">
<#import "partitials/common.ftl" as c>
<#import "partitials/default-container.ftl" as k>

<@c.page>
    <@k.page_default>
        <div class="row">
            <div class="col s12" style="left: 20px">
                <table class="highlight">
                    <thead>
                    <th></th>
                    <th>Месяц</th>
                    <th>Охват</th>
                    <th>Показы</th>
                    <th>Частота показов</th>
                    <th>Кол-во кликов</th>
                    <th>Кол-во пользователей с заявками</th>
                    <th>Кол-во пользователей с подписками</th>
                    <th>Кол-во пользователей с покупками</th>
                    <th>Кол-во заявок</th>
                    <th>Кол-во подписок</th>
                    <th>Кол-во покупок</th>
                    <th>Кол-во целевых действий</th>
                    <th>Кол-во расходов</th>
                    <th>Кол-во доходов</th>
                    </thead>
                    <tbody>
                    <#list stats as stat>
                        <tr>
                            <td>
                                <form method="post" action="/info-delete/${stat.id}">
                                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                    <input type="submit" class="aves-effect waves-light btn red lighten-2" value="Удалить">
                                </form>
                            </td>
                            <td><input type="month" disabled value="${stat.getDateStr()}"></td>
                            <td>${stat.numberOfCoverage}</td>
                            <td>${stat.numberOfShowing}</td>
                            <td>${stat.showingFrequencyForUser}</td>
                            <td>${stat.numberOfClicksOnAd}</td>
                            <td>${stat.numberOfUserRequest}</td>
                            <td>${stat.numberOfUserSubscribe}</td>
                            <td>${stat.numberOfUserOrder}</td>
                            <td>${stat.numberOfRequests}</td>
                            <td>${stat.numberOfSubscribes}</td>
                            <td>${stat.numberOfOrders}</td>
                            <td>${stat.numberOfTargetedActions}</td>
                            <td>${stat.totalExpense} руб.</td>
                            <td>${stat.totalIncome} руб.</td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>

        <a href="/add-info" class="aves-effect waves-light btn">Добавить</a>
    </@k.page_default>
</@c.page>