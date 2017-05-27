$(document).ready(function() {
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