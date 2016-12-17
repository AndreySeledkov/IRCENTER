var programm_tv_online = {

    switchSection:function (newSection, e) {
        $.get('/programm_tv?id=' + newSection, function (data) {
            if (newSection == 0) {
                $("#channel_1").html(data);
            } else if (newSection == 1) {
                $("#channel_2").html(data);
            } else if (newSection == 2) {
                $("#channel_3").html(data);
            } else if (newSection == 3) {
                $("#channel_4").html(data);
            }
        });
    },

    navigation:function (button, element_id) {
        $('#navig_menu table a').removeClass('select');
        $(button).addClass('select');
        $('#navig_menu div.down_menu').hide();
        $('#' + element_id).fadeIn(300);
    }

}


