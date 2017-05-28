$(function() {
    var apGet = javaweb.createAP("/categoryJson?method=getAllCategory");
    apGet.success = function(json) {
        var cates = json.listCategory;
        var select_category = $("#select_category");
        for (var i = 0; i < cates.length; i++) {
            var cate = cates[i];
            var str = `<option value="${cate.id}">${cate.name}</option>`;
            select_category.append(str);
        }
    };
    $.ajax(apGet);

    $("#btn_add_category").click(function() {
        var apSave = javaweb.createAP("/categoryJson");
        apSave.type = "POST";
        apSave.data = {
            "method": "saveCategory",
            "categoryName": $("#input_category").val()
        };
        apSave.success = function(json) {
            alert(json.message);
        }
        $.ajax(apSave);
    });
    $("#btn_add_sec_category").click(function() {
        var apSave = javaweb.createAP("/categoryJson");
        apSave.type = "POST";
        apSave.data = {
            "method": "saveSecCategory",
            "secCategoryName": $("#input_sec_category").val(),
            "category": $("#select_category").val()
        };
        apSave.success = function(json) {
            alert(json.message);
        };
        $.ajax(apSave);
    });
});