<#include "partitials/security.ftl">
<#import "partitials/common.ftl" as c>
<#import "partitials/default-container.ftl" as k>

<#assign x = 0>

<@c.page>
    <@k.page_default>
        <div class="row" style="margin-top: 3%">
            <div class="col s6 offset-s3">
                <h4>${order.text}</h4>
            </div>
        </div>
        <form method="post" id="analyze" action="/report/${order.id}">
            <div class="row">
                <div class="col s2 offset-s3">
                    <div><label> От: <input type="month" value="${from}" placeholder="С" min = "2000-01" max = "2023-09" required="true" name="from"/> </label></div>
                </div>
                <div class="col s2 offset-s2">
                    <div><label> До: <input type="month" value="${to}" placeholder="По" min = "2000-01" max = "2023-09" required="true" name="to"/> </label></div>
                </div>
            </div>
            <div class="row">
                <div class="col s6 offset-s3">
                    <div>
                        <select class="browser-default" required="true" form="analyze" name="type" id="type">
                            <option value="${ttype}" selected>${type}</option>
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
                </div>
            </div>
            <div class="row">
                <div class="col s6 offset-s3">
                    <div><input type="submit" class="aves-effect waves-light btn" value="Сгенерировать"></div>
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                </div>
            </div>
        </form>
        <div class="row" style="margin-top: 100px">
            <div class="col s10 offset-s1">
                <div class="row">
                    <#if isOK>
                        <div class="col s6 offset-s3">
                            Недостаточно данных для анализа... Пожалуйста, введите дополнительную статистику по месяцам в текущем диапазоне.
                        </div>
                    <#else>
                        <div class="col s8 offset-s2">
                            <table id="q-graph">
                                <thead>
                                    <tr>
                                        <th></th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <#list result as res>
                                        <tr class="qtr" style="margin-left: calc(150px * ${x});">
                                            <th scope="row">${res.getKey()}</th>
                                            <td class="sent bar" style="height: calc(332px*(${res.getValue()}/${maxTick}));"><p>${res.getValue()}</p></td>
                                            <#assign x = x + 1>
                                        </tr>
                                    </#list>
                                </tbody>
                            </table>

                            <div id="ticks">
                                <#list ticks as tick>
                                    <div class="tick" style="height: 59px;"><p>${tick}</p></div>
                                </#list>
                            </div>
                        </div>
                    </#if>
                </div>
            </div>
        </div>
        <div>
            <form action="/mail/${order.id}" method="post">
                <div class="row">
                    <div class="col s6 offset-s3">
                        <input type="text" placeholder="Введите заключение" required="true" name="text">
                    </div>
                </div>
                <div class="row">
                    <div class="col s6 offset-s3">
                        <div><input type="submit" class="aves-effect waves-light btn" value="Отправить"></div>
                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    </div>
                </div>
            </form>
        </div>
    </@k.page_default>
</@c.page>