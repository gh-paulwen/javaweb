$(function() {
    var apCollect = javaweb.createAP("/collectJson?method=getVerbose");
    apCollect.success = function(json) {
        var verboses = json.listVerbose,
            div_collects = $("#div_collects");
        for (var i = 0; i < verboses.length; i++) {
            var verbose = verboses[i],
                divcollect = $(document.createElement("div")),
                divrow = $(document.createElement("div")),
                div1 = $(document.createElement("div")),
                div2 = $(document.createElement("div")),
                div3 = $(document.createElement("div")),
                img = $(document.createElement("img")),
                h3 = $(document.createElement("h3")),
                p = $(document.createElement("p")),
                h2 = $(document.createElement("h2")),
                adelete = $(document.createElement("a"));
            divcollect.click((function(pid) {
                return function() {
                    location.href = "product_detail.html?pid=" + pid;
                };
            })(verbose.id));
            divrow.addClass("row");
            div1.addClass("col-xs-4");
            div2.addClass("col-xs-4");
            div3.addClass("col-xs-4");
            img.attr("style", "width:180px;");
            img.attr("src", javaweb.base + "/file?method=retrive&img=" + verbose.pic);
            h3.append("商品名称：" + verbose.name);
            p.append("商品描述：" + verbose.description);
            h2.append(`商品价格:<span style="color:red;">${verbose.price}</span>`);
            adelete.append("删除");
            adelete.click((function(pid) {
                return function() {
                    var apDelete = javaweb.createAP("/collectJson?method=delete&product=" + pid);
                    apDelete.success = function(json) {
                        alert(json.message);
                    }
                    $.ajax(apDelete);
                };
            })(verbose.id));
            div3.append(adelete);
            div2.append(h3);
            div2.append(p);
            div2.append(h2);
            div1.append(img);
            divcollect.append(div1);
            divcollect.append(div2);
            divcollect.append(div3);
            divcollect.attr("style", "min-height:200px;");
            div_collects.append(divcollect);
            divcollect.addClass("collect");
        }
    };
    $.ajax(apCollect);
});