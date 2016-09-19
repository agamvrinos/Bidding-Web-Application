<%--
  Created by IntelliJ IDEA.
  User: agg
  Date: 15/7/2016
  Time: 1:41 πμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
  <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">

      <script src="javascript/jquery-1.10.2.js"></script>
      <script src="javascript/jquery-ui.js"></script>
      <script src="javascript/form.js"></script>
      <script src="javascript/bootstrap.min.js"></script>
      <link rel="stylesheet" href="css/jquery-ui.css">
      <link rel="stylesheet" href="css/bootstrap.min.css">

      <title>Title</title>
  </head>
  <body>
    Hello Stefa. MEGA FRONT END TEST <br>

    <div class="se-pre-con"></div>
    <div id="regdialog-form" title="Δημιουργία νέου χρήστη"  style="display:none;">

        <form class="form-horizontal" action="Register" method="post">

            <p class="validateTips">Όλα τα πεδία είναι υποχρεωτικά.</p>

            <fieldset>
                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="fullname">Ονοματεπώνυμο</label>
                    <div class="col-md-4">
                        <input id="fullname" name="fullname" placeholder="Ονοματεπώνυμο" class="form-control input-md" required="" type="text">

                    </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="username">Όνομα Χρήστη</label>
                    <div class="col-md-4">
                        <input id="username" name="username" placeholder="Όνομα Χρήστη" class="form-control input-md" required="" type="text">

                    </div>
                </div>

                <!-- Password input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="password">Κωδικός</label>
                    <div class="col-md-4">
                        <input id="password" name="password" placeholder="Κωδικός" class="form-control input-md" required="" type="password">

                    </div>
                </div>

                <!-- Password input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="password_repeat">Επαλήθευση Κωδικού</label>
                    <div class="col-md-4">
                        <input id="password_repeat" name="password_repeat" placeholder="Επαλήθευση Κωδικού" class="form-control input-md" required="" type="password">

                    </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="email">Email</label>
                    <div class="col-md-4">
                        <input id="email" name="email" placeholder="Email" class="form-control input-md" required="" type="text">

                    </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="phone">Τηλέφωνο</label>
                    <div class="col-md-4">
                        <input id="phone" name="phone" placeholder="Τηλέφωνο" class="form-control input-md" required="" type="text">

                    </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="country">Χώρα Κατοικίας</label>
                    <div class="col-md-4">
                        <input id="country" name="country" placeholder="Χώρα Κατοικίας" class="form-control input-md" required="" type="text">
                    </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="city">Πόλη Κατοικίας</label>
                    <div class="col-md-4">
                        <input id="city" name="city" placeholder="Πόλη Κατοικίας" class="form-control input-md" required="" type="text">
                    </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="address">Διεύθυνση</label>
                    <div class="col-md-4">
                        <input id="address" name="address" placeholder="Διεύθυνση" class="form-control input-md" required="" type="text">
                    </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="afm">ΑΦΜ</label>
                    <div class="col-md-4">
                        <input id="afm" name="afm" placeholder="ΑΦΜ" class="form-control input-md" required="" type="text">
                    </div>
                </div>

                <!-- Select Basic -->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="UserRole">Τύπος Χρήστη</label>
                    <div class="col-md-4">
                        <select id="UserRole" name="UserRole" class="form-control">
                            <option value="1">Πωλητής</option>
                            <option value="2">Προσφέρων</option>
                        </select>
                    </div>
                </div>

                <!-- Button -->
                <div class="form-group">
                    <div class="col-md-4">
                        <button id="register" name="register" class="btn btn-primary">Εγγραφή</button>
                    </div>
                </div>

            </fieldset>
        </form>
    </div>

    <div id="logdialog-form" title="Σύνδεση χρήστη" style="display:none;">
        <form class="form-horizontal" action="Login" method="post">
            <fieldset>
                <!-- Text input-->
                <div class="form-group">
                    <%--<label class="col-md-4 control-label" for="username">Όνομα Χρήστη</label>--%>
                    <%--<br/>--%>
                    <div class="col-md-10">
                        <input id="username" name="username" placeholder="Όνομα Χρήστη" class="form-control input-md" required="" type="text">
                    </div>
                </div>

                <!-- Password input-->
                <div class="form-group">
                    <%--<label class="col-md-4 control-label" for="password">Κωδικός</label>--%>
                    <%--<br/>--%>
                    <div class="col-md-10">
                        <input id="password" name="password" placeholder="Κωδικός" class="form-control input-md" required="" type="password">
                    </div>
                </div>

                <!-- Button -->
                <div class="form-group">
                    <div class="col-md-6">
                        <button id="login" name="login" class="btn btn-primary">Σύνδεση</button>
                    </div>
                </div>

                <%--<label for="username">Όνομα χρήστη</label>--%>
                <%--<br/>--%>
                <%--<input type="text" name="username" id="username" class="text ui-widget-content ui-corner-all" required>--%>
                <%--<br/>--%>
                <%--<label for="password">Κωδικός</label>--%>
                <%--<br/>--%>
                <%--<input type="password" name="password" id="password" class="text ui-widget-content ui-corner-all" required>--%>
                <%--<br/>--%>
                <%--<br/>--%>
                <%--<input class="button-primary" type="submit" value="Συνδεση">--%>
            </fieldset>
        </form>
    </div>

    <input type="submit" id="register_button" class="btn btn-primary " value="Εγγραφή">
    <input type="submit" id="login_button" class="btn btn-success " value="Σύνδεση">


  </body>
</html>
