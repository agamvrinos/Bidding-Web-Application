<%@ page import="entities.AppEntities.Item" %>
<%@ page import="dao.ItemDAO" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="dao.BidDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="entities.AppEntities.Bid" %>
<%@ page import="dao.UserDAO" %>
<%@ page import="entities.AppEntities.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%
        Integer auction_id = Integer.valueOf(request.getParameter("id"));
        User user = (User) request.getSession().getAttribute("user");

        ItemDAO idao = new ItemDAO(true);
        BidDAO bdao = new BidDAO(true);
        UserDAO udao = new UserDAO(true);

        Item item = idao.getItemByID(auction_id);

        //if not valid, error page
        if(item == null) {
            response.sendRedirect("error_page.jsp");
            return;
        }

        List<Bid> bids = bdao.getItemBids(auction_id);
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
    <title><%=item.getName()%> - eBidders</title>

    <!-- Google Fonts -->
    <link href='https://fonts.googleapis.com/css?family=Titillium+Web:400,200,300,700,600' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Roboto+Condensed:400,700,300' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Raleway:400,100' rel='stylesheet' type='text/css'>

    <!-- Bootstrap -->
    <%--<link rel="stylesheet" href="css/bootstrap.min.css">--%>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css">

    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="css/owl.carousel.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/responsive.css">
    <link rel="stylesheet" href="css/jquery-ui.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">

    <style>
        #map {
            width: 100%;
            height: 400px;
        }

        .btn-static {
            background-color: lightgrey;
            border: 1px solid grey;
            cursor: default;
        }
        .btn-static:active {
            -moz-box-shadow:    inset 0 0 0px white;
            -webkit-box-shadow: inset 0 0 0px white;
            box-shadow:         inset 0 0 0px white;
        }
    </style>

</head>
<body>

<jsp:include page="header.jsp" />

