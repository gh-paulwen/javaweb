$(function() {
    var apOrder = javaweb.createAP("/orderJson?method=getByUser");
    javaweb.loadOrder(apOrder);
});