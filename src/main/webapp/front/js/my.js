$(document).ready(function() {
    javaweb.check(0, "login.html");
    var apInfo = javaweb.createAP("/userJson?method=getInfo");
    var apAddress = javaweb.createAP("/addressJson?method=getVerbose");
    apInfo.success = function(json) {
        $("#info_username").html(json.info.name);
        $("#info_register_date").html(json.info.registerDate);
        $("#info_email").html(json.info.email);
    };
    apAddress.success = function(json) {
        var verboses = json.listVerbose;
        for (var i = 0; i < verboses.length; i++) {
            var verbose = verboses[i];
            var tr = $(document.createElement("tr"));
            var tdRegion = $(document.createElement("td"));
            var tdVerbose = $(document.createElement("td"));
            var tdName = $(document.createElement("td"));
            var tdPhone = $(document.createElement("td"));
            var tdDelete = $(document.createElement("td"));
            var a = $(document.createElement("a"));
            a.append("删除");
            a.click((function(vid) {
                return function() {
                    var apDelete = javaweb.createAP(`/addressJson?method=delete&id=${vid}`);
                    apDelete.success = function(json) {
                        alert(json.message);
                    };
                    $.ajax(apDelete);
                };
            })(verbose.id));
            tdRegion.append(verbose.region);
            tdVerbose.append(verbose.verboseAddress);
            tdName.append(verbose.receiverName);
            tdPhone.append(verbose.receiverPhone);
            tdDelete.append(a);
            tr.append(tdRegion);
            tr.append(tdVerbose);
            tr.append(tdName);
            tr.append(tdPhone);
            tr.append(tdDelete);
            $("#t_address").append(tr);
        }
    };
    $.ajax(apInfo);
    $.ajax(apAddress);
});


$(function() {
    var apStore = javaweb.createAP("/storeJson?method=getByUser");
    apStore.success = function(json) {
        var stores = json.listStore;
        var div_stores = $("#div_stores");
        for (var i = 0; i < stores.length; i++) {
            var store = stores[i];
            var div = $(document.createElement("div"));
            div.click((function(sid) {
                return function() {
                    location.href = "my_store.html?sid=" + sid;
                };
            })(store.id));
            div.addClass("col-xs-3 store");
            var img = $(document.createElement("img"));
            img.attr("src", "img/logo.png");
            img.attr("style", "width:240px;");
            var h3 = $(document.createElement("h3"));
            h3.append(store.name);
            div.append(img);
            div.append(h3);
            div_stores.append(div);
            div_stores.append("<div class='col-xs-1'></div>");
        }
    };
    $.ajax(apStore);
});

$(function() {
    $("#btn_logout").click(function() {
        var logoutap = javaweb.createAP("/userJson?method=logout");
        logoutap.success = function(json) {
            alert(json.message);
            location.href = "login.html";
        };

        $.ajax(logoutap);
    });
});


$(function() {
    $("#btn_edit").click(function() {
        location.href = "my_edit.html";
    });
});

$(function() {
    $("#btn_admin").click(function() {
        location.href = "admin.html";
    });
});