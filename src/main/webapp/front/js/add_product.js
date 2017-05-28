$(function() {
    var storeid = location.search.split("=")[1];
    $("#btn_next").click(function() {
        var apNext = javaweb.createAP("/productJson");
        apNext.type = "POST";
        apNext.data = {
            "method": "save",
            "store": storeid,
            "name": $("#input_name").val(),
            "price": $("#input_price").val(),
            "secCategory": $("#select_sec_category").val(),
            "description": $("#input_description").val()
        };

        apNext.success = function(json) {
            alert(json.message);
            location.href = "../upload.jsp?pid=" + json.productid;
        };

        $.ajax(apNext);
    });
});