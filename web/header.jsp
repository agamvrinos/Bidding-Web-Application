<%@ page contentType="text/html;charset=UTF-8" language="java" import="entities.User" %>

    <div class="header-area">
        <div class="container">
            <div class="row">
                <div class="col-md-8">
                    <div class="user-menu">
                        <ul>
                            <li><a href="myprofile.jsp"><i class="fa fa-user"></i>Το Προφίλ μου</a></li>
                            <%--<li><a href="#"><i class="fa fa-heart"></i> Wishlist</a></li>--%>
                            <%--<li><a href="cart.html"><i class="fa fa-user"></i> My Cart</a></li>--%>
                            <%--<li><a href="checkout.html"><i class="fa fa-user"></i> Checkout</a></li>--%>
                            <% if (request.getSession().getAttribute("user")==null){ %>
                                <li><a href="login.jsp" id="login_button"><i class="fa fa-user"></i>Σύνδεση</a></li>
                                <li><a href="register.jsp" id="register_button"><i class="fa fa-user"></i>Εγγραφή</a></li>
                            <% }
                            else {
                            %>
                            <li><a href="my_messages.jsp" ><i class="fa fa-envelope"></i>Μηνύματα</a></li>
                            <li><a href="my_auctions.jsp" id="my_auctions"><i class="fa fa-user"></i>Διαχείριση Δημοπρασιών</a></li>
                            <li><a href="Logout" id="logout_button"><i class="fa fa-user"></i>Αποσύνδεση</a></li>
                            <%
                                }
                            %>
                        </ul>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="header-right">

                        <ul class="list-unstyled list-inline">
                            <li>
                                Καλώς ήρθες,
                                <% if (request.getSession().getAttribute("user")==null){ %>
                                    επισκέπτη!
                                <% } else {
                                    User user = (User) request.getSession().getAttribute("user");
                                    String username = user.getUsername();
                                %>
                                    <%=username%>!
                                <%}%>

                            </li>

                            <li class="dropdown dropdown-small">
                                <a data-toggle="dropdown" data-hover="dropdown" class="dropdown-toggle" href="#"><span class="key">Γλώσσα: </span><span class="value">Ελληνικά </span><b class="caret"></b></a>
                                <ul class="dropdown-menu">
                                    <li><a href="#">English</a></li>
                                    <li><a href="#">French</a></li>
                                    <li><a href="#">German</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div> <!-- End header area -->

    <div class="site-branding-area">
        <div class="container">
            <div class="row">
                <div class="col-sm-6">
                    <div class="logo">
                        <h1><a href="index.jsp">e<span>Bidders</span> <span style = "font-size: 15px; color:rgb(119, 164, 175); font-weight: bold;">BETA</span></a></h1>
                    </div>
                </div>

                <div class="col-sm-6">
                    <% if (request.getSession().getAttribute("user")!=null){ %>
                    <div class="shopping-item">
                        <a href="my_messages.jsp"><span id="msg">Loading...</span><i class="fa fa-envelope"></i><span class="product-count">0</span></a>
                    </div>
                    <%}%>
                </div>
            </div>
        </div>
    </div> <!-- End site branding area -->

    <div class="mainmenu-area">
        <div class="container">
            <div class="row">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                </div>
                <div class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="index.jsp">Αρχικη</a></li>
                        <li><a href="#">Γενικα</a></li>
                        <li><a href="categories.jsp">Δημοπρασιες</a></li>
                        <li><a href="#">Βοηθεια</a></li>
                        <li><a href="#">Επικοινωνια</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div> <!-- End mainmenu area -->

    <script src="javascript/jquery-1.10.2.js"></script>
    <script src="javascript/jquery-ui.js"></script>

    <%
    // If user is logged in
    if (request.getSession().getAttribute("user") !=null ) {
        User sessionUser = (User) request.getSession().getAttribute("user");%>
        <script>
            (function(){
                var messages = $('.product-count'),

                poll = function() {
                    console.log("Hey");
                    jQuery.ajax({
                        url: 'checkUserNewMessages',
                        method: "GET",
                        type: 'JSON',
                        data : "&userid="+<%=sessionUser.getId()%>,// query parameters 1st

                        success : function(response){
                            if (response != 0){
                                $('.shopping-item').css('background-color', '#377d70');
                                $('.shopping-item a').css('color', '#222');

                            }
                            $('#msg').text('Μηνύματα');

                            $(messages).text(response);
                        },
                        error : function(){
                            console.log("ERROR at: checkUserNewMessages");
                            clearInterval(pollInterval);
                        }
                    });
                },

                pollInterval = setInterval(function() {
                    poll();
                }, 60000);

                poll();

            })();
        </script>
    <%}%>
