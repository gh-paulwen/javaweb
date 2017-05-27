var javaweb = {};

javaweb.base = "/javaweb";

javaweb.AjaxParameter = function() {
    this.dataType = "json";
    this.type = "GET";
    return this;
};

javaweb.createAP = function(url) {
    var ap = new javaweb.AjaxParameter();
    ap.url = javaweb.base + url;
    return ap;
};