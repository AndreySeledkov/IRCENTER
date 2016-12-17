var tabs = {
    addNews:function () {
        $.get('/admin/news/add_news', function (data) {
            $("#wpbody-content").html(data);
        });
    },

    sliderForm:function () {
        $.get('/admin/slider/slider_form', function (data) {
            $("#wpbody-content").html(data);
        });
    }

}