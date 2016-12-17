// - html5 - placeholder imitator
function placeholder_imitator() {
    $('input[title]').each(function (i, e) {
        var title = $(e).attr('title');
        $(e).attr('value', title);
    });
    $('input[title]').bind({
        focus:function () {
            if ($(this).attr('title') == $(this).attr('value')) {
                $(this).attr({'value':''});
            }
        },
        blur:function () {
            if ($(this).attr('value') == '') {
                $(this).attr({'value':$(this).attr('title')});
            }
        }
    });
    $('textarea[title]').each(function (i, e) {
        $(e).attr('value', $(e).attr('title'));
    });
}

$(document).ready(function () {
    placeholder_imitator();
});

function show_navig(menu_id, button, element_id) {
    $('#' + menu_id + ' a.link').removeClass('select');
    $(button).addClass('select');
    $('#' + menu_id + ' div.down_menu').hide();
    $('#' + element_id).fadeIn(300);
}

function show_pad_menu(menu_id, from_element) {
    var menu = $('#' + menu_id);
    var from = $('#' + from_element);
    menu.stop(true);
    from.addClass('selected');
    $('*', from).addClass('selected');
    var left = from.offset().left;// + Math.round(from.width()/2) - Math.round(menu.width()/2)
    var top = from.offset().top + from.height();
    if (from.hasClass('select')) {
        top = top - 17;
        left = left - 5;
    }
    menu.css({top:top + 'px', left:left + 'px'}).fadeIn(100, function () {
        $(this).css({opacity:1});
    });
}
function hide_pad_menu(menu_id, from_element) {
    $('#' + menu_id).stop(true);
    $('#' + menu_id).fadeOut(100);
    var from = $('#' + from_element);
    from.removeClass('selected');
    $('*', from).removeClass('selected');
}
function show_pad2_menu(menu_id, from_element) {
    $('#' + menu_id).stop(true);
    $('#' + from_element).addClass('select');
    var left = $('#' + from_element).offset().left - ($('#' + menu_id).innerWidth() - $('#' + from_element).outerWidth());
    var top = $('#' + from_element).offset().top + $('#' + from_element).height();
    $('#' + menu_id).css({top:top + 'px', left:left + 'px'}).fadeIn(100, function () {
        $(this).css({opacity:1});
    });
}
function hide_pad2_menu(menu_id, from_element) {
    $('#' + menu_id).stop(true);
    $('#' + menu_id).fadeOut(100);
    $('#' + from_element).removeClass('select');
}

// - Slide bg elements
function to_element(element){
    var fly_bg = $('#fly_bg');
    $(fly_bg).stop(true).show();
    var to_element = $(element);
    var left = $(to_element).position().left;
    var width = $(to_element).width();
    $('.fly_element div').removeClass('active');
    $('div', element).addClass('active');
    if (to_element.hasClass('first')){
        left = left + 9;
        width = width - 7;
    }
    if (to_element.hasClass('last')){
        width = width - 5;
    }
    $(fly_bg).animate({left: (left - 10) + 'px', width: (width + 20) + 'px'});
}

// - Slideshow
var SlideT;
var col_slides;
var next_slide;
function ToggleSlide(slide_id) {
    clearTimeout(SlideT);
    col_slides = 0;
    $('#slides_control a').each(function (i, e) {
        col_slides++;
        $(e).removeClass('active');
        if (slide_id == (i + 1)) {
            $(e).addClass('active');
        }
    });
    if (col_slides <= 1) return;
    $('#slider div').each(function (i, e) {
        if (slide_id != (i + 1)) {
            if ($(e).css('display') == 'block') {
                $(e).stop(true).fadeTo(600, 0, function () {
                    $(this).css({'display':'none', 'opacity':0});
                });
            }
        } else {
            $(e).stop(true).css({'display':'none', 'opacity':0}).fadeTo(600, 1, function () {
                $(this).css({'display':'block', 'opacity':1});
            });
        }
    });

    if (slide_id == col_slides) {
        slide_id = 1;
    } else {
        slide_id++;
    }

    next_slide = slide_id;

    SlideT = setTimeout(function () {
        ToggleSlide(slide_id);
    }, 6000);
}
$('#slider').ready(function () {
    ToggleSlide(1);
    $('#slider').mouseover(
        function () {
            clearTimeout(SlideT);
        }).mouseout(function () {
            SlideT = setTimeout(function () {
                ToggleSlide(next_slide);
            }, 2000);
        });
});

