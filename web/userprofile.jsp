<%@ page import="entities.AppEntities.User" %>
<%@ page import="dao.UserDAO" %>
<%@ page import="dao.DAOUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%  String uid = request.getParameter("id");
    //in case id parameter is missing or invalid
    if(uid == null || DAOUtil.IntConvert(uid) == -1) {
        response.sendRedirect("error_page.jsp");
    }

    User user = new UserDAO(true).getUserbyID(Integer.valueOf(uid));
    //if user doesnt exist
    if(user==null) {
        response.sendRedirect("error_page.jsp");
        return;
    }

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
    <link rel="stylesheet" href="css/style.css">
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
                <div class="panel-heading" style="background-color:rgb(107, 154, 167); color:black; border-bottom: black;">
                    <h3 class="panel-title"><%=user.getFullname()%></h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-3 col-lg-3 " align="center"> <img alt="User Pic" src="img/default-user.png" class="img-circle img-responsive"> </div>

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
                                        else if (role == 2){
                                            roletext = "Προσφέρον";
                                        }
                                        else {
                                            roletext = "Πωλητής/ Προσφέρον";
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
                            </span>
                        </div>
                    </div>
                </div>
                <div class="panel-footer">
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