<div class="single-product-area">
    <%--<div class="zigzag-bottom"></div>--%>

    <div class="container">
        <div class="row">

            <div class="col-md-12">
                <% if (request.getAttribute("wrong_input") == "yes") { %>
                <div class="alert alert-danger">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <strong>Προσοχή! </strong>Το πεδίο προσφοράς πρέπει να είναι αριθμός!
                </div>
                <% }%>
                <div class="product-content-right">

                    <div class="row">
                        <div class="col-sm-4">
                            <div class="product-images">
                                <div class="product-main-img">
                                    <%String image = item.getImage();

                                    // If there is an image uploaded for this item
                                    if (image != null) {%>
                                        <img class="img-responsive center-block" src="files/<%=image%>">
                                    <%}
                                    // Else use the default image
                                    else {%>
                                        <img class="img-responsive center-block" src="img/blank.png">
                                    <%}%>
                                </div>

                                <div class="product-gallery">
                                    <img src="img/product-thumb-1.jpg" alt="">
                                    <img src="img/product-thumb-2.jpg" alt="">
                                    <img src="img/product-thumb-3.jpg" alt="">
                                </div>
                            </div>
                        </div>

                        <div class="col-sm-8">
                            <div class="product-inner">
                                <h2 class="product-name"><%=item.getName()%></h2>
                                <hr>

                                <div class="auction-info">
                                    <div class="row">
                                        <div class="col-md-8 gap">
                                            <span style="font-weight: bold;">Προσφορές: </span> <%=item.getTotal_offers()%>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-8 gap">
                                            <%
                                            Double buy_price = item.getBuy_price();
                                            if (buy_price != null){%>
                                                <span style="font-weight: bold;">Τιμή Άμεσης Αγοράς: </span> $<%=buy_price%>
                                            <%} else {%>
                                                <span style="font-weight: bold;">Τιμή Άμεσης Αγοράς: </span> Δεν έχει οριστεί
                                            <%}%>

                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-8 gap">
                                            <span style="font-weight: bold;">Τρέχουσα Τιμή: </span> $<%=item.getCurrently()%>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-8 gap">
                                            <span style="font-weight: bold;">Κατηγορίες: </span>
                                            <% for(int i=0; i<item.getCategories().size(); i++){%>
                                                <%=item.getCategories().get(i)%>
                                                <% if (i<item.getCategories().size()-1){%>,<%}%>
                                            <%}%>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-8 gap">
                                            <span style="font-weight: bold;">Χώρα: </span> <%=item.getCountry()%>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-8 gap">
                                            <span style="font-weight: bold;">Περιοχή: </span> <%=item.getLocation()%>
                                        </div>
                                    </div>

                                    <div class="row gap">
                                        <%
                                            Date end_t = item.getEnds();
                                            SimpleDateFormat end_format = new SimpleDateFormat("MM/dd/yyyy hh:mm");
                                            String ended = end_format.format(end_t);
                                        %>
                                        <div class="col-md-1">
                                            <span style="font-weight: bold;">Απομένει: </span>
                                        </div>

                                        <div class="col-md-9">
                                            <div class="countdown-<%=item.getId()%>" style="color:green; margin-left: 8px;"></div>
                                            <script>
                                                CountDownTimer('<%=ended%>', 'countdown-<%=item.getId()%>', '<%=item.getId()%>');
                                            </script>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-8">
                                            <span style="font-weight: bold;">Πωλητής: </span> <a href="userprofile.jsp?id=<%=udao.getUserbyName(item.getSeller()).getId()%>"> <%=item.getSeller()%></a>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-8">
                                            <h5><span style="background-color: #34525a;" class="label label-default">Βαθμολογία: <%=udao.getUserRating(item.getSeller())%> <i class="fa fa-thumbs-up" aria-hidden="true"></i></span></h5>                                        </div>
                                        </div>
                                    </div>

                                <br>
                                <br>

                                <%if (item.getState() == 1 && user != null && user.getRole() != 1){%>
                                <%--Betting Field and Button--%>
                                <div class="row">
                                    <div class="col-md-8">
                                        <form action="BetOffer" method="get">

                                            <input type="hidden" name="id" value="<%=auction_id%>">

                                            <div class="input-group">
                                                <span class="input-group-addon">$<%=item.getCurrently()%></span>
                                                <input id="bid" name="bid" type="text" class="form-control" value="0.0">
                                            <span class="input-group-btn">
                                                <button id="register" name="register" class="btn btn-primary" onclick="return confirm('Είστε σίγουροι για την Υποβολή Προσφοράς?');">Υποβολή</button>
                                            </span>
                                            </div>
                                        </form>
                                    </div>
                                </div>

                                <br>
                                <br>
                                <%}%>


                                <%--Buy Now Button--%>
                                <%if (buy_price != null && item.getState() == 1 && user != null && user.getRole() != 1){%>
                                    <div class="row">
                                        <div class="col-md-8">
                                                <a href="BuyAuction?id=<%=auction_id%>" class="btn btn-primary" onclick="return confirm('Είστε σίγουροι για την αγορά?');">Άμεση Αγορά </a>

                                        </div>
                                    </div>
                                <%}%>

                                <%--Export XML Button--%>
                                <%if (user != null && user.getRole() == 0){%>
                                <div class="row">
                                    <form action="ExportXML" method="POST" id="export-xml">
                                    <div class="col-md-8">
                                        <br>
                                        <button id="export" name="export" value="<%=request.getParameter("id")%>" class="btn btn-warning">Export XML</button>
                                    </div>
                                    </form>
                                </div>
                                <%}%>

                                <br>

                                <div role="tabpanel">
                                    <ul class="product-tab" role="tablist">
                                        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Περιγραφή</a></li>
                                        <%if (item.getLatitude() != null){%>
                                        <li role="presentation"><a href="#maps" id="gmaps" aria-controls="gmaps" role="tab" data-toggle="tab">Χάρτης</a></li>
                                        <%}%>
                                        <li role="presentation"><a href="#offers" id="#offers" aria-controls="offers" role="tab" data-toggle="tab">Προσφορές</a></li>
                                    </ul>
                                    <div class="tab-content">
                                        <div role="tabpanel" class="tab-pane fade in active" id="home">
                                            <p style="text-align: justify;"><%=item.getDesc()%></p>
                                        </div>
                                        <%if (item.getLatitude() != null){%>
                                        <div role="tabpanel" class="tab-pane fade" id="maps">
                                            <div id="map"></div>
                                        </div>
                                        <%}%>
                                        <div role="tabpanel" class="tab-pane fade" id="offers">

                                            <table id="offers-table" class="table table-striped hover display compact" cellspacing="0" width="100%">
                                                <thead>
                                                    <tr>
                                                        <th>Όνομα Χρήστη</th>
                                                        <th>Προσφορά</th>
                                                        <th>Ημερομηνία/Ώρα</th>
                                                        <th>Χώρα</th>
                                                    </tr>
                                                </thead>

                                                <tbody>
                                                    <%
                                                    for (Bid bid : bids){
                                                        Date bid_time = bid.getTime();
                                                        String b_time = end_format.format(bid_time);
                                                    %>
                                                    <tr>
                                                        <td><%=bid.getUsername()%></td>
                                                        <td>$<%=bid.getAmount()%></td>
                                                        <td><%=b_time%></td>
                                                        <td><%=bid.getLocation()%></td>
                                                    </tr>
                                                    <%}%>
                                                </tbody>
                                            </table>

                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>

                    <% // If user is logged in
                        if(user != null) {
                            List<Item> rec_items = idao.getRecItems(user.getUsername());
                            if(rec_items.size() != 0){%>
                            <div class="related-products-wrapper">
                                <h2 class="related-products-title">Προτεινομενα Προϊοντα</h2>
                                <div class="related-products-carousel">
                                    <%  for(int i = 0; i < rec_items.size(); i++){%>
                                        <div class="single-product">
                                            <div class="product-f-image">
                                                <%String rec_image = rec_items.get(i).getImage();

                                                    // If there is an image uploaded for this item
                                                if (rec_image != null) {%>
                                                <img class="img-responsive center-block" src="files/<%=rec_image%>">
                                                <%}
                                                // Else use the default image
                                                else {%>
                                                <img class="img-responsive center-block" src="img/blank.png">
                                                <%}%>
                                                <div class="product-hover">
                                                    <a href="singleproduct.jsp?id=<%=rec_items.get(i).getId()%>" class="view-details-link"><i class="fa fa-link"></i> προβολη</a>
                                                </div>
                                            </div>

                                            <h2><a href="singleproduct.jsp?id=<%=rec_items.get(i).getId()%>"><%=rec_items.get(i).getName()%></a></h2>

                                            <div class="product-carousel-price">
                                                <ins>$<%=rec_items.get(i).getCurrently()%></ins>
                                                <%if (rec_items.get(i).getBuy_price()!=null){%>
                                                $<%=rec_items.get(i).getBuy_price()%>
                                                <%}%>
                                            </div>
                                        </div>
                                    <%}%>
                                </div>
                            </div>
                            <%}%>
                        <%}%>
                </div>
            </div>
        </div>
    </div>