function show_all_pages() {
    var full = $('.navi_pagination .full');
    var all_button = $('.navi_pagination .all');

    if (full.css('display') == 'none') {
        full.slideDown(300);
        $('img', all_button).attr('src', 'images/arrow_top.png');
    } else {
        full.slideUp(300);
        $('img', all_button).attr('src', 'images/arrow_down.png');
    }
}

function sending_block_show() {
    var sending_block = $('.sending_block');
    if (sending_block.css('display') == 'block') {
        sending_block.fadeOut(300);
    } else {
        sending_block.fadeIn(300);
    }
}
function tv_params_block_show(button) {
    var tv_params = $('.tv_params');
    if (tv_params.css('opacity') == 1) {
        tv_params.fadeTo(300, 0);
        $('span', button).html('Параметры');
        $('img', button).attr('src', 'images/arrow_link_down.png');
    } else {
        tv_params.fadeTo(300, 1);
        $('span', button).html('Закрыть');
        $('img', button).attr('src', 'images/arrow_link_top.png');
    }
}
function show_reg_form() {
    $('#auth_block').slideUp(300);
    $('#reg_block').slideDown(300);
}
function show_auth_form() {
    $('#auth_block').slideDown(300);
    $('#reg_block').slideUp(300);
}

function show_header_bottom_line() {
    var bottom_line = $('#header_bottom_line');
    if (bottom_line.css('display') == 'block') {
        bottom_line.slideUp(300);
    } else {
        bottom_line.slideDown(300);
    }
}

function createModal(url) {
    $('body').append('<div id="modal_bg">&nbsp;</div>');
    $('#modal_bg').fadeTo(300, 0.8).bind({
        click:function () {
            hideModal();
        }
    });

    $('body').append('<div id="modal_win_bg"></div>');
    $('#modal_win_bg').append('<div id="modal_win"></div>');
    $('#modal_win').append('<a href="javascript:hideModal();" id="modal_win_close">X</a><div id="modal_win_in"></div>');

    resizeModal(300, 200);
    updateModal(url);
}
function hideModal() {
    $('#modal_bg').remove();
    $('#modal_win_bg').remove();
    $('.imgareaselect-selection').parent().remove();
    $('.imgareaselect-outer').remove();
}
function waitModal() {
    $('#modal_win').css({'background-color':'#ffffff', 'background-image':'url(/static/img/preloader.gif)', 'background-repeat':'no-repeat', 'background-position':'center center'});
    resizeModal(300, 200);
    $('#modal_win_in').fadeTo(300, 0);
}
function updateModal(url) {
    waitModal();
    $.get(url, function (data) {
        if (data == '') {
            hideModal();
            return;
        }

        var modal_win_in = $('#modal_win_in');
        modal_win_in.html(data);
        placeholder_imitator();
        updateSizeModal();
    });
}
function resizeModal(w, h, callback) {
    var modal_bg = $('#modal_win_bg');
    var modal = $('#modal_win');
    modal_bg.animate({'margin-top':'-' + (h / 2 + 10) + 'px', 'margin-left':'-' + (w / 2 + 10) + 'px'}, 300);
    modal.animate({'width':w + 'px', 'height':h + 'px'}, 300, callback);
}
function updateSizeModal() {
    var modal_win_in = $('#modal_win_in');

    width = 0;
    height = 0;

    $('*', modal_win_in).each(function (i, e) {
        if (width < $(e).outerWidth(true)) {
            width = $(e).outerWidth(true);
            height = $(e).outerHeight(true);
        }
    });

    width = (width >= $(window).width()) ? ($(window).width() - 100) : width;

    height = (height >= $(window).height()) ? ($(window).height() - 100) : height;

    resizeModal(width, height, function () {
        $('#modal_win').css('background', '#ffffff');
        $('#modal_win_in').fadeTo(300, 1);
    });
}
function show_local_message(from_element, message) {
    $('body').append('<div id="local_message"><p>' + message + '</p></div>');
    var from = $(from_element);
    var message_e = $('#local_message');

    var top = from.offset().top - (from.outerHeight(true) + message_e.outerHeight(true) - 10);
    var left = from.offset().left;

    message_e.css({'opacity':0, 'display':'table', 'top':top + 'px', 'left':left + 'px'}).animate({'opacity':1}, 300, function () {
        setTimeout(function () {
            $('#local_message').fadeTo(300, 0, function () {
                $('#local_message').remove();
            });
        }, 5000);
    });
}

