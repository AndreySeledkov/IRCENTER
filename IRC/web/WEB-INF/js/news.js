var news = {

    switchSection:function (newSection, e) {
        $.get('/news/embed_news?partId=' + newSection, function (data) {
            $("#news_embed").html(data);
        });
    },

    like:function (typ, idn) {
        $.post('/like', {type:typ, id:idn}, function (response) {
            if (response.complete) {
                var v = eval($(".group_likes").text());
                $(".group_likes").text(v + 1);
            }
        }, 'json');
    },

    comment:function (idn) {
        $.post('/news/comment', {text:$('#comment_content').val(), id:idn}, function (response) {
            if (response.complete) {
                $('#comment_content').val('');
                location.reload();
            }
        }, 'json');

    },

    navigation:function (button, element_id) {
        $('#navig_menu table a').removeClass('select');
        $(button).addClass('select');
        $('#navig_menu div.down_menu').hide();
        $('#' + element_id).fadeIn(300);
    }

}


