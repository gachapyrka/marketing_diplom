<#import "partitials/common.ftl" as c>
<#import "partitials/login.ftl" as l>
<#import "partitials/default-container.ftl" as k>

<@c.page>
    <@k.page_default>
        <div class="row" style="margin-top: 2%">
            <div class="col s6 offset-s3">
                <h4>Рассылка:</h4>
            </div>
        </div>
        <div class="row" style="margin-top: 5%">
            <form method="post" action="${path}">
                <div class="col s3 offset-s3">
                    <input type="text" name="subj" placeholder="Тема">
                </div>
                <div class="col s6 offset-s3">
                    <input type="text" name="text" placeholder="Текст">
                </div>
                <div class="col s6 offset-s3" style="margin-top: 5%">
                    <input type="submit" class="aves-effect waves-light btn" value="Отправить">
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                </div>
            </form>
        </div>
    </@k.page_default>
</@c.page>