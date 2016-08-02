<%@ page import="entities.Item" %>
<%@ page import="dao.ItemDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Item item = new ItemDAO(true).getItemByID(Integer.valueOf(request.getParameter("id")));

//TODO: NA FTIAXTOUN OI ROLOI XRHSTWN KAI POIOS EXEI PROSVASI EDW

    if(item==null || item.getState()!=1) { //if not valid, error page
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
    </style>

</head>
<body>

<jsp:include page="header.jsp" />

<div class="single-product-area">
    <%--<div class="zigzag-bottom"></div>--%>
    <div class="container">
        <div class="row">
            <div class="col-md-4">
                <div class="single-sidebar">
                    <h2 class="sidebar-title">Search Products</h2>
                    <form action="">
                        <input type="text" placeholder="Search products...">
                        <input type="submit" value="Search">
                    </form>
                </div>

                <div class="single-sidebar">
                    <h2 class="sidebar-title">Products</h2>
                    <div class="thubmnail-recent">
                        <img src="img/product-thumb-1.jpg" class="recent-thumb" alt="">
                        <h2><a href="">Sony Smart TV - 2015</a></h2>
                        <div class="product-sidebar-price">
                            <ins>$700.00</ins> <del>$800.00</del>
                        </div>
                    </div>
                    <div class="thubmnail-recent">
                        <img src="img/product-thumb-1.jpg" class="recent-thumb" alt="">
                        <h2><a href="">Sony Smart TV - 2015</a></h2>
                        <div class="product-sidebar-price">
                            <ins>$700.00</ins> <del>$800.00</del>
                        </div>
                    </div>
                    <div class="thubmnail-recent">
                        <img src="img/product-thumb-1.jpg" class="recent-thumb" alt="">
                        <h2><a href="">Sony Smart TV - 2015</a></h2>
                        <div class="product-sidebar-price">
                            <ins>$700.00</ins> <del>$800.00</del>
                        </div>
                    </div>
                    <div class="thubmnail-recent">
                        <img src="img/product-thumb-1.jpg" class="recent-thumb" alt="">
                        <h2><a href="">Sony Smart TV - 2015</a></h2>
                        <div class="product-sidebar-price">
                            <ins>$700.00</ins> <del>$800.00</del>
                        </div>
                    </div>
                </div>

                <div class="single-sidebar">
                    <h2 class="sidebar-title">Recent Posts</h2>
                    <ul>
                        <li><a href="">Sony Smart TV - 2015</a></li>
                        <li><a href="">Sony Smart TV - 2015</a></li>
                        <li><a href="">Sony Smart TV - 2015</a></li>
                        <li><a href="">Sony Smart TV - 2015</a></li>
                        <li><a href="">Sony Smart TV - 2015</a></li>
                    </ul>
                </div>
            </div>

            <div class="col-md-8">
                <div class="product-content-right">
                    <%--<div class="product-breadcroumb">--%>
                        <%--<a href="index.jsp">Αρχή</a>--%>
                        <%--<a href="">Category Name</a>--%>
                        <%--<a href="">Sony Smart TV - 2015</a>--%>
                    <%--</div>--%>

                    <div class="row">
                        <div class="col-sm-6">
                            <div class="product-images">
                                <div class="product-main-img">
                                    <img src="img/product-2.jpg" alt="">
                                </div>

                                <div class="product-gallery">
                                    <img src="img/product-thumb-1.jpg" alt="">
                                    <img src="img/product-thumb-2.jpg" alt="">
                                    <img src="img/product-thumb-3.jpg" alt="">
                                    <img src="img/product-thumb-4.jpg" alt="">
                                </div>
                            </div>
                        </div>

                        <div class="col-sm-6">
                            <div class="product-inner">
                                <h2 class="product-name"><%=item.getName()%></h2>
                                <% if(item.getBuy_price()!=null){ %>
                                <div class="product-inner-price">
                                    <ins>$<%=item.getBuy_price()%></ins> <a href="#" class="btn btn-primary">ΑΜΕΣΗ ΑΓΟΡΑ</a>
                                </div>
                                <% } %>

                                <form action="" class="cart">
                                    <%--<div class="quantity">--%>
                                        <%--<input type="number" size="4" class="input-text qty text" title="Qty" value="1" name="quantity" min="1" step="1">--%>
                                    <%--</div>--%>
                                    <div class="col-sm-2">
                                        <b>$<%=item.getCurrently()%></b>
                                    </div>
                                    <div class="col-sm-4">
                                        <input id="bid" name="bid" class="form-control input-md" type="text">
                                    </div>
                                    <button class="add_to_cart_button" type="submit">ΥΠΟΒΟΛΗ</button>
                                </form>

                                <div class="product-inner-category">
                                    <p>Κατηγορίες:
                                    <% for(int i=0; i<item.getCategories().size(); i++){%>
                                    <%=item.getCategories().get(i)%>
                                        <% if (i<item.getCategories().size()-1){%>
                                        ,
                                        <%}else{%>
                                        .
                                        <%}%>
                                    <%}%>
                                    </p>
                                </div>

                                <div role="tabpanel">
                                    <ul class="product-tab" role="tablist">
                                        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Περιγραφή</a></li>
                                        <li role="presentation"><a href="#maps" id="gmaps" aria-controls="gmaps" role="tab" data-toggle="tab">Χάρτης</a></li>
                                    </ul>
                                    <div class="tab-content">
                                        <div role="tabpanel" class="tab-pane fade in active" id="home">
                                            <p><%=item.getDesc()%></p>
                                        </div>
                                        <div role="tabpanel" class="tab-pane fade" id="maps">
                                            <div id="map"></div>
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
