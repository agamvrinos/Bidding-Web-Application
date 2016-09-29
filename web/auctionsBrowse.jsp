<%@ page import="dao.ItemDAO" %>
<%@ page import="entities.Item" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String category = request.getParameter("category");

    // If url is typed without category parameter then send him to index
    if (category == null) {
        response.sendRedirect("index.jsp");
    }
    ItemDAO dao = new ItemDAO(true);

    // Get all active auctions
    List<Item> auctions = dao.getAuctionsByCategory(category);
%>

<script>


    function CountDownTimer(dt, class_name, auction_id)
    {
        var end = new Date(dt);

        var _second = 1000;
        var _minute = _second * 60;
        var _hour = _minute * 60;
        var _day = _hour * 24;
        var timer;

        function showRemaining() {
            var now = new Date();
            var distance = end - now;
            if (distance < 0) {

                clearInterval(timer);
                document.getElementsByClassName(class_name)[0].innerHTML = 'Η Δημοπρασία έληξε!';

                return;
            }
            var days = Math.floor(distance / _day);
            var hours = Math.floor((distance % _day) / _hour);
            var minutes = Math.floor((distance % _hour) / _minute);
            var seconds = Math.floor((distance % _minute) / _second);


            if (String(hours).length < 2){
                hours = 0 + String(hours);
            }
            if (String(minutes).length < 2){
                minutes = 0 + String(minutes);
            }
            if (String(seconds).length < 2){
                seconds = 0 + String(seconds);
            }

            var datestr = days + ' days ' +
                    hours + ' hrs ' +
                    minutes + ' mins ' +
                    seconds + ' secs';

            document.getElementsByClassName(class_name)[0].innerHTML = datestr;
        }

        timer = setInterval(showRemaining, 1000);
    }
</script>

<html>
<head>
    <title>Δημοπρασίες</title>

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
            </br>
            <% if (request.getAttribute("bought") == "yes") { %>
                <div class="alert alert-danger">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <strong>Λυπούμαστε! </strong>Το συγκεκριμένο προϊόν αγοράστηκε!
                </div>
            <% }%>

            <% if (request.getAttribute("ended") == "yes") { %>
                <div class="alert alert-danger">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <strong>Λυπούμαστε! </strong>Η συγκεκριμένη δημοπρασία έχει λήξει!
                </div>
            <% }%>

            <% if (request.getAttribute("success") == "yes") { %>
            <div class="alert alert-success">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                <strong>Συγχαρητήρια! </strong>Η αγορά σας ολοκληρώθηκε!
            </div>
            <% }%>

            </br>

            <h4>Κατηγορία: <%=category%> | <%=auctions.size()%> αποτελέσματα βρέθηκαν</h4>

            <hr style="border-top: 1px solid #1abc9c">

            <%
                for (int i = 0; i < auctions.size(); i++) {%>

                    <div class="row" >

                        <%--Image Section--%>
                        <div class="col-sm-3 col-xs-3 col-md-3">
                            <%String image = auctions.get(i).getImage();

                            // If there is an image uploaded for this item
                            if (image != null) {%>
                                <img class="img-responsive center-block" src="files/<%=image%>" style="height: 200px; width: 200px">
                            <%}
                            // Else use the default image
                            else {%>
                                <img class="img-responsive center-block" src="img/blank.png" style="height: 200px; width: 200px">
                            <%}%>
                        </div>

                        <%--Info Section--%>
                        <div class="col-sm-9 col-xs-9 col-md-9">

                            <table style="table-layout:fixed; word-wrap: break-word;" id="userlist-table" class="table table-hover table-striped table-condensed ">
                                <tbody>
                                <div id="title" style="font-size: 25px; text-decoration: underline; font-weight: 600;">
                                    <a href="singleproduct.jsp?id=<%=auctions.get(i).getId()%>&category=<%=category%>" style="color:#333333;"><%=auctions.get(i).getName()%></a>
                                </div>
                                <tr>
                                    <th>Κατηγορία/ες</th>
                                    <%  List<String> categories = auctions.get(i).getCategories();
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
                                    <td><%=auctions.get(i).getCurrently()%></td>
                                </tr>
                                <tr>
                                    <th>Τιμή Αγοράς</th>
                                    <%if(auctions.get(i).getBuy_price() == null){%>
                                    <td>Δεν έχει οριστεί</td>
                                    <%}else{%>
                                    <td><%=auctions.get(i).getBuy_price()%></td>
                                    <%}%>
                                </tr>
                                <tr>
                                    <th>Αρχική Προσφορά</th>
                                    <td><%=auctions.get(i).getFirst_bid()%></td>
                                </tr>
                                <tr>
                                    <th>Ημερομηνία/Ώρα Λήξης</th>
                                    <td><%=auctions.get(i).getEnds()%></td>
                                </tr>
                                <%--Countdown timer if auction is published--%>
                                <%
                                    Date end_time = auctions.get(i).getEnds();
                                    SimpleDateFormat endformat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
                                    String end = endformat.format(end_time);
                                %>
                                <tr>
                                    <th>Χρόνος που Απομένει</th>
                                    <td>
                                        <div class="countdown-<%=auctions.get(i).getId()%>"></div>
                                        <script>
                                            CountDownTimer('<%=end%>', 'countdown-<%=auctions.get(i).getId()%>', '<%=auctions.get(i).getId()%>');
                                        </script>
                                    </td>
                                </tr>
                                <tr>
                                    <th>Πωλητής</th>
                                    <td><%=auctions.get(i).getSeller()%></td>

                                </tr>
                                </tbody>
                            </table>
                        </div>

                    </div>

                    <hr style="border-top: 1px solid #1abc9c">
                <%}%>

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
