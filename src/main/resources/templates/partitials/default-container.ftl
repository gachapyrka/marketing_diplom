<#macro page_default>
    <div style="background-image: url('../../img/main.jpg');
            height: calc(100vh - 64px);
            background-size: cover;
            position: relative;">
        <div class="container">
            <#nested>
        </div>
    </div>
</#macro>

<#macro page_index>
    <div class="def-container index">
        <#nested>
    </div>
</#macro>