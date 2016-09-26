<%@ page import="entities.Item" %>
<%@ page import="dao.ItemDAO" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--TODO: NA FTIAXTOUN OI ROLOI XRHSTWN KAI POIOS EXEI PROSVASI EDW--%>
    <%
        Integer auction_id = Integer.valueOf(request.getParameter("id"));
//        String chosen_category = request.getParameter("category");

        ItemDAO idao = new ItemDAO(true);
        Item item = idao.getItemByID(auction_id);

        if(item == null) { //if not valid, error page
            response.sendRedirect("index.jsp");
            return;
        }
%>

<html>
<head>
    <title><%=item.getName()%> - eBidders</title>

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
                                    <img src="img/product-2.jpg" alt="">
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
                                            if (buy_price != 0.0){%>
                                                <span style="font-weight: bold;">Τιμή Αγοράς: </span> <%=buy_price%>$
                                            <%} else {%>
                                                <span style="font-weight: bold;">Τιμή Αγοράς: </span> Δεν έχει οριστεί
                                            <%}%>

                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-8 gap">
                                            <span style="font-weight: bold;">Τρέχουσα Προσφορά: </span> <%=item.getCurrently()%>$
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-8 gap">
                                            <%
                                                Date end_t = item.getEnds();
                                                SimpleDateFormat end_format = new SimpleDateFormat("dd/MM/yyyy hh:mm");
                                                String ended = end_format.format(end_t);
                                            %>

                                            <span style="font-weight: bold;">Λήξη: </span> <%=ended%>
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
                                        <div class="col-md-8">
                                            <span style="font-weight: bold;">Πωλητής: </span> <a href="#"> <%=item.getSeller()%></a>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-8">
                                            <h5><span style="background-color: #34525a;" class="label label-default">Βαθμολογία: <%=idao.getUserRating(item.getSeller())%> <i class="fa fa-thumbs-up" aria-hidden="true"></i></span></h5>                                        </div>
                                        </div>
                                    </div>

                                <br>
                                <br>

                                <%--Betting Field and Button--%>
                                <div class="row">
                                    <div class="col-md-8">
                                        <form action="BetOffer" method="get">

                                            <input type="hidden" name="id" value="<%=auction_id%>">
                                            <%--<input type="hidden" name="category" value="<%=chosen_category%>">--%>

                                            <div class="input-group">
                                                <span class="input-group-addon"><%=item.getCurrently()%>$</span>
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

                                <%--Buy Now Button--%>
                                <div class="row">
                                    <div class="col-md-8">
                                        <%if (buy_price != null){%>
                                            <a href="BuyAuction?id=<%=auction_id%>" class="btn btn-primary" onclick="return confirm('Είστε σίγουροι για την αγορά?');">Άμεση Αγορά </a>
                                        <%}%>
                                    </div>
                                </div>

                                <%--Export XML Button--%>
                                <div class="row">
                                    <form action="ExportXML" method="POST" id="export-xml">
                                    <div class="col-md-8">
                                        <br>
                                        <button id="export" name="export" value="<%=request.getParameter("id")%>" class="btn btn-warning">Export XML</button>
                                    </div>
                                    </form>
                                </div>

                                <br>

                                <div role="tabpanel">
                                    <ul class="product-tab" role="tablist">
                                        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Περιγραφή</a></li>
                                        <li role="presentation"><a href="#maps" id="gmaps" aria-controls="gmaps" role="tab" data-toggle="tab">Χάρτης</a></li>
                                        <li role="presentation"><a href="#reviews" id="#reviews" aria-controls="reviews" role="tab" data-toggle="tab">Προσφορές</a></li>
                                    </ul>
                                    <div class="tab-content">
                                        <div role="tabpanel" class="tab-pane fade in active" id="home">
                                            <p style="text-align: justify;"><%=item.getDesc()%></p>
                                        </div>
                                        <div role="tabpanel" class="tab-pane fade" id="maps">
                                            <div id="map"></div>
                                        </div>
                                        <div role="tabpanel" class="tab-pane fade" id="reviews">

                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>


                    <div class="related-products-wrapper">
                        <h2 class="related-products-title">Related Products</h2>
                        <div class="related-products-carousel">
                            <div class="single-product">
                                <div class="product-f-image">
                                    <img src="img/product-1.jpg" alt="">
                                    <div class="product-hover">
                                        <a href="" class="add-to-cart-link"><i class="fa fa-shopping-cart"></i> Add to cart</a>
                                        <a href="" class="view-details-link"><i class="fa fa-link"></i> See details</a>
                                    </div>
                                </div>

                                <h2><a href="">Sony Smart TV - 2015</a></h2>

                                <div class="product-carousel-price">
                                    <ins>$700.00</ins> <del>$800.00</del>
                                </div>
                            </div>
                            <div class="single-product">
                                <div class="product-f-image">
                                    <img src="img/product-2.jpg" alt="">
                                    <div class="product-hover">
                                        <a href="" class="add-to-cart-link"><i class="fa fa-shopping-cart"></i> Add to cart</a>
                                        <a href="" class="view-details-link"><i class="fa fa-link"></i> See details</a>
                                    </div>
                                </div>

                                <h2><a href="">Apple new mac book 2015 March :P</a></h2>
                                <div class="product-carousel-price">
                                    <ins>$899.00</ins> <del>$999.00</del>
                                </div>
                            </div>
                            <div class="single-product">
                                <div class="product-f-image">
                                    <img src="img/product-3.jpg" alt="">
                                    <div class="product-hover">
                                        <a href="" class="add-to-cart-link"><i class="fa fa-shopping-cart"></i> Add to cart</a>
                                        <a href="" class="view-details-link"><i class="fa fa-link"></i> See details</a>
                                    </div>
                                </div>

                                <h2><a href="">Apple new i phone 6</a></h2>

                                <div class="product-carousel-price">
                                    <ins>$400.00</ins> <del>$425.00</del>
                                </div>
                            </div>
                            <div class="single-product">
                                <div class="product-f-image">
                                    <img src="img/product-4.jpg" alt="">
                                    <div class="product-hover">
                                        <a href="" class="add-to-cart-link"><i class="fa fa-shopping-cart"></i> Add to cart</a>
                                        <a href="" class="view-details-link"><i class="fa fa-link"></i> See details</a>
                                    </div>
                                </div>

                                <h2><a href="">Sony playstation microsoft</a></h2>

                                <div class="product-carousel-price">
                                    <ins>$200.00</ins> <del>$225.00</del>
                                </div>
                            </div>
                            <div class="single-product">
                                <div class="product-f-image">
                                    <img src="img/product-5.jpg" alt="">
                                    <div class="product-hover">
                                        <a href="" class="add-to-cart-link"><i class="fa fa-shopping-cart"></i> Add to cart</a>
                                        <a href="" class="view-details-link"><i class="fa fa-link"></i> See details</a>
                                    </div>
                                </div>

                                <h2><a href="">Sony Smart Air Condtion</a></h2>

                                <div class="product-carousel-price">
                                    <ins>$1200.00</ins> <del>$1355.00</del>
                                </div>
                            </div>
                            <div class="single-product">
                                <div class="product-f-image">
                                    <img src="img/product-6.jpg" alt="">
                                    <div class="product-hover">
                                        <a href="" class="add-to-cart-link"><i class="fa fa-shopping-cart"></i> Add to cart</a>
                                        <a href="" class="view-details-link"><i class="fa fa-link"></i> See details</a>
                                    </div>
                                </div>

                                <h2><a href="">Samsung gallaxy note 4</a></h2>

                                <div class="product-carousel-price">
                                    <ins>$400.00</ins>
                                </div>
                            </div>
                        </div>
                    </div>
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
        });
    });


</script>


</body>
</html>
