<#include "partitials/security.ftl">
<#import "partitials/common.ftl" as c>
<#import "partitials/default-container.ftl" as k>

<@c.page>
    <@k.page_default>
        <h4>${order.text}</h4>
        <div class="row">
            <div class="col s6 offset-s3">
                <form method="post" id="analyze" action="/report/${order.id}">
                    <div><label> От: <input type="month" placeholder="С" min = "2000-01" max = "2023-09" required="true" name="from"/> </label></div>
                    <div><label> ДО: <input type="month" placeholder="По" min = "2000-01" max = "2023-09" required="true" name="to"/> </label></div>
                    <div>
                        <select class="browser-default" required="true" form="analyze" name="type" id="type">
                            <option disabled selected>Выберите метрику</option>
                            <option value="CRonAd">CR кликов по объявлению</option>
                            <option value="CRusrReq">CR пользователей, оставивших заявку</option>
                            <option value="CRusrSub">CR пользователей, которые подписались</option>
                            <option value="CRusrOrd">CR пользователей, совершивших покупку</option>
                            <option value="CTR">CTR</option>
                            <option value="CPM">CPM</option>
                            <option value="CPC">CPC</option>
                            <option value="CPL">CPL</option>
                            <option value="CPA">CPA</option>
                            <option value="CPO">CPO</option>
                            <option value="DRR">DRR</option>
                        </select>
                    </div>
                    <div><input type="submit" value="Сгенерировать"></div>
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                </form>
            </div>
        </div>
        <div class="row">
            <div class="col s6 offset-s3">
                <#if isOK>
                    Недостаточно данных для анализа... Пожалуйста, введите дополнительную статистику по месяцам в текущем диапазоне.
                <#else>

                </#if>
            </div>
        </div>
    </@k.page_default>
</@c.page>