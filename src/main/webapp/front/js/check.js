javaweb.check = function(al, not) {
    var apCheck = javaweb.createAP("/userJson?method=getInfo");
    apCheck.success = function(json) {
        if (json.info) {
            //已登录
            if (al) {
                window.location.href = al;
            }
        } else {
            //未登录
            // window.location.href = "login.html";
            if (not) {
                alert("未登录");
                window.location.href = not;
            }
        }
    };
    $.ajax(apCheck);

}