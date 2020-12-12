$(document).on('click', '.notification > button.delete', function() {
    $(this).parent().addClass('is-hidden');
    return false;
});

$(document).on('click', '#change-cover', function() {
    $(".modal").addClass('is-active');
    return false;
});

$(document).on('click', '.modal-card-head > button.delete', function() {
    $(this).closest(".modal").removeClass('is-active');
    return false;
});

function profileUpdate(type){
    $('.notification').addClass('is-hidden');
    if(type === 'profile'){
        $('#updateProfile').removeClass('is-hidden');
        $('#changePassword').addClass('is-hidden');
    }else if(type === 'password'){
        $('#updateProfile').addClass('is-hidden');
        $('#changePassword').removeClass('is-hidden');
    }
    return false;
};

function follow(action, username){
    if(action === 'follow'){
        $(".follow").attr("href", "follow?action=follow&username=" + username);
    }else{
        $(".follow").attr("href", "follow?action=unfollow&username=" + username);
    }
    
};

$(document).ready(function(){
    $("#hashtag").mouseover(function(){
        var hashtag = $("#hashtag").text();
        hashtag = hashtag.substring(1);
        $("#hashtag").attr("href","hashtag?action=viewTweets&hashtagText=" + hashtag);
    });
    var times = document.getElementsByClassName("timeAgo");
    for (var i = 0; i < times.length; i++){
        var result = timeAgo(times[i].innerText);
        times[i].innerText = result;
    }
});

function timeAgo (dateString) {
    var t = dateString.split(/[- :]/);
    
    var rightNow = new Date();
    var then = new Date(Date.UTC(t[0], t[1]-1, t[2], t[3], t[4], t[5]));
    var diff = rightNow - then;
    
    var second = 1000,
    minute = second * 60,
    hour = minute * 60,
    day = hour * 24,
    week = day * 7;
    
    diff -= hour * 7; //minus time offset for colorado

    if (isNaN(diff) || diff < 0) {
        return ""; // return blank string if unknown
    }

    if (diff < second * 2) {
        // within 2 seconds
        return "right now";
    }

    if (diff < minute) {
        return Math.floor(diff / second) + " seconds ago";
    }

    if (diff < minute * 2) {
        return "about 1 minute ago";
    }

    if (diff < hour) {
        return Math.floor(diff / minute) + " minutes ago";
    }

    if (diff < hour * 2) {
        return "about 1 hour ago";
    }

    if (diff < day) {             
        return Math.floor(diff / hour) + " hours ago";         
    }           
    
    if (diff > day && diff < day * 2) {
        return "yesterday";
    }
    
    if (diff > week && diff < day * 365) {
        return Math.floor(diff / week) + " weeks ago";
    }

    if (diff < day * 365) {
        return Math.floor(diff / day) + " days ago";
    }

    else {
        return "over a year ago";
    }
}


$(document).on('click', '.navbar-item', function() {
    $('navbar-item').removeClass('is-active is-tab');
});

