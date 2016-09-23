<%@ page import="dao.ItemDAO" %>
<%@ page import="entities.Item" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="dao.DAOUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
    <title>Αποτελέσματα Αναζήτησης Δημοπρασιών για <%=request.getParameter("value")%></title>

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



        <% ItemDAO dao = new ItemDAO(true);
            String page_str = request.getParameter("page");
            Integer pageid;
            if (page_str != null)
                pageid = DAOUtil.IntConvert(page_str);
            else
                pageid = 1;
            List<Item> auctions = dao.getSearchResults(request.getParameter("value"), request.getParameter("type"), pageid);%>

            <br><h3>Βρέθηκαν <%=dao.getNumOfResults()%> αποτελέσματα για: "<%=request.getParameter("value")%>"</h3>

            <%--PAGING--%>
            <div class="alignright">
                <form class="form-horizontal" action="searchResults.jsp" method="get" >
                    <% if (pageid > 1){%>
                    <a class="btn btn-default" style="display:inline-block" href="searchResults.jsp?value=<%=request.getParameter("value")%>&type=<%=request.getParameter("type")%>&page=<%=pageid-1%>">
                        <strong><</strong>
                    </a>
                    <%}%>
                    <input type="hidden" name="value" value="<%=request.getParameter("value")%>" />
                    <input type="hidden" name="type" value="<%=request.getParameter("type")%>" />
                    <input type="text" style="width: 40px; height: 33px; display:inline-block" name="page" value="<%=pageid%>" />
                    από <%=dao.getNumOfPages()%>
                    <% if (pageid < dao.getNumOfPages()){%>
                    <a class="btn btn-default" style="display:inline-block" href="searchResults.jsp?value=<%=request.getParameter("value")%>&type=<%=request.getParameter("type")%>&page=<%=pageid+1%>">
                        <strong>></strong>
                    </a>
                    <%}%>
                </form>
            </div>
            <%--END OF PAGING--%>

            <br><br><hr style="border-top: 1px solid #1abc9c">

            <% for (int i = 0; i < auctions.size(); i++) {%>

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
                            <a href="singleproduct.jsp?id=<%=auctions.get(i).getId()%>" style="color:#333333;"><%=auctions.get(i).getName()%></a>
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
                            <td>$<%=auctions.get(i).getCurrently()%></td>
                        </tr>
                        <tr>
                            <th>Τιμή Αγοράς</th>
                            <%if(auctions.get(i).getBuy_price() == null){%>
                            <td>Δεν έχει οριστεί</td>
                            <%}else{%>
                            <td>$<%=auctions.get(i).getBuy_price()%></td>
                            <%}%>
                        </tr>
                        <tr>
                            <th>Αρχική Προσφορά</th>
                            <td>$<%=auctions.get(i).getFirst_bid()%></td>
                        </tr>
                        <tr>
                            <th>Ημερομηνία/Ώρα Λήξης</th>
                            <td><%=auctions.get(i).getEnds()%></td>
                        </tr>
                        <%--Countdown timer if auction is published--%>
                        <%
                            Date end_time = auctions.get(i).getEnds();
                            SimpleDateFormat endformat = new SimpleDateFormat("MM/dd/yyyy hh:mm");
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

            <%--PAGING--%>
                <div class="alignright">
                    <form class="form-horizontal" action="searchResults.jsp" method="get" >
                        <% if (pageid > 1){%>
                            <a class="btn btn-default" style="display:inline-block" href="searchResults.jsp?value=<%=request.getParameter("value")%>&type=<%=request.getParameter("type")%>&page=<%=pageid-1%>">
                                <strong><</strong>
                            </a>
                        <%}%>
                        <input type="hidden" name="value" value="<%=request.getParameter("value")%>" />
                        <input type="hidden" name="type" value="<%=request.getParameter("type")%>" />
                        <input type="text" style="width: 40px; height: 33px; display:inline-block" name="page" value="<%=pageid%>" />
                        από <%=dao.getNumOfPages()%>
                        <% if (pageid < dao.getNumOfPages()){%>
                            <a class="btn btn-default" style="display:inline-block" href="searchResults.jsp?value=<%=request.getParameter("value")%>&type=<%=request.getParameter("type")%>&page=<%=pageid+1%>">
                               <strong>></strong>
                            </a>
                        <%}%>
                    </form>
                </div>
            <%--END OF PAGING--%>


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
