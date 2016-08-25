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
                                <li><a class="link" href="#others">Διεγραμμένα &nbsp<span class="badge">10</span></a></li>
                                <%--Others--%>
                                <li class="dropdown">
                                    <a href="#" rel="others" class="dropdown-toggle link" data-toggle="dropdown">Περισσότερα <b class="caret"></b></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="#">Σημαντικά</a></li>
                                        <li><a href="#">Πρόχειρα</a></li>
                                    </ul>
                                </li>

                            </ul>
                        </div><!--/.nav-collapse -->
                    </div>
                </div>
            </div>
            <div class="col-sm-9 content-container">
                <div id = "received" class="active">
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque in consequat turpis, efficitur porta lectus. Aliquam sit amet auctor ante. Nullam interdum non tellus in iaculis. Ut id consectetur ipsum, in consectetur nisl. Mauris dui tellus, porttitor ac nisi nec, molestie cursus nisl. Suspendisse et odio quis est porta suscipit eu a urna. Phasellus eget lectus nec elit consequat efficitur. In auctor nisi non vulputate aliquam. Suspendisse aliquam pellentesque faucibus. Vestibulum urna mi, imperdiet in commodo eget, hendrerit vel dui. Cras id arcu pellentesque, luctus urna at, accumsan nibh.

                    Nullam est leo, elementum mattis lectus sit amet, mollis rutrum ante. Mauris vestibulum volutpat volutpat. Suspendisse at magna urna. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Quisque in augue tristique, mattis arcu ut, rutrum urna. Etiam nec massa at libero pharetra suscipit. In mi risus, feugiat ut nisl in, pretium molestie lectus. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Donec ac ex ut felis pulvinar rutrum eget sit amet turpis. Nulla lacinia tempus neque, ac vulputate est aliquet nec. Mauris tincidunt egestas mauris, et aliquet augue iaculis ut. Sed tincidunt eget est ac gravida. Vestibulum in eros et nulla convallis blandit.

                    Morbi auctor, purus in commodo dapibus, est lectus tincidunt elit, sit amet efficitur dui nibh et ex. Curabitur ornare malesuada turpis, sed sodales lectus condimentum quis. Nunc dictum pellentesque ipsum, vel accumsan ex posuere non. Sed nec vulputate ligula, ut egestas lacus. Donec dapibus elit id ipsum bibendum, quis varius purus lacinia. Nullam finibus ac enim sed dignissim. Suspendisse sollicitudin efficitur nulla laoreet convallis. Nunc iaculis ipsum tempor, lobortis risus sed, porta urna. Cras quis ex vitae augue eleifend ornare eu sed dui. Suspendisse a suscipit sem. Donec gravida convallis mi ut ornare. Nullam magna dui, congue et eros vitae, tincidunt fermentum purus. Pellentesque sagittis est eget diam dignissim, quis dictum urna dignissim. Suspendisse massa ante, tempus vitae quam eget, imperdiet volutpat odio. Aliquam in imperdiet felis. Nunc mauris tortor, consequat in diam ut, porta pellentesque magna.

                </div>
                <div id = "sent" class="hide">
                    Loreal2222 ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque in consequat turpis, efficitur porta lectus. Aliquam sit amet auctor ante. Nullam interdum non tellus in iaculis. Ut id consectetur ipsum, in consectetur nisl. Mauris dui tellus, porttitor ac nisi nec, molestie cursus nisl. Suspendisse et odio quis est porta suscipit eu a urna. Phasellus eget lectus nec elit consequat efficitur. In auctor nisi non vulputate aliquam. Suspendisse aliquam pellentesque faucibus. Vestibulum urna mi, imperdiet in commodo eget, hendrerit vel dui. Cras id arcu pellentesque, luctus urna at, accumsan nibh.

                    Nullam est leo, elementum mattis lectus sit amet, mollis rutrum ante. Mauris vestibulum volutpat volutpat. Suspendisse at magna urna. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Quisque in augue tristique, mattis arcu ut, rutrum urna. Etiam nec massa at libero pharetra suscipit. In mi risus, feugiat ut nisl in, pretium molestie lectus. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Donec ac ex ut felis pulvinar rutrum eget sit amet turpis. Nulla lacinia tempus neque, ac vulputate est aliquet nec. Mauris tincidunt egestas mauris, et aliquet augue iaculis ut. Sed tincidunt eget est ac gravida. Vestibulum in eros et nulla convallis blandit.

                    Morbi auctor, purus in commodo dapibus, est lectus tincidunt elit, sit amet efficitur dui nibh et ex. Curabitur ornare malesuada turpis, sed sodales lectus condimentum quis. Nunc dictum pellentesque ipsum, vel accumsan ex posuere non. Sed nec vulputate ligula, ut egestas lacus. Donec dapibus elit id ipsum bibendum, quis varius purus lacinia. Nullam finibus ac enim sed dignissim. Suspendisse sollicitudin efficitur nulla laoreet convallis. Nunc iaculis ipsum tempor, lobortis risus sed, porta urna. Cras quis ex vitae augue eleifend ornare eu sed dui. Suspendisse a suscipit sem. Donec gravida convallis mi ut ornare. Nullam magna dui, congue et eros vitae, tincidunt fermentum purus. Pellentesque sagittis est eget diam dignissim, quis dictum urna dignissim. Suspendisse massa ante, tempus vitae quam eget, imperdiet volutpat odio. Aliquam in imperdiet felis. Nunc mauris tortor, consequat in diam ut, porta pellentesque magna.

                </div>
                <div id="others" class="hide"></div>

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
</body>


</html>
