<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entities.User" %>

<%--Make sure user is logged in--%>
<%--<%--%>
    <%--User sessionUser;--%>
    <%--if (request.getSession().getAttribute("user")==null) {--%>
        <%--request.setAttribute("nologgedin","no");--%>
        <%--RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");--%>
        <%--dispatcher.forward(request, response);--%>
        <%--return;--%>
    <%--}--%>
    <%--else {--%>
        <%--sessionUser = (User) request.getSession().getAttribute("user");--%>
    <%--}--%>
<%--%>--%>
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
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="css/responsive.css">
    <link rel="stylesheet" href="css/jquery-ui.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
<jsp:include page="header.jsp" />

<div class="maincontent-area">
    <div class="zigzag-bottom"></div>
    <div id="msg-info">
        <div id="msg-header">
            <div id="msg-title">
                [K31: Compilers] Φωτογραφίες <br>
            </div>
            <i>Yannis Smaragdakis    15/05/2016 12:23</i><br><br>
        </div>
        <div id="msg-content">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse quis felis a lectus efficitur ultrices id sit amet turpis. Nam eu ante sagittis, rutrum nisi nec, mattis est. Praesent vestibulum nunc ac dui ultrices, eu cursus risus laoreet. Praesent sagittis lorem ac risus semper finibus. Nunc consectetur eros vitae turpis iaculis, facilisis sodales ante cursus. Morbi nunc lectus, varius quis urna et, faucibus condimentum est. Nulla lectus ante, laoreet vitae euismod id, varius rutrum velit. Duis quis nulla eu lorem aliquam hendrerit pharetra a mauris. Morbi eu ipsum eget massa tristique condimentum in et diam.

            Sed volutpat pharetra ornare. Nullam venenatis odio consectetur, viverra quam et, porttitor sapien. Vivamus fermentum ex id faucibus tristique. Maecenas commodo porttitor orci ut pretium. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean vestibulum maximus pulvinar. Donec tincidunt, turpis mollis facilisis egestas, eros tellus accumsan elit, at pulvinar turpis leo at nibh. Aliquam iaculis augue ac augue dapibus dictum.

            Curabitur nec venenatis nisi. Mauris nibh lacus, cursus sed consectetur sed, lobortis sed lorem. Integer in porttitor nunc. Sed varius et nisi vel porta. Mauris felis ante, ornare nec aliquet a, hendrerit id nisl. Mauris consequat mauris rutrum, suscipit erat ut, sodales quam. Praesent bibendum condimentum mauris, at ultrices enim sollicitudin in. Cras semper, nisl non tempus semper, tellus leo pellentesque lorem, ut lobortis augue neque nec risus. Vivamus vel est imperdiet, facilisis tortor eget, blandit lorem. Nulla nulla purus, malesuada sit amet magna ut, tempus facilisis ex. Aenean ullamcorper nibh in quam ullamcorper, posuere gravida velit sagittis. In hac habitasse platea dictumst. Cras malesuada velit quis felis finibus pellentesque.

        </div>

        <br>
        <a href="#" class="btn btn-primary" role="button">Απάντηση</a>
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
</body>
</html>
