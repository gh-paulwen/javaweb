$(function() {
    javaweb.check(0, "login.html");
    var apOrder = javaweb.createAP("/orderJson?method=getByUser");
    javaweb.loadOrder(apOrder);
});