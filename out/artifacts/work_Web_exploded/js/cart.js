(function(w,d,u){
    var settleAccount = util.get('settleAccount');
    if(!settleAccount){
        return;
    }

    var productList = [];

    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            var status = xhr.status;
            if (status >= 200 && status < 300 || status === 304) {
                var json = JSON.parse(xhr.responseText);
                if (json && json.code === 200) {
                    productList = json.data;
                } else {
                    alert(json.message);
                }
            } else {
                loading.result(message || '获取购物车失败');
            }
        }
    };
    xhr.open('GET', '/system/api/getCart', false);
    xhr.send(null);
    var name = 'products';
    var products = util.getCookie(name);
    var $ = function(id){
        return document.getElementById(id);
    };

    var str = "<tr>" +
        "<th>" + '商品名称'  + "</th>"+
        "<th>" + '数量' + "</th>" +
        "<th>" + '价格' + "</th>" +
        "</tr>";

    for(var i = 0; i < productList.length; i++){
        str = str +
            "<tr>" +
            "<td>" + productList[i].title  + "</td>"+
            "<td>" +
            "<span class=\"totalNum\" id=\"allNum\">" + productList[i].num + "</span>" +
            "<td>" + productList[i].price/100 + "</td>" +
            "</tr>";
    }

    $("newTable").innerHTML = str;

    window.onload = function(){
        $('newTable').onclick = function(e){
            var e = arguments[0] || window.event;
            target = e.srcElement ? e.srcElement : e.target;
            if(target.nodeName === "SPAN" && target.className === "moreNum"){
                var num = target.parentElement.children[1].textContent;
                var id = target.parentElement.children[2].textContent;
                num ++;
                target.parentElement.children[1].textContent = num;
                util.modifyOne(productList,id,num);
            }else if(target.nodeName === "SPAN" && target.className === "lessNum"){
                var num = target.parentElement.children[1].textContent;
                var id = target.parentElement.children[2].textContent;
                num --;
                if(num < 0){
                    alert("该商品数量为0");
                }else{
                    target.parentElement.children[1].textContent = num;
                    util.modifyOne(productList,id,num);
                }
            }
            return false;
        };
    };

    var loading = new Loading();
    var layer = new Layer();
    $('Account').onclick = function(e){
        var newProducts = productList.map(function(arr){
            return {'id':arr.id,'number':arr.num,'price':arr.price};
        });
        console.log(newProducts);
        var ele = e.target;
        layer.reset({
            content:'确认购买吗？',
            onconfirm:function(){
                layer.hide();
                loading.show();

                var xhr = new XMLHttpRequest();
                var data = JSON.stringify(newProducts);
                xhr.onreadystatechange = function(){
                    if(xhr.readyState === 4){
                        var status = xhr.status;
                        if(status >= 200 && status < 300 || status === 304){
                            var json = JSON.parse(xhr.responseText);
                            if(json && json.code === 200){
                                loading.result('购买成功',function(){location.href = './account';});
                                util.deleteCookie(name);
                            }else{
                                alert(json.message);
                            }
                        }else{
                            loading.result(message||'购买失败');
                        }
                    }
                };
                xhr.open('post','/system/api/buy');
                xhr.setRequestHeader('Content-Type','application/json');
                xhr.send(data);
            }.bind(this)
        }).show();
        return;
    };
    $('back').onclick = function(){
        window.history.back();
    }
})(window,document);