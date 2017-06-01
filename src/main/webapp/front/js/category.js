javaweb.loadSec = function(category, ssc) {
    var apSec = javaweb.createAP("/categoryJson?method=getSecByCategory&category=" + category);
    apSec.success = function(json) {
        ssc.find("option").remove();
        var secs = json.listSecCategory;
        for (var i = 0; i < secs.length; i++) {
            var sec = secs[i];
            var str = `<option value="${sec.id}">${sec.name}</option>`;
            ssc.append(str);
        }
    };
    $.ajax(apSec);
};


javaweb.loadCategory = function(after) {
    var select_category = $("#select_category");
    var select_sec_category = $("#select_sec_category");
    //load province and city
    var apCate = javaweb.createAP("/categoryJson?method=getAllCategory");
    select_category.change(function() {
        javaweb.loadSec(select_category.val(), select_sec_category);
    });
    apCate.success = function(json) {
        var cates = json.listCategory;
        var first = cates[0].id;
        javaweb.loadSec(first, select_sec_category);
        for (var i = 0; i < cates.length; i++) {
            var cate = cates[i];
            var str = `<option value="${cate.id}">${cate.name}</option>`;
            select_category.append(str);
        }
        $.ajax(after);
    };
    $.ajax(apCate);
};