$(function() {
    var apUser = javaweb.createAP("/userJson?method=getAll");
    apUser.success = function(json) {
        var users = json.listUser;
        for (var i = 0; i < users.length; i++) {
            var user = users[i];
            var tr = $(document.createElement("tr"));
            var tdId = $(document.createElement("td"));
            var tdName = $(document.createElement("td"));
            var tdType = $(document.createElement("td"));
            var tdRegisterDate = $(document.createElement("td"));
            var tdEmail = $(document.createElement("td"));
            var tdDelete = $(document.createElement("td"));
            var a = $(document.createElement("a"));
            a.append("删除");
            a.click((function() {
                return function() {};
            })());
            tdId.append(user.id);
            tdName.append(user.name);
            tdType.append(user.type);
            tdRegisterDate.append(user.registerDate);
            tdEmail.append(user.email);
            tdDelete.append(a);
            tr.append(tdId);
            tr.append(tdName);
            tr.append(tdType);
            tr.append(tdRegisterDate);
            tr.append(tdEmail);
            tr.append(tdDelete);
            $("#t_users").append(tr);
        }
    };


    var apStore = javaweb.createAP("/storeJson?method=getAll");
    apStore.success = function(json) {
        var stores = json.listStore;
        for (var i = 0; i < stores.length; i++) {
            var store = stores[i];
            var tr = $(document.createElement("tr"));
            var tdId = $(document.createElement("td"));
            var tdName = $(document.createElement("td"));
            var tdOwner = $(document.createElement("td"));
            var tdRegisterDate = $(document.createElement("td"));
            var tdDelete = $(document.createElement("td"));
            var a = $(document.createElement("a"));
            a.append("删除");
            a.click(function() {});
            tdId.append(store.id);
            tdName.append(store.name);
            tdOwner.append(store.owner);
            tdRegisterDate.append(store.registerDate);
            tdDelete.append(a);
            tr.append(tdId);
            tr.append(tdName);
            tr.append(tdOwner);
            tr.append(tdRegisterDate);
            tr.append(tdDelete);
            $("#t_stores").append(tr);
        }

    };

    var apCheck = javaweb.createAP("/userJson?method=getInfo");
    apCheck.success = function(json) {
        var info = json.info;
        if (info.type != 3) {
            alert("无管理员权限");
            location.href = "my.html";
            return;
        }
        //加载其他
        $.ajax(apUser);
        $.ajax(apStore);
    };
    $.ajax(apCheck);

});