var message_timer;
function show_message(message_text, time) {

    $('body').append('<div id="window_message">' + message_text + '</div>');
    var message_e = $('#window_message');
    message_e.css({'margin-left':'-' + Math.ceil(message_e.outerWidth() / 2) + 'px', 'margin-top':'-' + Math.ceil(message_e.outerHeight() / 2) + 'px'});
    message_timer = setTimeout(function () {
        hide_message();
    }, (time * 1000));
}
function hide_message() {
    clearTimeout(message_timer);
    $('#window_message').fadeOut(300, function () {
        $('#window_message').remove();
    });
}

$("#back-top").hide();
$(function () {
    $(window).scroll(function () {
        if ($(this).scrollTop() > 100) {
            $('#back-top').fadeIn();
        } else {
            $('#back-top').fadeOut();
        }
    });
    $('#back-top').click(function () {
        $('body,html').animate({
            scrollTop:0
        }, 800);
        return false;
    });
});
function show_partner_block(elem_id, from_elem){
    var element = $('#' + elem_id);

    if (element.css('display')=='none'){
        element.slideDown(300);
        $(from_elem).css({borderRadius: '5px 5px 0 0'});
        $('span', from_elem).text('-');
    } else {
        element.slideUp(300);
        $(from_elem).css({borderRadius: '5px'});
        $('span', from_elem).text('+');
    }
}


function createDialog(text){
    $('body').append('<div id="modal_bg">&nbsp;</div>');
    $('#modal_bg').fadeTo(300, 0.8).bind({
        click: function(){
            hideModal();
        }
    });

    $('body').append('<div id="modal_win_bg"></div>');
    $('#modal_win_bg').append('<div id="modal_win"></div>');
    $('#modal_win').append('<a href="javascript:hideModal();" id="modal_win_close">X</a><div id="modal_win_in"></div>');

    resizeModal(300, 200);
    updateDialog(text);
}
function updateDialog(text){
    var modal_win_in = $('#modal_win_in');
    modal_win_in.html('<div id="dialog">' + text + '</div>');
    placeholder_imitator();
    updateSizeModal();
}

function busResistration() {
    var n = $('#bus_name').val();
    var p = $('#bus_phone').val();
    var e = $('#bus_email').val();
    var m = $('#bus_message').val();
    if (n.length > 50) {
        n = n.substr(0, 50);
    }

    if (p.length > 30) {
        p = p.substr(0, 30);
    }

    if (e.length > 50) {
        e = e.substr(0, 50);
    }

    if (m.length > 300) {
        m = m.substr(0, 300);
    }
    $.post('/city/registration', {name:n, phone:p, email:e, message:m}, function (response) {
        if (response.complete) {
            alert('Отправлено!');
            $('#bus_name').val('');
            $('#bus_phone').val('');
            $('#bus_email').val('');
            $('#bus_message').val('');
        } else {
            alert('Не все поля введены');
        }
    }, 'json');
}

var nav = {
    go:function (loc, ev, opts) {

    }
}






function update_timer(date_count) {
    ts = countdown(date_count, null, countdown.DAYS|countdown.HOURS|countdown.MINUTES|countdown.SECONDS);
    if (ts.days>0){
        $('.hour_day_type').html('д');
        $('.minute_hour_type').html('ч');
        $('.second_minute_type').html('м');
        $(".hour_day_data").html(ts.days);
        $(".minute_hour_data").html(ts.hours);
        $(".second_minute_data").html(ts.minutes);
    } else {
        $('.hour_day_type').html('ч');
        $('.minute_hour_type').html('м');
        $('.second_minute_type').html('с');
        $(".hour_day_data").html(ts.hours);
        $(".minute_hour_data").html(ts.minutes);
        $(".second_minute_data").html(ts.seconds);
    }
}




