window.onload = function() {
    var btnSubmit = document.getElementById("btn_upload");
    var img_small = document.getElementById("img_small");
    var img_big = document.getElementById("img_big");
    btnSubmit.onclick = function() {
        var data = new FormData(document.forms.namedItem("uploadForm"));
        alert(data);
        var req = new XMLHttpRequest();
        req.open("POST", "/javaweb/file?method=upload", true);
        req.onload = function(oEvent) {
            if (req.status == 200) {
                img_small.src = "/javaweb/file?method=retrive&img=" + req.responseText;
                img_big.src = "/javaweb/file?method=retrive&img=" + req.responseText;
            } else {
                alert("failed");
            }
        };
        req.send(data);
    };
};