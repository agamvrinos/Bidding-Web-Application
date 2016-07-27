<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Εγγραφή Νέου Χρήστη</title>

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
        <div class="row">

            <form class="form-horizontal" action="Register" method="post">

                </br><h3>Εγγραφή Χρήστη</h3>

                <%
                    Integer value = (Integer)request.getAttribute("register-error");
                    // Username already exists error
                    if (value != null && value == -2) {%>
                        <div class ="alert alert-danger"><span class="fa fa-times" aria-hidden="true"></span> Username already exists! Please try again with a different one.</div><%  }
                    // Other error
                    else if (value != null && value == -1){%>
                        <div class ="alert alert-danger"><span class="fa fa-times" aria-hidden="true" style="font-size:1.2em;"></span> An error occurred! Please try again.</div><%  }%>

                <fieldset>
                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="fullname">Ονοματεπώνυμο</label>
                        <div class="col-md-4">
                            <input id="fullname" name="fullname" placeholder="Ονοματεπώνυμο" class="form-control input-md" required="" type="text">

                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="username">Όνομα Χρήστη</label>
                        <div class="col-md-4">
                            <input id="username" name="username" placeholder="Όνομα Χρήστη" class="form-control input-md" onblur="usernameAvailability()" required="" type="text">
                            <div id="ajaxGetUserServletResponse" style="color: red"></div>
                        </div>
                    </div>

                    <!-- Password input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="password">Κωδικός</label>
                        <div class="col-md-4">
                            <input id="password" name="password" placeholder="Κωδικός" class="form-control input-md" required="" type="password">

                        </div>
                    </div>

                    <!-- Password input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="password_repeat">Επαλήθευση Κωδικού</label>
                        <div class="col-md-4">
                            <input id="password_repeat" name="password_repeat" placeholder="Επαλήθευση Κωδικού" class="form-control input-md" required="" type="password">

                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="email">Email</label>
                        <div class="col-md-4">
                            <input id="email" name="email" placeholder="Email" class="form-control input-md" required="" type="text">

                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="phone">Τηλέφωνο</label>
                        <div class="col-md-4">
                            <input id="phone" name="phone" placeholder="Τηλέφωνο" class="form-control input-md" required="" type="text">

                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="country">Χώρα Κατοικίας</label>
                        <div class="col-md-4">
                            <input id="country" name="country" placeholder="Χώρα Κατοικίας" class="form-control input-md" required="" type="text">
                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="city">Πόλη Κατοικίας</label>
                        <div class="col-md-4">
                            <input id="city" name="city" placeholder="Πόλη Κατοικίας" class="form-control input-md" required="" type="text">
                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="address">Διεύθυνση</label>
                        <div class="col-md-4">
                            <input id="address" name="address" placeholder="Διεύθυνση" class="form-control input-md" required="" type="text">
                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="afm">ΑΦΜ</label>
                        <div class="col-md-4">
                            <input id="afm" name="afm" placeholder="ΑΦΜ" class="form-control input-md" required="" type="text">
                        </div>
                    </div>

                    <!-- Select Basic -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="UserRole">Τύπος Χρήστη</label>
                        <div class="col-md-4">
                            <select id="UserRole" name="UserRole" class="form-control">
                                <option value="1">Πωλητής</option>
                                <option value="2">Προσφέρων</option>
                            </select>
                        </div>
                    </div>

                    <br>

                    <!-- Button -->
                    <div class="col-md-8">
                        <span class="pull-right">
                            <button id="register" name="register" class="btn btn-primary">Εγγραφή</button>
                        </span>
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

</body>
</html>
