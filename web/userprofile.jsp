<%@ page import="entities.User" %>
<%@ page import="dao.UserDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% User user = new UserDAO(true).getUserbyID(Integer.valueOf(request.getParameter("id")));
    User sessionUser = (User) request.getSession().getAttribute("user");
    int sessionUserRole = -1;
    if (sessionUser != null)
        sessionUserRole = sessionUser.getRole();
%>
<html>
<head>
    <title>Προφίλ Χρήστη - <%=user.getUsername()%></title>

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

<div class="container">
    <div class="row">
        </br><h3>Προφίλ Χρήστη: <%=user.getUsername()%></h3><br>
        <div class="col-md-5  toppad  pull-right col-md-offset-3 ">
        </div>
        <div>
            <div class="panel panel-info">
                <div class="panel-heading" style="background-color:#399280; color:black; border-bottom: black;">
                    <h3 class="panel-title"><%=user.getFullname()%></h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-3 col-lg-3 " align="center"> <img alt="User Pic" src="https://gurucul.com/wp-content/uploads/2015/01/default-user-icon-profile.png" class="img-circle img-responsive"> </div>

                        <!--<div class="col-xs-10 col-sm-10 hidden-md hidden-lg"> <br>
                          <dl>
                            <dt>DEPARTMENT:</dt>
                            <dd>Administrator</dd>
                            <dt>HIRE DATE</dt>
                            <dd>11/12/2013</dd>
                            <dt>DATE OF BIRTH</dt>
                               <dd>11/12/2013</dd>
                            <dt>GENDER</dt>
                            <dd>Male</dd>
                          </dl>
                        </div>-->
                        <span class=" col-md-9 col-lg-9 ">
                            <table class="table table-user-information">
                                <tbody>
                                <tr>
                                    <td>Όνομα Χρήστη:</td>
                                    <td><%=user.getUsername()%></td>
                                </tr>
                                <tr>
                                    <td>Ρόλος:</td>
                                    <% int role = user.getRole();
                                        String roletext;
                                        if (role==0) {
                                            roletext = "Διαχειριστής";
                                        }
                                        else if (role == 1) {
                                            roletext = "Πωλητής";
                                        }
                                        else {
                                            roletext = "Προσφέρον";
                                        }%>
                                    <td><%=roletext%></td>
                                </tr>
                                <tr>
                                    <td>ΑΦΜ:</td>
                                    <td><%=user.getAfm()%></td>
                                </tr>
                                <tr>
                                    <td>Χώρα:</td>
                                    <td><%=user.getCountry()%></td>
                                </tr>
                                <tr>
                                    <td>Πόλη:</td>
                                    <td><%=user.getCity()%></td>
                                </tr>
                                <tr>
                                    <td>Διεύθυνση:</td>
                                    <td><%=user.getAddress()%></td>
                                </tr>
                                <tr>
                                    <td>Email:</td>
                                    <td><%=user.getEmail()%></td>
                                </tr>
                                <tr>
                                    <td>Τηλέφωνο:</td>
                                    <td><%=user.getPhone()%></td>
                                </tr>
                                <tr>
                                    <td>Επικυρωμένος:</td>
                                    <% if (user.getValidated()==0){ %>
                                    <td><span style="color: red">Όχι</span>
                                        <% if (sessionUserRole == 0){ %>
                                            <a href="ValidateUser?id=<%=user.getId()%>" class="btn btn-primary btn-sm"><i class="fa fa-pencil-square-o" aria-hidden="true"></i> Έγκριση Προφίλ</a>
                                        <% } %>
                                    </td>
                                    <% }
                                    else{ %>
                                    <td><span style="color: forestgreen">Ναι</span></td>
                                    <% } %>
                                </tr>

                                </tbody>
                            </table>
                            <div class="btn-toolbar">
                                <a href="#" class="btn btn-primary"><i class="fa fa-pencil-square-o" aria-hidden="true"></i> Επεξεργασία Προφίλ</a>
                                <a href="#" class="btn btn-primary"><i class="fa fa-envelope" aria-hidden="true"></i> Τα Μηνύματα μου </a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel-footer">
                    <%--<a data-original-title="Broadcast Message" data-toggle="tooltip" type="button" class="btn btn-sm btn-primary"><i class="glyphicon glyphicon-envelope"></i></a>--%>
                    <%--<span class="pull-right">--%>
                    <%--<a href="edit.html" data-original-title="Edit this user" data-toggle="tooltip" type="button" class="btn btn-sm btn-warning"><i class="glyphicon glyphicon-cog"></i></a>--%>
                    <%--<a data-original-title="Remove this user" data-toggle="tooltip" type="button" class="btn btn-sm btn-danger"><i class="glyphicon glyphicon-remove"></i></a>--%>
                    <%--</span>--%>
                </div>

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