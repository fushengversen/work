<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>首页</title>
    <meta charset="UTF-8"/>
    <meta http-equiv="Content-Type" content="text/html"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link rel="stylesheet" role="text/css" href="/css/style.css" charset="UTF-8"/>

</head>
<body>
<#include "include/header.ftl">
<div class="g-doc">
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <div class="tab">
            <ul>
            <#if user?? && user.role=1>
                <#if role=1>
                    <li><a href="/system">所有内容</a></li>
                    <li class="z-sel"><a href="/system?role=1">未购买的内容</a></li>
                <#else>
                    <li class="z-sel"><a href="/system">所有内容</a></li>
                    <li><a href="/system?role=1">未购买的内容</a></li>
                </#if>
            <#else>
                <li class="z-sel"><a href="/system">所有内容</a></li>
            </#if>
            </ul>
        </div>
    </div>
    <div class="n-plist">
        <ul class="f-cb" id="plist">
        <#list allItems>
            <#items as item>
                <#if user??>
                    <#if user.role=0>
                        <li id="p-${item.id}">
                            <a href="/system/show?id=#{item.id}" class="link">
                                <div class="img"><img src="${item.image}"
                                                      alt="${item.title}">
                                </div>
                                <h3>${item.title}</h3>
                                <div class="price"><span class="v-unit">¥</span>
                                    <span class="v-value">${(item.price/100)?c}</span>
                                </div>
                            </a>
                            <#if item.sold=0>
                                <span class="u-btn u-btn-normal u-btn-xs del"
                                      data-del="${item.id}">删除</span>
                            <#else>
                                <span class="had"><b>已售出${item.sold}件</b></span>
                            </#if>
                        </li>
                    <#elseif role=0>
                        <li id="p-${item.id}">
                            <a href="/system/show?id=#{item.id}" class="link">
                                <div class="img"><img src="${item.image}"
                                                      alt="${item.title}">
                                </div>
                                <h3>${item.title}</h3>
                                <div class="price"><span class="v-unit">¥</span>
                                    <span class="v-value">${(item.price/100)?c}</span>
                                </div>
                            </a>
                            <#if (item.sold > 0)>
                                <span class="had"><b>已购买</b></span>
                            </#if>
                        </li>
                    <#elseif item.sold=0>
                        <li id="p-${item.id}">
                            <a href="/system/show?id=#{item.id}" class="link">
                                <div class="img"><img src="${item.image}"
                                                      alt="${item.title}">
                                </div>
                                <h3>${item.title}</h3>
                                <div class="price"><span class="v-unit">¥</span>
                                    <span class="v-value">${(item.price/100)?c}</span>
                                </div>
                            </a>
                        </li>
                    </#if>
                <#else>
                    <li id="p-${item.id}">
                        <a href="/system/show?id=#{item.id}" class="link">
                            <div class="img"><img src="${item.image}"
                                                  alt="${item.title}">
                            </div>
                            <h3>${item.title}</h3>
                            <div class="price"><span class="v-unit">¥</span>
                                <span class="v-value">${(item.price/100)?c}</span>
                            </div>
                        </a>
                    </li>
                </#if>
            </#items>
        </#list>
        </ul>
    </div>
</div>
<#include "include/footer.ftl">
</body>
<script type="text/javascript" src="/js/global.js"></script>
<script type="text/javascript" src="/js/pageIndex.js"></script>
</html>