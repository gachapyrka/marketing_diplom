<#import "partitials/common.ftl" as c>
<#import "partitials/login.ftl" as l>
<#import "partitials/default-container.ftl" as k>

<@c.page>
    <@k.page_default>
        <h4>Профиль</h4>
        <h3>${message?ifExists}</h3>
        <form id="profile" action="/profile" method="post">
            <div class="row">
                <div class="input-field col s12">
                    <select class="browser-default" required="true" form="profile" name="departments" id="departments">
                        <option selected>${profile.department}</option>
                        <option value="Отдел кадров">Отдел кадров</option>
                        <option value="Отдел разработки">Отдел разработки</option>
                        <option value="Отдел продаж">Отдел продаж</option>
                    </select>
                </div>
            </div>
            <div><label> ФИО: <input type="text" placeholder="Иванов И.А." required="true" value="${profile.credentials}" name="credentials"/> </label></div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div><input type="submit" class="aves-effect waves-light btn col s2 offset-s5" value="Изменить"/></div>
        </form>
        <form action="/delete-profile" method="post">
            <div class="row">
                <div class="input-field col s12">
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    <div><input type="submit" class="aves-effect waves-light btn col s2 offset-s5" value="Удалить аккаунт"/></div>
                </div>
            </div>
        </form>
    </@k.page_default>
</@c.page>