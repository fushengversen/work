<div class="n-support">请使用Chrome、Safari等webkit内核的浏览器！</div>
<div class="n-head">
    <div class="g-doc f-cb">
    <#if user??>
        <div class="user">
            <#switch user.role>
                <#case 0>
                    卖家你好，
                    <#break>
                <#case 1>
                    买家你好，
                    <#break>
            </#switch>
            <span>${user.username}!</span>
            <a href="/system/logout">[退出]</a>
        </div>
        <ul class="nav">
            <li><a href="/system">首页</a></li>
            <#if user.role=0>
                <li><a href="/system/publish">发布</a></li>
            <#else>
                <li><a href="/system/account">账务</a></li>
                <li><a href="/system/settleAccount">购物车</a></li>
            </#if>
        </ul>
    <#else>
        <div class="user">
            请<a href="/system/login">[登录]</a>
        </div>
        <ul class="nav">
            <li><a href="/system">首页</a></li>
        </ul>
    </#if>
    </div>
</div>
