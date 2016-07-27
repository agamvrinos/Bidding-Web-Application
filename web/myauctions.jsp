<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entities.User" %>

<%  User sessionUser = (User) request.getSession().getAttribute("user");
    if(sessionUser==null || sessionUser.getRole()!=0)
        response.sendRedirect("index.jsp");
%>

<html>
<head>
    <title>Οι Δημοπρασίες Μου</title>

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
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="css/responsive.css">
    <link rel="stylesheet" href="css/jquery-ui.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">

</head>
<body>
    <jsp:include page="header.jsp" />

    <div class="maincontent-area">
        <div class="zigzag-bottom"></div>
            <div class="container">
                <br>
                <div class="row">
                    <div class="col-sm-3 col-xs-3 col-md-3">
                        <img class="img-responsive center-block" src="http://www.freeiconspng.com/uploads/-png-keywords-books-icons-icons-icons-psd-files-size-5-54mb-zip-license-14.png" width="50%" height="50%">
                    </div>
                    <div class="col-sm-7 col-xs-7 col-md-7">
                        <div class="title"> <span style="font-weight: bold">Τίτλος: </span> Nokia 3110 </div>
                        <div class="category"><span style="font-weight: bold">Κατηγορία/ες:</span> Ηλεκτρονικά </div>
                        <div class="current"><span style="font-weight: bold">Τρέχουσα προσφορά:</span> 0 </div>
                        <div class="buyprice"><span style="font-weight: bold">Τιμή Αγοράς:</span> 500 </div>
                        <div class="firstbid"><span style="font-weight: bold">Αρχική Προσφορά:</span> 25 </div>
                        <div class="startdate"><span style="font-weight: bold">Ημερομηνία/Ώρα Έναρξης:</span> 2016-07-23 16:29:53 </div>
                        <div class="enddate"><span style="font-weight: bold">Ημερομηνία/Ώρα Λήξης:</span> 2016-07-28 16:29:53 </div>
                        <div class="state"><span style="font-weight: bold">Κατάσταση:</span> Δημοσιευμένη </div>
                    </div>

                    <div class="col-sm-1 col-xs-1 col-md-1">
                        <a href="newauction.jsp" class="btn btn-danger" role="button">Eνεργοποίηση</a>
                    </div>
                </div>

                <hr style="border-top: 1px solid #1abc9c">

                <div class="row">
                    <div class="col-sm-3 col-xs-3 col-md-3">
                        <img class="img-responsive center-block" src="http://www.freeiconspng.com/uploads/-png-keywords-books-icons-icons-icons-psd-files-size-5-54mb-zip-license-14.png" width="50%" height="50%">
                    </div>
                    <div class="col-sm-7 col-xs-7 col-md-7">
                        <div class="title"> <span style="font-weight: bold">Τίτλος: </span> Nokia 3110 </div>
                        <div class="category"><span style="font-weight: bold">Κατηγορία/ες:</span> Ηλεκτρονικά </div>
                        <div class="current"><span style="font-weight: bold">Τρέχουσα προσφορά:</span> 0 </div>
                        <div class="buyprice"><span style="font-weight: bold">Τιμή Αγοράς:</span> 500 </div>
                        <div class="firstbid"><span style="font-weight: bold">Αρχική Προσφορά:</span> 25 </div>
                        <div class="startdate"><span style="font-weight: bold">Ημερομηνία/Ώρα Έναρξης:</span> 2016-07-23 16:29:53 </div>
                        <div class="enddate"><span style="font-weight: bold">Ημερομηνία/Ώρα Λήξης:</span> 2016-07-28 16:29:53 </div>
                        <div class="state"><span style="font-weight: bold">Κατάσταση:</span> Μη Δημοσιευμένη </div>

                    </div>
                    <div class="col-sm-1 col-xs-1 col-md-1">
                        <a href="newauction.jsp" class="btn btn-success" role="button">Eνεργοποίηση</a>
                    </div>
                </div>

                <hr style="border-top: 1px solid #1abc9c">

                <div class="row">
                    <br>
                    <a href="newauction.jsp" class="btn btn-primary" role="button">Νέα Δημοπρασία</a>
                </div>
            </div>
    </div>


    <jsp:include page="footer.jsp" />


</body>

<!-- jQuery -->
<script src="javascript/jquery-1.10.2.js"></script>
<script src="javascript/jquery-ui.js"></script>
<script src="javascript/form.js"></script>
<script src="javascript/bootstrap.min.js"></script>


<!-- jQuery sticky menu -->
<script src="javascript/owl.carousel.min.js"></script>
<script src="javascript/jquery.sticky.js"></script>

<!-- jQuery easing -->
<script src="javascript/jquery.easing.1.3.min.js"></script>

<!-- Main Script -->
<script src="javascript/main.js"></script>

</html>
