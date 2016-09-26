<%@ page import="dao.ItemDAO" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Κατηγορίες Δημοπρασιών</title>

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

            </br><h3>Κατηγορίες Δημοπρασιών</h3></br>
            <%
                ItemDAO dao = new ItemDAO(true);
                List<String> categories = dao.getCategories();

                for (int i = 0; i < categories.size(); i ++)
                {
            %>
                    <div class="row" style="padding-bottom: 5px;">
                        <div class="btn-group btn-group-justified" role="group" aria-label="...">
                            <div class="btn-group" role="group">
                                <%if (i % 3 == 0) {%>
                                    <a href="auctionsBrowse.jsp?category=<%=categories.get(i)%>" class="btn btn-default" role="button" style="background-color: rgb(140, 182, 194); border-color: rgb(52, 82, 90);"><%=categories.get(i)%></a>
                                <%}%>
                            </div>
                            <div class="btn-group" role="group">
                                <%if (i + 1 < categories.size() &&  (i+1) % 3 == 1) {%>
                                    <a href="auctionsBrowse.jsp?category=<%=categories.get(i + 1)%>" class="btn btn-default" role="button" style="background-color: rgb(140, 182, 194); border-color: rgb(52, 82, 90);"><%=categories.get(i + 1)%> </a>
                                <%}%>
                            </div>
                            <div class="btn-group" role="group">
                                <%if (i + 2 < categories.size() &&  (i+2) % 3 == 2) {%>
                                    <a href="auctionsBrowse.jsp?category=<%=categories.get(i + 2)%>" class="btn btn-default" role="button" style="background-color: rgb(140, 182, 194); border-color: rgb(52, 82, 90);"><%=categories.get(i + 2)%></a>
                                <%}%>
                            </div>
                        </div>

                    </div>
            <%
                }
            %>
        </div>
    </div>

    <jsp:include page="footer.jsp" />

</body>

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
