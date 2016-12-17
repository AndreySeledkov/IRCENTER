function sendMessage(receiverId) {
    $.post('/messages/send_message', {userId:receiverId, message:$('#messageText').val()}, function (response) {
        location.reload();
    }, 'json');
}

function deleteFriend(id) {
    $.ajax({
        url:'/friends/delete_friend',
        dataType:'text',
        data:{ friend_id:id },
        success:function (response) {
            if (response == 'Ok') {
                location.reload();
            }
        }
    })
}
function deleteRequestFriend(id) {
    $.ajax({
        url:'/friends/delete_request_friend',
        dataType:'text',
        data:{ request_friend_id:id },
        success:function (response) {
            if (response == 'Ok') {
                location.reload();
            }
        }
    })
}

function addToFriend(id) {
    $.ajax({
        url:'/friends/add_to_friend',
        dataType:'text',
        data:{ request_friend_id:id },
        success:function (response) {
            if (response == 'Ok') {
                location.reload();
            }
        }
    })
}

function deleteMessage(messageId) {
    $.ajax({
        url:'/messages/delete_message',
        dataType:'text',
        data:{ message_id:messageId },
        success:function (response) {
            if (response == 'Ok') {
                location.reload();
            }
        }
    })

}


function removeFavoritePost(post_id) {
    $.ajax({
        url:"/favorite/remove",
        dataType:'text',
        data:{ postId:post_id },
        success:function (response) {
            if (response == 'Ok') {
                location.reload();
            }
        }
    })
}


function addPostInFavorite(post_id) {
    $.ajax({
        url:"/favorite/like",
        dataType:'text',
        data:{ postId:post_id },
        success:function (response) {
            if (response == 'Ok') {
                //todo
            }
        }
    })
}