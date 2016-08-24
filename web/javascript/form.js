
function usernameAvailability() {
    var userName = jQuery("#username").val();

    jQuery.ajax({
        url : "UsernameAvailability",
        method: "GET",
        type : "JSON",
        data : "&username="+userName,// query parameters 1st
        success : function(response){
            $('#ajaxGetUserServletResponse').text(response);

        }
    });
}

function disableEndedAuction(auction_id) {

    jQuery.ajax({
        url : "DisableEndedAuction",
        method: "GET",
        type : "JSON",
        data : "&id="+auction_id ,   // query parameters 1st
        success : function(){
            location.reload();
        }
    });
}

$(document).ready(function () {
    $('.sidebar-nav .navbar-nav li a').click(function(e) {
        $('.sidebar-nav .navbar-nav li').removeClass('active');

        var $parent = $(this).parent();
        if (!$parent.hasClass('active')) {
            $parent.addClass('active');
        }
        e.preventDefault();
    });
});