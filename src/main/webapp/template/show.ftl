<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>商品展示</title>
    <link rel="stylesheet" href="/css/style.css"/>
</head>
<body>
<#include "include/header.ftl">
<div class="g-doc">
    <div class="n-show f-cb" id="showContent">
        <div class="img"><img src="${item.image}" alt=""></div>
        <div class="cnt">
            <h2>${item.title}</h2>
            <p class="description">${item.description}</p>
            <div class="price">
                <span class="v-unit">¥</span><span class="v-value">${(item.price/100)?c}</span>
            </div>
            <div class="num">购买数量：1</div>
        <#if user??>
            <div class="oprt f-cb">
                <#if user.role=0>
                    <a href="/system/api/edit?id=${item.id}" class="u-btn u-btn-primary">编 辑</a>
                <#else>
                    <#if item.sold!=0>
                        <span class="u-btn u-btn-primary z-dis">已购买</span>
                        <span class="buyprice">购买价格：¥${(item.price/100)?c}</span>
                    <#else>
                        <button class="u-btn u-btn-primary" id="add" data-id="${item.id}"
                                data-title="${item.title}" data-price="${item.price?c}">
                            加入购物车
                        </button>
                    </#if>
                </#if>
            </div>
        </#if>
        </div>
    </div>
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>详细信息</h2>
    </div>
    <div class="n-detail">
    ${item.detail}
    </div>
</div>
<#include "include/footer.ftl">
<script type="text/javascript" src="/js/global.js"></script>
<script type="text/javascript" src="/js/pageShow.js"></script>
</body>
</html>