<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>网易买卖家系统</title>
    <link rel="stylesheet" href="/css/style.css"/>
</head>
<body>
<#include "include/header.ftl">
<div class="g-doc">
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>内容编辑</h2>
    </div>
    <div class="n-public">
        <form class="m-form m-form-ht" id="form" method="post" action="/system/api/editSubmit?id=${item.id}" onsubmit="return false;" autocomplete="off">
            <div class="fmitem">
                <label class="fmlab">标题：</label>
                <div class="fmipt">
                    <input type="hidden" name="id" value="${item.id}"/>
                    <input class="u-ipt ipt" name="title" value="${item.title}" placeholder="2-80字符"/>
                </div>
            </div>
            <div class="fmitem">
                <label class="fmlab">摘要：</label>
                <div class="fmipt">
                    <input class="u-ipt ipt" name="summary" value="${item.description}" placeholder="2-140字符"／>
                </div>
            </div>
            <div class="fmitem">
                <label class="fmlab">图片：</label>
                <div class="fmipt" id="uploadType">
                    <input name="image" type="radio" value="url" checked /> 图片地址
                    <input name="image" type="radio" value="file" /> 本地上传
                </div>
            </div>
            <div class="fmitem">
                <label class="fmlab"></label>
                <div class="fmipt" id="urlUpload">
                    <input class="u-ipt ipt"  name="image" value="${item.image}" placeholder="图片地址"/>
                </div>
                <div class="fmipt" id="fileUpload"  style="display:none">
                    <input class="u-ipt ipt" name="file" type="file" id="fileUp"/>
                    <button class="u-btn u-btn-primary" id="upload">上传</button>
                </div>
            </div>
            <div class="fmitem">
                <label class="fmlab">正文：</label>
                <div class="fmipt">
                    <textarea class="u-ipt" name="detail" rows="10" placeholder="2-1000个字符">${item.detail}</textarea>
                </div>
            </div>
            <div class="fmitem">
                <label class="fmlab">价格：</label>
                <div class="fmipt">
                    <input class="u-ipt price" name="price" value="${(item.price/100)?c}"/>元
                </div>
            </div>
            <div class="fmitem fmitem-nolab fmitem-btn">
                <div class="fmipt">
                    <button type="submit" class="u-btn u-btn-primary u-btn-lg" id="publish">保 存</button>
                </div>
            </div>
        </form>
        <span class="imgpre"><img src="${item.image}" alt="" id="imgpre"></span>
    </div>
</div>
<#include "include/footer.ftl">

</body>
<script type="text/javascript" src="/js/global.js"></script>
<script type="text/javascript" src="/js/publish.js"></script>
</html>