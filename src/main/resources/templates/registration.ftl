<#import "partitials/common.ftl" as c>
<#import "partitials/login.ftl" as l>
<#import "partitials/default-container.ftl" as k>

<@c.page>
    <@k.page_default>
        <div class="row" style="margin-top: 2%">
            <div class="col s6 offset-s3">
                <h4>Регистрация</h4>
                <h3>${message?ifExists}</h3>
            </div>
        </div>
        <form id="registration" action="/registration" method="post">
            <div class="row" style="margin-top: 2%">
                <div class="col s6 offset-s3">
                    <select class="browser-default" required="true" form="registration" name="departments" id="departments">
                        <option disabled selected>Выберите отдел</option>
                        <option value="Отдел кадров">Отдел кадров</option>
                        <option value="Отдел разработки">Отдел разработки</option>
                        <option value="Отдел продаж">Отдел продаж</option>
                        <option value="Отдел управления">Отдел управления</option>
                        <option value="Отдел маркетинга">Отдел маркетинга</option>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="col s6 offset-s3">
                    <div><label> ФИО: <input type="text" placeholder="Иванов И.А." required="true" name="credentials"/> </label></div>
                    <div><label> Email : <input type="email" required="true" name="username"/> </label></div>
                    <div><label> Пароль: <input type="password" required="true" name="password"/> </label></div>
                    <div><label> Повторите пароль: <input type="password" required="true" name="repeatPassword"/> </label></div>
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    <div><input type="submit" class="aves-effect waves-light btn" value="Зарегистрироваться"/></div>
                </div>
            </div>
        </form>
    </@k.page_default>
</@c.page>