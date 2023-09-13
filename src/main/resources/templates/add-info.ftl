<#include "partitials/security.ftl">
<#import "partitials/common.ftl" as c>
<#import "partitials/default-container.ftl" as k>

<@c.page>
    <@k.page_default>
        <form method="post" action="/add-info">
            <div class="row" style="margin-top: 5%">
                <div class="col s2 offset-s1">
                    <div><label> Месяц: <input type="month" placeholder="Выберите месяц" min = "2000-01" max = "2023-09" required="true" name="date"/> </label></div>
                </div>
            </div>
            <div class="row">
                <div class="col s3 offset-s1">
                    <div><label> Охват: <input type="number" required="true" name="numberOfCoverage"/> </label></div>
                </div>
                <div class="col s3 offset-s2">
                    <div><label> Показы: <input type="number" required="true" name="numberOfShowing"/> </label></div>
                </div>
            </div>
            <div class="row">
                <div class="col s3 offset-s1">
                    <div><label> Частота показов: <input type="number" required="true" name="showingFrequencyForUser"/> </label></div>
                </div>
                <div class="col s3 offset-s2">
                    <div><label> Частота переходов по ссылке: <input type="number" required="true" name="numberOfClicksOnAd"/> </label></div>
                </div>
            </div>
            <div class="row">
                <div class="col s3 offset-s1">
                    <div><label> Кол-во пользователей с заявками: <input type="number" required="true" name="numberOfUserRequest"/> </label></div>
                </div>
                <div class="col s3 offset-s2">
                    <div><label> Кол-во пользователей с подписками: <input type="number" required="true" name="numberOfUserSubscribe"/> </label></div>
                </div>
            </div>
            <div class="row">
                <div class="col s3 offset-s1">
                    <div><label> Кол-во пользователей с покупками: <input type="number" required="true" name="numberOfUserOrder"/> </label></div>
                </div>
                <div class="col s3 offset-s2">
                    <div><label> Кол-во заявок: <input type="number" required="true" name="numberOfRequests"/> </label></div>
                </div>
            </div>
            <div class="row">
                <div class="col s3 offset-s1">
                    <div><label> Кол-во подписок: <input type="number" required="true" name="numberOfSubscribes"/> </label></div>
                </div>
                <div class="col s3 offset-s2">
                    <div><label> Кол-во покупок: <input type="number" required="true" name="numberOfOrders"/> </label></div>
                </div>
            </div>
            <div class="row">
                <div class="col s3 offset-s1">
                    <div><label> Кол-во целевых действий: <input type="number" required="true" name="numberOfTargetedActions"/> </label></div>
                </div>
                <div class="col s3 offset-s2">
                    <div><label> Кол-во расходов: <input type="number" required="true" name="totalExpense"/> </label></div>
                </div>
            </div>
            <div class="row">
                <div class="col s3 offset-s1">
                    <div><label> Кол-во доходов: <input type="number" required="true" name="totalIncome"/> </label></div>
                </div>
            </div>
            <div class="row">
                <div class="col s3 offset-s1">
                    <div><input type="submit" class="aves-effect waves-light btn" value="Сохранить"></div>
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                </div>
            </div>
        </form>
    </@k.page_default>
</@c.page>