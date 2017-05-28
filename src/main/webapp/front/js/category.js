$(document).ready(function() {
    var select_category = $("#select_category");
    var select_sec_category = $("#select_sec_category");
    var loadSec = function(category) {
        var apSec = javaweb.createAP("/categoryJson?method=getSecByCategory&category=" + category);
        apSec.success = function(json) {
            select_sec_category.find("option").remove();
            var secs = json.listSecCategory;
            for (var i = 0; i < secs.length; i++) {
                var sec = secs[i];
                var str = `<option value="${sec.id}">${sec.name}</option>`;
                select_sec_category.append(str);
            }
        };
        $.ajax(apSec);
    };

    //load province and city
    var apCate = javaweb.createAP("/categoryJson?method=getAllCategory");
    select_category.change(function() {
        loadSec(select_category.val());
    });
    apCate.success = function(json) {
        var cates = json.listCategory;
        var first = cates[0].id;
        loadSec(first);
        for (var i = 0; i < cates.length; i++) {
            var cate = cates[i];
            var str = `<option value="${cate.id}">${cate.name}</option>`;
            select_category.append(str);
        }
    };
    $.ajax(apCate);
});