</div>



<jsp:include page="footer.jsp" />

</body>

<script src="javascript/jquery-1.10.2.js"></script>
<script src="javascript/jquery-ui.js"></script>
<script src="javascript/form.js"></script>
<%--<script src="javascript/bootstrap.min.js"></script>--%>


<!-- jQuery sticky menu -->
<script src="javascript/owl.carousel.min.js"></script>
<script src="javascript/jquery.sticky.js"></script>

<!-- jQuery easing -->
<script src="javascript/jquery.easing.1.3.min.js"></script>

<!-- Main Script -->
<script src="javascript/main.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.12/js/dataTables.bootstrap.min.js"></script>

<%-- GOOGLE MAPS --%>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBn22YCesLQ6cAqQavGhd1lmwCIVUCZalo&callback=initialize">
</script>

<script>
    $(document).ready(function(){
        $("#gmaps").on('shown.bs.tab', function() {
            var mapProp = {
                center:new google.maps.LatLng(<%=item.getLatitude()%>,<%=item.getLongitude()%>),
                zoom:7,
                mapTypeId:google.maps.MapTypeId.ROADMAP
            };
            var map=new google.maps.Map(document.getElementById("map"),mapProp);

            var marker = new google.maps.Marker({
                position: new google.maps.LatLng(<%=item.getLatitude()%>,<%=item.getLongitude()%>),
                map: map
            });
        });
    });
</script>

<script>
    $(document).ready(function() {
        $('#offers-table').DataTable();
    });
</script>

</html>
