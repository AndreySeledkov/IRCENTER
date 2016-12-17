var svid_online = {

    switchSection:function (newSection, e) {
        $.get('/svid_online', function (data) {
            $("#svid_online").html(data);
        });
    },

    navigation:function (button, element_id) {
        $('#navig_menu table a').removeClass('select');
        $(button).addClass('select');
        $('#navig_menu div.down_menu').hide();
        $('#' + element_id).fadeIn(300);
    }

}


