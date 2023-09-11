<#import "partitials/common.ftl" as c>
<#import "partitials/login.ftl" as l>
<#import "partitials/default-container.ftl" as k>

<@c.page>
    <@k.page_default>
        <div class="row" style="margin-top: 2%">
            <div class="col s6 offset-s3">
                <h4>Профиль</h4>
            </div>
        </div>
        <form id="profile" action="/profile" method="post">
            <div class="row" style="margin-top: 2%">
                <div class="col s6 offset-s3">
                    <select class="browser-default" required="true" form="profile" name="departments" id="departments">
                        <option selected>${profile.department}</option>
                        <option value="Отдел кадров">Отдел кадров</option>
                        <option value="Отдел разработки">Отдел разработки</option>
                        <option value="Отдел продаж">Отдел продаж</option>
                        <option value="Отдел управления">Отдел управления</option>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="col s6 offset-s3">
                    <div><label> ФИО: <input type="text" placeholder="Иванов И.А." required="true" value="${profile.credentials}" name="credentials"/> </label></div>
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    <div><input type="submit" class="aves-effect waves-light btn" value="Изменить"/></div>

                </div>
            </div>
        </form>
        <form action="/delete-profile" method="post">
            <div class="row">
                <div class="input-field col s6 offset-s3">
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    <div><input type="submit" class="aves-effect waves-light btn blue lighten-5" value="Удалить аккаунт"/></div>
                </div>
            </div>
        </form>
    </@k.page_default>
</@c.page>