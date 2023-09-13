<#import "partitials/common.ftl" as c>
<#import "partitials/login.ftl" as l>
<#import "partitials/default-container.ftl" as k>
<@c.page>
    <@k.page_default>
        <div class="row" style="margin-top: 2%">
            <div class="col s8 offset-s2">
                <h4>Пользователи:</h4>
            </div>
        </div>
        <div class="row">
            <div class="col s8 offset-s2">
                <table class="highlight">
                    <thead>
                    <th>Логин</th>
                    <th>ФИО</th>
                    <th>Отдел</th>
                    <th>Статус</th>
                    </thead>
                    <tbody>
                    <#list usrs as usr>
                        <tr>
                            <td>${usr.username}</td>
                            <td>${usr.getProfile().credentials}</td>
                            <td>${usr.getProfile().department}</td>
                            <td>
                                <form method="post" action="/users/${usr.id}">
                                    <#if usr.active>
                                        <input type="submit" class="aves-effect waves-light btn" value="Активен">
                                    <#else>
                                        <input type="submit" class="aves-effect waves-light btn red lighten-2" value="Заблокирован">
                                    </#if>
                                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                </form>
                            </td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="row">
            <div class="col s8 offset-s2">
                <a href="/users/newsletter" class="aves-effect waves-light btn">Рассылка</a>
            </div>
        </div>
    </@k.page_default>
</@c.page>