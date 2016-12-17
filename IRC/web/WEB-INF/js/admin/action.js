var action = {
    saveNews:function () {
        var t = $('#title').val();
        var p = $('#name').val();
        var d = $('#date_start').val();
        var i = $('#image').val();
        var bod = CKEDITOR.instances['area3'].getData();
        if (t == undefined || t == '' || p == undefined || p == '' || d == undefined || d == '' || i == undefined || i == '' || bod == undefined || bod == '') {
            alert('Не все поля введены!');
            return;
        }
        $.post('/admin/save_news', {title:t, partId:p, startDate:d, defimage:i, body:bod},
            function (response) {
                if (response.complete) {
                    alert('Новость сохранена!');
                    $('#title').val('');
                    $('#name').val('');
                    $('#date_start').val('');
//                    $('#date_stop').val('');
                    $('#image').val('')
                    location.reload();
                }
            }, 'json');
        return false;
    },

    updateNews:function (id) {
        var t = $('#title').val();
        var p = $('#id').val();
        var d = $('#date_start').val();
        var i = $('#image').val();
        var bod = CKEDITOR.instances['area3'].getData();
        if (t == undefined || t == '' || p == undefined || p == '' || d == undefined || d == '' || i == undefined || i == '' || bod == undefined || bod == '') {
            alert('Не все поля введены!');
            return;
        }
        $.post('/admin/save_news', {title:t, partId:p, startDate:d, defimage:i, body:bod, newsId:id},
            function (response) {
                if (response.complete) {
                    alert('Новость изменена!');
                }
            }, 'json');
    },
    removeNews:function (id) {
        $.get('/admin/remove_news?id=' + id, function (data) {
            alert('Новость удалена!');
            location.reload();
        });
    }
}