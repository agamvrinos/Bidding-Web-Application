$( document ).ready(function(){


    regdialog = $( "#regdialog-form" ).dialog({
        autoOpen: false,
        height: 600,
        width: 600,
        modal: true,
        draggable: false,
        resizable: false,
        buttons: {
            Ακυρωση: function() {
                regdialog.dialog( "close" );
            }
        }
    });
    $( "#register_button" ).on( "click", function() {
        regdialog.dialog( "open" );
    });

    logdialog = $( "#logdialog-form" ).dialog({
        autoOpen: false,
        height: 370,
        width: 300,
        modal: true,
        draggable: false,
        resizable: false,
        buttons: {
            Ακυρωση: function() {
                logdialog.dialog( "close" );
            }
        }
    });
    $( "#login_button" ).on( "click", function() {
        logdialog.dialog( "open" );
    });
    // $( "#lib_search" ).keyup(function() {
    //     query = $("#lib_search" ).val();
    //     $.ajax({
    //             method: "POST",
    //             url: "lib_search.php",
    //             data: { key: query }
    //         })
    //         .done(function( msg ) {
    //             $("#lib_results" ).html(msg);
    //         });
    // });


    // $( ".navbar-link, .asyc" ).on( "click", function() {
    //
    //     event.preventDefault();
    //     var pagename = $(this).attr("name");
    //
    //     $('#main').fadeOut('fast', function(){
    //
    //         $('#main').load("pages/"+pagename+'.php', function(){
    //             history.pushState('data', '', 'http://localhost:8000/?page='+pagename);
    //             var pagetitle = "Αρχική Σελίδα";
    //             if(pagename=="home") pagetitle = "Αρχική Σελίδα";
    //             if(pagename=="libraries") pagetitle = "Βιβλιοθήκες";
    //             if(pagename=="news") pagetitle = "Ανακοινώσεις";
    //             if(pagename=="help") pagetitle = "Βοήθεια";
    //             if(pagename=="contact") pagetitle = "Επικοινωνία";
    //             document.title = pagetitle;
    //             $('#main').fadeIn('fast');
    //         });
    //
    //     });
    // });


});
