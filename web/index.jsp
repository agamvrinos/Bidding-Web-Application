<%--
  Created by IntelliJ IDEA.
  User: agg
  Date: 15/7/2016
  Time: 1:41 πμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
      <meta charset="utf-8">
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

    <input type="submit" id="register_button" class="btn btn-primary " value="Εγγραφή">
    <input type="submit" id="login_button" class="btn btn-success " value="Σύνδεση">


  </body>
</html>
