function rightSideUpdateModal(href, page) {
    history.pushState({path:this.path}, '', href);

    if (href == "/friends?section=all") {
        $.get('/friends/friends_form?page=' + page, function (data) {
            var profile_right_column = $('#bottom_side_bar');
            profile_right_column.html(data);
        });
    } else if (href == "/friends?section=online") {
        $.get('/friends/friends_online_form?page=' + page, function (data) {
            var profile_right_column = $('#bottom_side_bar');
            profile_right_column.html(data);
        });
    } else if (href == "/friends?section=all_requests") {
        $.get('/friends/friends_request_form?page=' + page, function (data) {
            var profile_right_column = $('#bottom_side_bar');
            profile_right_column.html(data);
        });
    } else if (href == "/friends?section=search") {
        $.get('/friends/friends_search_form?page=' + page, function (data) {
            var profile_right_column = $('#bottom_side_bar');
            profile_right_column.html(data);
        });
    } else if (href == "/favorite") {
        $.get('/favorite/favorite_form', function (data) {
            var profile_right_column = $('#bottom_side_bar');
            profile_right_column.html(data);
        });
    }

    return false;
}

function rightSideUpdateMessagesModal(href, section, page) {
    history.pushState({path:this.path}, '', href);
    if (href == "/messages?section=inbox" || href == "/messages?section=outbox") {
        $.get('/messages/messages_form?section=' + section + '&page=' + page, function (data) {
            var profile_right_column = $('#bottom_side_bar');
            profile_right_column.html(data);
        });
    }
    return false;
}


function rightSideUpdateMessageModal(href, userId) {
//    history.pushState({path:this.path}, '', href);
//    if (href == "/new_message") {
    $.get('/messages/message_form?userId=' + userId, function (data) {
        var profile_right_column = $('#bottom_side_bar');
        profile_right_column.html(data);
    });
//    }
    return false;
}

function rightSideUpdateSearchModal(href) {
    history.pushState({path:this.path}, '', href);
    if (href == "/friends?section=search") {
        $.get('/friends/friends_search_form?searchText=' + $('#searchText').val(), function (data) {
            var profile_right_column = $('#bottom_side_bar');
            profile_right_column.html(data);
        });
    }
    return false;
}