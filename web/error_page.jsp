<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Σφάλμα!</title>

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

    <%--TODO: Na to modaroume sto telos tis erg (dynamic)--%>
    <br><h3 style="text-align: center;">Σφάλμα 404! Drakos Not FOUND!</h3><br>

    <img src="img/dragon.jpg" class="img-responsive center-block"><br><br>

    <div style="text-align: center; text-decoration: underline; font-size: 20px;">
        <a href="index.jsp">Επιστροφή στην Αρχική</a><br><br><br>
    </div>

    <jsp:include page="footer.jsp" />

</body>
</html>
