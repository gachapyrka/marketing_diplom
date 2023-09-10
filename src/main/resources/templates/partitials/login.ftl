<#macro login path>
    <div class="row">
        <div class="col s6 offset-s3">
            <form action="${path}" method="post">
                <div><label> Логин : <input type="email" required="true" name="username"/> </label></div>
                <div><label> Пароль: <input type="password" required="true" name="password"/> </label></div>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <div><input class="aves-effect waves-light btn col s2 offset-s5" style="margin-top: 10px" type="submit" value="Вход"/></div>
            </form>
        </div>
    </div>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit">Выход</button>
    </form>
</#macro>