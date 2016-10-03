<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entities.AppEntities.User" %>
<%@ page import="entities.AppEntities.Message" %>
<%@ page import="dao.MessageDAO" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="dao.UserDAO" %>

<%
    // Make sure user is logged in
    if (request.getSession().getAttribute("user")==null) {
        request.setAttribute("nologgedin","no");
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
        return;
    }

    // Get the message id passed and type of message (rec or sent)
    Integer message_id = Integer.valueOf(request.getParameter("id"));
    String msg_type = request.getParameter("type");

    // Get full message using the id
    MessageDAO mdao = new MessageDAO(true);
    Message message = mdao.getMessageById(message_id);

    // Check1: No message with this id
    // Check2: Illegal type
    if (message == null || (!msg_type.equals("send") && !msg_type.equals("rec"))){
        response.sendRedirect("error_page.jsp");
        return;
    }

    // Format Date
    Date end_t = message.getDate();
    SimpleDateFormat end_format = new SimpleDateFormat("dd/MM/yyyy hh:mm");
    String date = end_format.format(end_t);

    // Who sent me the message???? or to whom did I sent it????
    User sender = new UserDAO(true).getUserbyID(message.getSender_id());
    String sender_username = sender.getUsername();
    User receiver = new UserDAO(true).getUserbyID(message.getReceiver_id());
    String receiver_username = receiver.getUsername();

    if (msg_type.equals("rec"))   // Mark message as read
        mdao.updateIsRead(message_id);
%>
<html>
<head>
    <title>Τα Μηνυματά Μου</title>

    <!-- Google Fonts -->
    <link href='https://fonts.googleapis.com/css?family=Titillium+Web:400,200,300,700,600' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Roboto+Condensed:400,700,300' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Raleway:400,100' rel='stylesheet' type='text/css'>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="css/bootstrap.min.css">

    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="css/owl.carousel.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/responsive.css">
    <link rel="stylesheet" href="css/jquery-ui.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">

    <style>
        .rating {
            padding-top: 22px;
        }
        .rating-desc {
            color: #7E8631;
            font-weight: 500;
            margin-bottom: 7px;
        }

        .edit {
            color: rgb(47, 47, 47);
        }
    </style>
</head>
<body>
    <jsp:include page="header.jsp" />

    <div class="maincontent-area">
        <div class="zigzag-bottom"></div>
        <div class="container">
            <div id="msg-info">
                <div id="msg-header">
                    <div id="msg-title">
                        <%=message.getTitle()%><br>
                    </div>
                    <% if (msg_type.equals("rec")){%>
                        <i><span>Αποστολέας:</span> <%=sender_username%> <span>Ημερομηνία/Ώρα:</span> <%=date%></i><br><br>
                    <% } else if (msg_type.equals("send")){%>
                        <i><span>Εστάλη στον:</span> <%=receiver_username%> <span>Ημερομηνία/Ώρα:</span> <%=date%></i><br><br>
                    <% }%>
                </div>
                <div id="msg-content">
                    <%=message.getMessage()%>
                </div>

                <br>
                <a href="message_reply.jsp?id=<%=message_id%>&type=<%=msg_type%>" class="btn btn-primary" role="button">Απάντηση</a>

                <%
                // if auto_message or already rated
                if (message.getIs_auto() == 1){%>
                    <div class="rating">
                        <div class="row">
                            <div class="col-md-3">
                                <div class="rating-desc">
                                    Αξιολόγηση Πωλητή:
                                </div>

                                <div class="btn-group btn-group-justified">
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-success edit rate" value="1">
                                            <i class="fa fa-thumbs-up" aria-hidden="true"></i> Θετικά
                                        </button>
                                    </div>
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-danger edit rate" value="-1">
                                            <i class="fa fa-thumbs-down" aria-hidden="true"></i> Αρνητικά
                                        </button>
                                    </div>
                                </div>
                                <div id="ajaxServletResponse" style="color: cadetblue"></div>
                            </div>
                        </div>
                    </div>
                <%}%>
            </div>
        </div>
    </div>

    <jsp:include page="footer.jsp" />

<script src="javascript/jquery-1.10.2.js"></script>
<script src="javascript/jquery-ui.js"></script>
<script src="javascript/bootstrap.min.js"></script>


<!-- jQuery sticky menu -->
<script src="javascript/owl.carousel.min.js"></script>
<script src="javascript/jquery.sticky.js"></script>

<!-- jQuery easing -->
<script src="javascript/jquery.easing.1.3.min.js"></script>

<!-- Main Script -->
<script src="javascript/main.js"></script>

<script>
    $(document).ready(function(){
        $('.rate').click(function(){

            // Get if rating was positive or negative
            var rate_val = $(this).attr("value");

            // Disable the other button than the one clicked
            $('.rate').not($(this)).prop('disabled', true);
            $(this).prop('disabled', true);
            $(this).css("opacity", "1");


            $.ajax({
                url : "DisableMessageRating",
                method: "POST",
                type : "JSON",
                data : {seller_uname: '<%=sender.getUsername()%>',
                        msg_id: <%=message_id%>,
                        rate_val: rate_val},
                success : function(){
                    $('#ajaxServletResponse').text("Η αξιολόγηση καταχωρήθηκε");
                }

            });
        });
    });
</script>

</body>
</html>
