<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

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
        /* make sidebar nav vertical */
        @media (min-width: 768px) {
            .sidebar-nav .navbar .navbar-collapse {
                padding: 0;
                max-height: none;
            }
            .sidebar-nav .navbar ul {
                float: none;
            }
            .sidebar-nav .navbar ul:not {
                display: block;
            }
            .sidebar-nav .navbar li {
                float: none;
                display: block;
            }
            .sidebar-nav .navbar li a {
                padding-top: 12px;
                padding-bottom: 12px;
                color: black;
                border: solid 1px #085849;
            }

            .sidebar-nav .navbar-nav>.active>a, .sidebar-nav .navbar-nav>.active>a:hover, .sidebar-nav .navbar-nav>.active>a:focus {
                color: white;
                background-color: #157d69;
            }

            .hide{
                display: none;
            }

            .sidebar-nav .navbar-nav .dropdown-menu>.active>a, .dropdown-menu>.active>a:focus, .dropdown-menu>.active>a:hover {
                color: white;
                background-color: #2f9a86;
            }

            tr {
                cursor: pointer;
            }

            .is_read-1 {
                background-color: #c1c1c1;
                color: black;
                font-weight: 600;
            }


        }
    </style>

</head>
<body>

<jsp:include page="header.jsp" />

<div class="maincontent-area">
    <div class="zigzag-bottom"></div>
    <div class="container">
        <div class="row">
            <br><h3>Τα Μηνύματα μου</h3><br>
        </div>
        <div class="row">
            <div class="col-sm-3">
                <div class="sidebar-nav">
                    <div class="navbar navbar-default" role="navigation">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-navbar-collapse">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                            <span class="visible-xs navbar-brand">Μενού</span>
                        </div>
                        <div class="navbar-collapse collapse sidebar-navbar-collapse" style="background-color: white;">
                            <ul class="nav navbar-nav">

                                <%--Received--%>
                                <li class="active"><a href="#received">Εισερχόμενα</a></li>
                                <%--Sent--%>
                                <li><a class="link" href="#sent">Εξερχόμενα</a></li>
                                <%--Deleted--%>
                                <li><a class="link" href="#deleted">Διεγραμμένα &nbsp<span class="badge">10</span></a></li>
                                <%--Others--%>
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle link" data-toggle="dropdown">Περισσότερα <b class="caret"></b></a>
                                </li>

                            </ul>
                        </div><!--/.nav-collapse -->
                    </div>
                </div>
            </div>
            <div class="col-sm-9 content-container">
                <div id = "received" class="active">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th></th>
                            <th></th>
                            <th></th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr class='clickable-row is_read-1' data-href='index.html'>
                            <td>
                                <input type="checkbox" id="checkbox3">
                                <label for="checkbox3"></label>
                            </td>
                            <td>Yannis Smaragdakis</td>
                            <td>[K31: Compilers] Φωτογραφίες</td>
                            <td>15/03/2017 16:40</td>
                        </tr>
                        <tr class='clickable-row is_read-0' data-href='index.html'>
                            <td>
                                <input type="checkbox" id="checkbox4">
                                <label for="checkbox4"></label>
                            </td>
                            <td>Yannis Smaragdakis</td>
                            <td>[K31: Compilers] Φωτογραφίες</td>
                            <td>15/03/2017 16:40</td>
                        </tr>
                        <tr class='clickable-row is_read-0' data-href='index.html'>
                            <td>
                                <input type="checkbox" id="checkbox5">
                                <label for="checkbox5"></label>
                            </td>
                            <td>Yannis Smaragdakis</td>
                            <td>[K31: Compilers] Φωτογραφίες</td>
                            <td>15/03/2017 16:40</td>
                        </tr>
                        <tr class='clickable-row is_read-0' data-href='index.html'>
                            <td>
                                <input type="checkbox" id="checkbox2">
                                <label for="checkbox2"></label>
                            </td>
                            <td>Yannis Smaragdakis</td>
                            <td>[K31: Compilers] Φωτογραφίες</td>
                            <td>15/03/2017 16:40</td>
                        </tr>
                        <tr class='clickable-row is_read-0' data-href='index.html'>
                            <td>
                                <input type="checkbox" id="checkbox2">
                                <label for="checkbox2"></label>
                            </td>
                            <td>Yannis Smaragdakis</td>
                            <td>[K31: Compilers] Φωτογραφίες</td>
                            <td>15/03/2017 16:40</td>
                        </tr>
                        <tr class='clickable-row is_read-0' data-href='index.html'>
                            <td>
                                <input type="checkbox" id="checkbox2">
                                <label for="checkbox2"></label>
                            </td>
                            <td>Yannis Smaragdakis</td>
                            <td>[K31: Compilers] Φωτογραφίες</td>
                            <td>15/03/2017 16:40</td>
                        </tr>
                        </tbody>
                    </table>

                </div>
                <div id = "sent" class="hide">
                    <span style="font-size: 25px; font-weight: bold;">Sent Messages</span>
                </div>
                <div id="deleted" class="hide">
                    <span style="font-size: 25px; font-weight: bold;">Deleted Messages</span>
                </div>
                <div id="others" class="hide">
                    <span style="font-size: 25px; font-weight: bold;">Other Messages</span>
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

<script>
    $(document).ready(function() {
        $('.sidebar-nav .navbar-nav li a').click(function(event){
            event.preventDefault();//stop browser to take action for clicked anchor

            //get displaying tab content jQuery selector
            var active_tab_selector = $('.sidebar-nav .navbar-nav > li.active > a').attr('href');

            //find actived navigation and remove 'active' css
            var actived_nav = $('.sidebar-nav .navbar-nav > li.active');
            actived_nav.removeClass('active');

            //add 'active' css into clicked navigation
            $(this).parents('li').addClass('active');

            //hide displaying tab content
            $(active_tab_selector).removeClass('active');
            $(active_tab_selector).addClass('hide');

            //show target tab content
            var target_tab_selector = $(this).attr('href');
            $(target_tab_selector).removeClass('hide');
            $(target_tab_selector).addClass('active');
        });
    });
</script>

<script>
    jQuery(document).ready(function($) {
        $(".clickable-row").click(function() {
            window.document.location = $(this).data("href");
        });
    });
</script>
</body>


</html>
