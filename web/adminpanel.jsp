<%@ page import="dao.UserDAO" %>
<%@ page import="entities.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: stef
  Date: 20/7/2016
  Time: 1:12 μμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Πίνακας Διαχειριστή</title>

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
        </br><h3>Πίνακας Διαχείρισης Χρηστών</h3></br>
        <%--<div id="userlist" class="well col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1 col-xs-12">--%>
        <div id="userlist" class="well col-md-10 col-md-offset-1 ">
            <table id="userlist-table" class="table table-hover table-striped table-condensed">
                <thead>
                <tr>
                    <th>Ονοματεπώνυμο</th>
                    <th>Όνομα Χρήστη</th>
                    <th>Email</th>
                    <th>Ρόλος</th>
                    <th><div style="text-align: center">Επικυρωμένος</div></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <%  List<User> userlist = new UserDAO(true).getUserList();
                    for(int i=0; i < userlist.size(); i++){
                %>
                <tr>
                    <td><%=userlist.get(i).getFullname()%></td>
                    <td><%=userlist.get(i).getUsername()%></td>
                    <td><%=userlist.get(i).getEmail()%></td>
                    <% int role = userlist.get(i).getRole();
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
                    <% if (userlist.get(i).getValidated()==0){ %>
                    <td><div style="color: red;text-align: center">Όχι</div></td>
                    <% }
                        else{ %>
                    <td><div style="color: forestgreen;text-align: center">Ναι</div></td>
                    <% } %>
                    <td><a class="btn btn-primary" href="#" role="button">Προφίλ</a></td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />

</body>
</html>
