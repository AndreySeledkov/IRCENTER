function show_pad2_menu(menu_id, from_element){
    $('#' + menu_id).stop(true);
    $('#' + from_element).addClass('select');
    var left = $('#' + from_element).offset().left - ($('#' + menu_id).innerWidth() - $('#' + from_element).outerWidth());
    var top = $('#' + from_element).offset().top + $('#' + from_element).height();
    $('#' + menu_id).css({top: top + 'px', left: left + 'px'}).fadeIn(100, function(){$(this).css({opacity: 1});});
}
function hide_pad2_menu(menu_id, from_element){
    $('#' + menu_id).stop(true);
    $('#' + menu_id).fadeOut(100);
    $('#' + from_element).removeClass('select');
}



function open_product_group(element){

	var element = $('#' + element);

	if ($(element).hasClass('open')){
		return false;
	}

	$('#left_sidebar ul.category.open').each(function(i, e){$(e).stop(true).animate({height: '24px'}, 500, function(){$(this).removeClass('open');});});

	var col_group = 1;
	$('li.group', element).each(function(i, e){col_group++;});
	$(element).animate({height: (col_group*24) + 'px'}, 500, function(){$(this).addClass('open');});

	return false;
}
