<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entities.User" %>
<%@ page import="entities.Item" %>
<%@ page import="dao.ItemDAO" %>
<%@ page import="java.util.*" %>

<%  User sessionUser = (User) request.getSession().getAttribute("user");
    if(sessionUser==null)
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

                </br><h3>Οι Δημοπρασίες Μου</h3></br>

                <% if (request.getAttribute("auction-creation-success") == "yes") { %>
                    <div class="alert alert-success">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <strong>Eπιτυχία!</strong>Η Δημοπρασία σας δημιουργήθηκε!
                    </div>
                <% } %>

                <hr style="border-top: 1px solid #1abc9c">

                <%
                if (sessionUser != null) {
                    String username = sessionUser.getUsername();
                    ItemDAO dao = new ItemDAO(true);

                    // Get the auctions that belong to this user
                    List<Item> userAuctions = dao.getUserAuctions(username);

                    for (int i = 0; i < userAuctions.size(); i++) {%>

                    <div class="row">

                        <%--Image Section--%>
                        <div class="col-sm-3 col-xs-3 col-md-3">
                            <%--TODO:Psisou na ftiaksoume to center --%>
                            <img class="img-responsive center-block" src="http://www.freeiconspng.com/uploads/-png-keywords-books-icons-icons-icons-psd-files-size-5-54mb-zip-license-14.png" width="70%">
                        </div>

                        <%--Info Section--%>
                        <div class="col-sm-7 col-xs-7 col-md-7">

                            <table style="table-layout:fixed; word-wrap: break-word;" id="userlist-table" class="table table-hover table-striped table-condensed ">
                                <tbody>
                                <tr>
                                    <th>Τίτλος</th>
                                    <td><%=userAuctions.get(i).getName()%></td>
                                </tr>
                                <tr>
                                    <th>Κατηγορία/ες</th>
                                    <%  List<String> categories = userAuctions.get(i).getCategories();
                                        String total = "";
                                        if (categories != null){
                                            for(int j = 0; j < categories.size(); j++){
                                                if (j == categories.size() - 1)
                                                    total += categories.get(j);
                                                else
                                                    total += categories.get(j) + ",";%>
                                            <%}
                                        }%>
                                    <td><%=total%></td>

                                </tr>
                                <tr>
                                    <th>Τρέχουσα προσφορά</th>
                                    <td><%=userAuctions.get(i).getCurrently()%></td>
                                </tr>
                                <tr>
                                    <th>Τιμή Αγοράς</th>
                                    <%if(userAuctions.get(i).getBuy_price() == null){%>
                                        <td>Δεν έχει οριστεί</td>
                                    <%}else{%>
                                        <td><%=userAuctions.get(i).getBuy_price()%></td>
                                    <%}%>
                                </tr>
                                <tr>
                                    <th>Αρχική Προσφορά</th>
                                    <td><%=userAuctions.get(i).getFirst_bid()%></td>
                                </tr>
                                <tr>
                                    <th>Ημερομηνία/Ώρα Λήξης</th>
                                    <td><%=userAuctions.get(i).getEnds()%></td>
                                </tr>
                                <tr>
                                    <th>Κατάσταση</th>

                                    <% if (userAuctions.get(i).getState() == -1){%>
                                        <td style="color: red">Ανενεργή</td>
                                    <%}else if (userAuctions.get(i).getState() == 0){%>
                                        <td style="color: orange">Μη Δημοσιευμένη</td>
                                    <%}else if (userAuctions.get(i).getState() == 1){%>
                                        <td style="color: green">Δημοσιευμένη</td>
                                    <%}%>

                                </tr>
                                </tbody>
                            </table>
                        </div>

                        <%--TODO: FIX Button functionality--%>
                        <%--Activate Button Section--%>
                        <div class="col-sm-1 col-xs-1 col-md-1">
                            <% if (userAuctions.get(i).getState() == -1){%>
                                <a href="#" class="btn btn-danger disabled" role="button">Eνεργοποίηση</a>
                            <%}else if (userAuctions.get(i).getState() == 0){%>
                                <a href="ActivateAuction?id=<%=userAuctions.get(i).getId()%>" class="btn btn-success" role="button">Eνεργοποίηση</a>
                            <%}else if (userAuctions.get(i).getState() == 1){%>
                                <a href="#" class="btn btn-danger disabled" role="button">Eνεργοποίηση</a>
                            <%}%>

                        </div>
                    </div>

                <hr style="border-top: 1px solid #1abc9c">

                <%}
                }
                %>

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
