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