<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dao.ItemDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="entities.User" %>

<%  User sessionUser = (User) request.getSession().getAttribute("user");
    if(sessionUser==null || sessionUser.getRole()!=0)
        response.sendRedirect("index.jsp");
%>

<html>
<head>
    <title>Δημιουργία Νέας Δημοπρασίας</title>

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


    <link rel="stylesheet" type="text/css" href="css/jquery.datetimepicker.css"/>

</head>
<body>

<jsp:include page="header.jsp" />

<div class="maincontent-area">
    <div class="zigzag-bottom"></div>
    <div class="container">
        <div class="row">

            <form class="form-horizontal" action="Create" method="post" id="createitem">

                </br><h3>Δημιουργία Νέας Δημοπρασίας</h3></br>
                <%  if (request.getAttribute("item-creation-error") != null) { %>
                <div class ="alert alert-danger"><span class="fa fa-times" aria-hidden="true" style="font-size:1.2em;"></span> <%=request.getAttribute("item-creation-error")%></div>
                <br>
                <% } %>

                <fieldset>
                    <!-- Auction Title -->
                    <div class="form-group required">
                        <label class="col-md-4 control-label" for="title">Τίτλος</label>
                        <div class="col-md-4">
                                <input id="title" name="title" placeholder="Τίτλος Δημοπρασίας" class="form-control input-md" required="" type="text">
                        </div>
                    </div>

                    <!-- Category Multiple Choice-->
                    <div class="form-group required">
                        <label class="col-md-4 control-label" for="categories">Κατηγορία</label>
                        <div class="col-md-4">
                            <select id="categories" name="categories"  multiple size="3">
                                <%  ItemDAO dao = new ItemDAO(true);
                                    List<String> cat = dao.getCategories();
                                    for(int i=0;i<cat.size();i++){    %>
                                <option><%=cat.get(i)%></option>
                                <% } %>
                            </select>
                        </div>
                    </div>

                    <!-- Item Buy Price -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="buyout">Τιμή Άμεσης Αγοράς</label>
                        <div class="col-md-4">
                            <input id="buyout" name="buyout" placeholder="Τιμή Άμεσης Αγοράς" class="form-control input-md" type="text">
                        </div>
                    </div>


                    <!-- First Bid -->
                    <div class="form-group required">
                        <label class="col-md-4 control-label" for="first-bid">Αρχική Προσφορά</label>
                        <div class="col-md-4">
                            <input id="first-bid" name="first-bid" placeholder="Αρχική Προσφορά" class="form-control input-md" required="" type="text">
                        </div>
                    </div>

                    <!-- Country -->
                    <div class="form-group required">
                        <label class="col-md-4 control-label" for="country">Χώρα</label>
                        <div class="col-md-4">
                            <input id="country" name="country" placeholder="π.χ Ελλάδα" class="form-control input-md" required="" type="text">
                        </div>
                    </div>

                    <!-- Location -->
                    <div class="form-group required">
                        <label class="col-md-4 control-label" for="location">Περιοχή</label>
                        <div class="col-md-4">
                            <input id="location" name="location" placeholder="π.χ Αθήνα" class="form-control input-md" required="" type="text">
                        </div>
                    </div>

                    <!-- Latitude-Longitude input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label">Συντεταγμένες</label>
                        <div class="col-md-2">
                            <input id="latitude" name="latitude" placeholder="Latitude" class="form-control input-md" type="text">
                        </div>
                        <div class="col-md-2">
                            <input id="longitude" name="longitude" placeholder="Longitude" class="form-control input-md" type="text">
                        </div>
                    </div>

                    <%--<div class="form-group required">--%>
                        <%--<label class="col-md-4 control-label" for="datetimepicker_dark2">Ημερομηνία/Ώρα έναρξης</label>--%>
                        <%--<div class="col-md-4">--%>
                            <%--<div class="input-group date" >--%>
                                <%--<input id="datetimepicker_dark2" type="text" placeholder="Ημερομηνία/Ώρα έναρξης" class="form-control" />--%>
                                    <%--<span class="input-group-addon">--%>
                                        <%--<i class="fa fa-calendar" aria-hidden="true"></i>--%>
                                    <%--</span>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <div class="form-group required">
                        <label class="col-md-4 control-label" for="datetimepicker_dark">Ημερομηνία/Ώρα λήξης</label>
                        <div class="col-md-4">
                            <div class="input-group date" >
                                <input name="date" id="datetimepicker_dark" type="text" placeholder="Ημερομηνία/Ώρα λήξης" class="form-control" />
                                    <span class="input-group-addon">
                                        <i class="fa fa-calendar" aria-hidden="true"></i>
                                    </span>
                            </div>
                        </div>
                    </div>

                    <!-- Item Description -->
                    <div class="form-group required">
                        <label class="col-md-4 control-label" for="desc">Περιγραφή</label>
                        <div class="col-md-4">
                            <textarea class="form-control" rows="8" id="desc" name="desc" placeholder="Δώστε μια περιγραφή για το αντικείμενο."></textarea>
                        </div>
                    </div>

                    <!-- Item Image browse -->
                    <div class="form-group">
                        <label class="col-md-4  control-label">Επιλογή Φωτογραφίας</label>
                        <div class="col-md-4">
                            <input id="input-1a" type="file" class="file form-control" data-show-preview="false">
                        </div>
                    </div>

                    <!-- Button -->
                    <div class="col-md-8">
                        <span class="pull-right">
                            <button id="create" name="create" class="btn btn-primary">Δημιουργία Δημοπρασίας</button>
                        </span>
                    </div>

                </fieldset>
            </form>
        </div>
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

<script src="javascript/jquery.js"></script>
<script src="javascript/jquery.datetimepicker.full.js"></script>

<script>
    $('#datetimepicker_dark').datetimepicker({theme:'dark'});
//    $('#datetimepicker_dark2').datetimepicker({theme:'dark'});
</script>
</html>
