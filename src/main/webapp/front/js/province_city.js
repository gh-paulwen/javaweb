$(document).ready(function() {
    var loadCity = function(province) {
        var apCity = javaweb.createAP("/city?method=getCity&province=" + province);
        apCity.success = function(json) {
            select_city.find("option").remove();
            var cities = json.listCity;
            for (var i = 0; i < cities.length; i++) {
                var city = cities[i];
                var str = `<option value="${city.id}">${city.name}</option>`;
                select_city.append(str);
            }
        };
        $.ajax(apCity);
    };

    //load province and city
    var apProvince = javaweb.createAP("/city?method=getProvince");
    var select_province = $("#select_province");
    select_province.change(function() {
        loadCity(select_province.val());
    });
    var select_city = $("#select_city");
    apProvince.success = function(json) {
        var provinces = json.listProvince;
        var first = provinces[0].id;
        loadCity(first);
        for (var i = 0; i < provinces.length; i++) {
            var province = provinces[i];
            var str = `<option value="${province.id}">${province.name}</option>`;
            select_province.append(str);
        }
    };
    $.ajax(apProvince);
});