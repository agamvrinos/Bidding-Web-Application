<%@ page import="entities.AppEntities.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%  User sessionUser = (User) request.getSession().getAttribute("user");
    if(sessionUser==null || sessionUser.getRole()!=0) {
        response.sendRedirect("error_page.jsp");
    }
%>

<html>
<head>
    <title>Load XML</title>

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
</head>
<body>
<jsp:include page="header.jsp" />

<div class="maincontent-area">
    <div class="zigzag-bottom"></div>
    <div class="container">
        <div class="row">
            <br>
            <br>

            <div class="col-lg-6 col-md-6 col-sm-6">
                <%
                    String error = (String)request.getAttribute("error");
                    String message = (String)request.getAttribute("message");

                // Wrong xml file number
                if (error != null) {%>
                    <div class="alert alert-danger">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <strong>Προσοχή! </strong><%=error%>
                    </div>
                <%}
                // Import success
                if (message != null) {%>
                <div class="alert alert-success">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <strong>Επιτυχία! </strong><%=message%>
                </div>
                <%}%>

                <form action="LoadXml" method="post">
                    <label for="xml_amount">Δώστε το απόλυτο τοπικό μονοπάτι του αρχείου XML που θέλετε να φορτωθεί:</label>
                    <div class="input-group">
                        <input id="xml_amount" name="xml_path" type="text" class="form-control" placeholder="Π.χ. /home/user1/xml/item-1.xml">
                        <span class="input-group-btn">
                            <button class="btn btn-primary">Φόρτωση!</button>
                        </span>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />

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
</body>
</html>
