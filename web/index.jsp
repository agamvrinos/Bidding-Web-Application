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
      <script src="jquery-1.10.2.js"></script>
      <script src="jquery-ui.js"></script>
      <script src="form.js"></script>
      <title>Title</title>
  </head>
  <body>
    Hello Stefa. MEGA FRONT END TEST <br>

    <div class="se-pre-con"></div>
    <div id="regdialog-form" title="Δημιουργία νέου χρήστη"  style="display:none;">
        <p class="validateTips">Όλα τα πεδία είναι υποχρεωτικά.</p>

        <form action="" method="post">
            <fieldset>
                <label for="fullname">Ονοματεπώνυμο</label>
                <input type="text" name="fullname" id="fullname" placeholder="Ονοματεπώνυμο" class="text ui-widget-content ui-corner-all" required>
                <br>
                <label for="username">Όνομα χρήστη</label>
                <input type="text" name="username" id="username" placeholder="username" class="text ui-widget-content ui-corner-all" required>
                <br>
                <label for="email">Email</label>
                <input type="text" name="email" id="email"  placeholder="Email"  class="text ui-widget-content ui-corner-all" required>
                <br>
                <label for="password">Κωδικός</label>
                <input type="password" name="password" id="password"  placeholder="κωδικός" class="text ui-widget-content ui-corner-all" required>
                <br>
                <label for="passwordc">Επαλήθευση Κωδικού</label>
                <input type="password" name="passwordc" id="passwordc"  placeholder="Επαλήθευση Κωδικού" class="text ui-widget-content ui-corner-all" required>

                <!-- Allow form submission with keyboard without duplicating the dialog button -->
                <br/>
                <input class="button-primary" type="submit" value="Εγγραφή">

            </fieldset>
        </form>
    </div>

    <input type="submit" id="register_button" value="Εγγραφή">


  </body>
</html>
