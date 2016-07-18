<%--
  Created by IntelliJ IDEA.
  User: stef
  Date: 18/7/2016
  Time: 1:37 μμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>

    <!-- Google Fonts -->
    <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,200,300,700,600' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Roboto+Condensed:400,700,300' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Raleway:400,100' rel='stylesheet' type='text/css'>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

    <!-- Font Awesome -->
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">

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
        <div class="row">
            <form class="form-horizontal" action="Login" method="post">

                </br><h3>Σύνδεση Χρήστη</h3>
                <fieldset>
                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="username">Όνομα Χρήστη</label>
                        <div class="col-md-4">
                            <input id="username" name="username" placeholder="Όνομα Χρήστη" class="form-control input-md" required="" type="text">

                        </div>
                    </div>

                    <!-- Password input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="password">Κωδικός</label>
                        <div class="col-md-4">
                            <input id="password" name="password" placeholder="Κωδικός" class="form-control input-md" required="" type="password">

                        </div>
                    </div>

                    <!-- Button -->
                    <div class="form-group">
                        <div class="col-md-7"></div>
                        <div class="col-md-4">
                            <button id="login" name="login" class="btn btn-primary">Σύνδεση</button>
                        </div>
                    </div>

                </fieldset>
            </form>
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
