<#import "partitials/common.ftl" as c>
<#import "partitials/login.ftl" as l>
<#import "partitials/default-container.ftl" as k>

<@c.page>
    <@k.page_default>
        Рассылка:
        <div class="row">
            <div class="col s6 offset-s3">
                <form method="post" action="${path}">
                    <input type="text" name="subj" placeholder="Тема">
                    <input type="text" name="text" placeholder="Текст">
                    <input type="submit" value="Отправить">
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                </form>
            </div>
        </div>
    </@k.page_default>
</@c.page>