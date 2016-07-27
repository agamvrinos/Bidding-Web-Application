<%@ page import="dao.ItemDAO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
</head>
<body>

<script>
    $(document).ready(function() {
        $("#datepicker").datepicker();
    });
</script>

<jsp:include page="header.jsp" />

<div class="maincontent-area">
    <div class="zigzag-bottom"></div>
    <div class="container">
        <div class="row">

            <form class="form-horizontal" action="Create" method="post" id="createitem">

                </br><h3>Δημιουργία Νέας Δημοπρασίας</h3>
                <br>
                <%  if (request.getAttribute("item-creation-error") != null) { %>
                <div class ="alert alert-danger"><span class="fa fa-times" aria-hidden="true" style="font-size:1.2em;"></span> <%=request.getAttribute("item-creation-error")%></div>
                <br>
                <% } %>

                <fieldset>
                    <!-- title input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="title">Τίτλος</label>
                        <div class="col-md-4">
                            <input id="title" name="title" placeholder="π.χ. Καρέκλα Γραφείου με ροδάκια" class="form-control input-md" required type="text" maxlength="250">

                        </div>
                    </div>

                    <!-- Category Multiple Choice-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="categories">Κατηγορία</label>
                        <div class="col-md-4">
                            <select id="categories" name="categories" required multiple size="3">
                                <%  ItemDAO dao = new ItemDAO(true);
                                    List<String> cat = dao.getCategories();
                                    for(int i=0;i<cat.size();i++){    %>
                                <option><%=cat.get(i)%></option>
                                <% } %>
                            </select>
                        </div>
                    </div>

                    <!-- First Bid input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="first-bid">Αρχική Τιμή</label>
                        <div class="col-md-2">
                            <input id="first-bid" name="first-bid" class="form-control input-md" min="0.01" step="0.01" required type="number">
                        </div>
                    </div>

                    <!-- Buyout Price input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="buyout">Τιμή Εξαγοράς*</label>
                        <div class="col-md-2">
                            <input id="buyout" name="buyout" class="form-control input-md" min="0.01" step="0.01" type="number">
                        </div>
                    </div>

                    <!-- Country input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="country">Χώρα</label>
                        <div class="col-md-4">
                            <input id="country" name="country" placeholder="π.χ. Ελλάδα" class="form-control input-md" required type="text">
                        </div>
                    </div>

                    <!-- Location input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="location">Περιοχή</label>
                        <div class="col-md-4">
                            <input id="location" name="location" placeholder="π.χ. Αθήνα" class="form-control input-md" required type="text">
                        </div>
                    </div>

                    <!-- Latitude-Longitude input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label">Συντεταγμένες*</label>
                        <div class="col-md-2">
                            <input id="latitude" name="latitude" placeholder="Latitude" class="form-control input-md" type="text">
                        </div>
                        <div class="col-md-2">
                            <input id="longitude" name="longitude" placeholder="Longitude" class="form-control input-md" type="text">
                        </div>
                    </div>

                    <!-- date and time input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label">Χρόνος Λήξης</label>
                        <div class="col-md-2">
                            <input id="datepicker" name="date" placeholder="ΜΜ/ΗΗ/ΧΧΧΧ" class="form-control input-md" required type="text">
                        </div>
                        <div class="col-md-2">
                            <input type="text" class="form-control input-md" placeholder="ΩΩ:ΛΛ" required pattern="([01]?[0-9]{1}|2[0-3]{1}):[0-5]{1}[0-9]{1}" title="π.χ. 23:59" name="time"/>
                        </div>
                    </div>

                    <!-- Desc input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="desc">Περιγραφή</label>
                        <div class="col-md-4">
                            <textarea rows="5" cols="50" id="desc" name="desc" form="createitem" required placeholder="Δώστε μια περιγραφή για το αντικείμενο..."></textarea>

                        </div>
                    </div>

                    <br>

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


</body>
</html>
