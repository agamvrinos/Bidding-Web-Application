<%@ page import="dao.ItemDAO" %>
<%@ page import="java.util.*" %>
<%@ page import="dao.DAOUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Κατηγορίες Δημοπρασιών</title>

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
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/responsive.css">
    <link rel="stylesheet" href="css/jquery-ui.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
    <jsp:include page="header.jsp" />

    <div class="maincontent-area">
        <div class="zigzag-bottom"></div>
        <div class="container">

            <br>
            <br>

            <%
                ItemDAO dao = new ItemDAO(true);
                String page_str = request.getParameter("page");
                Integer pageid;
                if (page_str != null)
                    pageid = DAOUtil.IntConvert(page_str);
                else
                    pageid = 1;
                List<String> search_categories = dao.getCategories();
                List<String> categories = dao.getLimitedCategories(pageid);
            %>

            <div class="row">
                <div class="splash" style="background-color: rgb(211, 221, 224);">

                    <form id="search" action="searchResults.jsp" method="get">
                        <div class="search-bar">
                            <div class="input-group input-group-md">
                                <input name="text" type="text" class="form-control" placeholder="Αναζήτηση Δημοπρασίας..." aria-describedby="sizing-addon1">
                                <span class="input-group-btn">
                                    <button class="btn btn-secondary" id="search-btn" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample" style="height: 34px; background-color: #8cb6c2;" >
                                        <span class="caret" id="search-caret"></span>
                                    </button>
                                    <button class="btn btn-primary" style="margin-left: 14px; border-radius: 3px;">
                                        Αναζήτηση
                                    </button>
                                </span>
                            </div>
                        </div>

                        <div class="collapse" id="collapseExample">
                            <div class="card card-block">
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-md-4">
                                            <label for="price" style="font-weight: 400; color: rgb(52, 82, 90);">Μέγιστη Τιμή:</label>
                                            <input name="price" type="text" class="form-control" id="price" placeholder="π.χ 40">
                                        </div>
                                        <div class="col-md-4">
                                            <label for="category" style="font-weight: 400; color: rgb(52, 82, 90);">Κατηγορία:</label>
                                            <%--<input name="category" type="text" class="form-control" id="category" placeholder="π.χ Ρούχα">--%>
                                            <select name="category" id="category" class="form-control">
                                                <option value="">Όλες</option>
                                                <%for(int j=0; j<search_categories.size(); j++){%>
                                                <option value="<%=search_categories.get(j)%>"><%=search_categories.get(j)%></option>
                                                <%}%>
                                            </select>
                                        </div>
                                        <div class="col-md-4">
                                            <label for="location" style="font-weight: 400; color: rgb(52, 82, 90);">Περιοχή:</label>
                                            <input name="location" type="text" class="form-control" id="location" placeholder="π.χ Αθήνα">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <br>

            <div class="row">
                </br><h3>Κατηγορίες Δημοπρασιών</h3></br>
            </div>

            <%
                for (int i = 0; i < categories.size(); i ++)
                {
            %>
                <div class="row" style="padding-bottom: 5px;">
                    <div class="btn-group btn-group-justified" role="group" aria-label="...">
                        <div class="btn-group" role="group">
                            <%if (i % 3 == 0) {%>
                                <a href="searchResults.jsp?text=&price=&location=&category=<%=categories.get(i)%>" class="btn btn-default" role="button" style="background-color: rgb(140, 182, 194); border-color: rgb(52, 82, 90);"><%=categories.get(i)%></a>
                            <%}%>
                        </div>
                        <div class="btn-group" role="group">
                            <%if (i + 1 < categories.size() &&  (i+1) % 3 == 1) {%>
                                <a href="searchResults.jsp?text=&price=&location=&category=<%=categories.get(i+1)%>" class="btn btn-default" role="button" style="background-color: rgb(140, 182, 194); border-color: rgb(52, 82, 90);"><%=categories.get(i + 1)%> </a>
                            <%}%>
                        </div>
                        <div class="btn-group" role="group">
                            <%if (i + 2 < categories.size() &&  (i+2) % 3 == 2) {%>
                                <a href="searchResults.jsp?text=&price=&location=&category=<%=categories.get(i+2)%>" class="btn btn-default" role="button" style="background-color: rgb(140, 182, 194); border-color: rgb(52, 82, 90);"><%=categories.get(i + 2)%></a>
                            <%}%>
                        </div>
                    </div>

                </div>
            <%
                }
            %>

            <%--PAGING--%>
            <div class="alignright">
                <form class="form-horizontal" action="searchResults.jsp" method="get" >
                    <% if (pageid > 1){%>
                    <a class="btn btn-default" style="display:inline-block" href="categories.jsp?page=<%=pageid-1%>">
                        <strong><</strong>
                    </a>
                    <%}%>
                    <input type="text" style="width: 40px; height: 33px; display:inline-block" name="page" value="<%=pageid%>" />
                    από <%=dao.getNumOfPages()%>
                    <% if (pageid < dao.getNumOfPages()){%>
                    <a class="btn btn-default" style="display:inline-block" href="categories.jsp?page=<%=pageid+1%>">
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
