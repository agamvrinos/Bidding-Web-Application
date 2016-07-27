package servlets;

import dao.ItemDAO;
import entities.Item;
import entities.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.DoubleSummaryStatistics;
import java.util.List;


@WebServlet("/Create")
public class createItem extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Set request encoding to UTF-8
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // in case you are not logged in
        if (user==null) {
            response.sendRedirect("index.jsp");
            return;
        }

        String title = request.getParameter("title");
        String first_bid = request.getParameter("first-bid");
        String buyout = request.getParameter("buyout");
        String country = request.getParameter("country");
        String location = request.getParameter("location");
        String latitude = request.getParameter("latitude");
        String longitude = request.getParameter("longitude");
        String date = request.getParameter("date");
        String desc = request.getParameter("desc");

        Double first_bid_number = null;
        Double buyout_number = null;
        Double latitude_number = null;
        Double longitude_number = null;

        try {

            first_bid_number = Double.parseDouble(first_bid);

            if (latitude != "" && longitude != "") {
                latitude_number = Double.parseDouble(latitude);
                longitude_number = Double.parseDouble(longitude);
            }

            if (buyout != "")
                buyout_number = Double.parseDouble(buyout);

        }
        catch (NumberFormatException ex){
            request.setAttribute("item-creation-error","Κάτι πήγε στραβά με τα αριθμητικά στοιχεία που συμπληρώσατε! Προσπαθήστε ξανά! " + ex.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("newauction.jsp");
            dispatcher.forward(request, response);
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date datetime;
        try {
            datetime = sdf.parse(date);
        }
        catch (ParseException ex){
            //TODO: Den xreiazetai me to new calendar mallon
            request.setAttribute("item-creation-error","Κάτι πήγε στραβά με την ημερομηνία που συμπληρώσατε! Προσπαθήστε ξανά!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("newauction.jsp");
            dispatcher.forward(request, response);
            return;
        }

        String[] categories = request.getParameterValues("categories");
        List<String> cat = new ArrayList<String>();
        for (String temp : categories){
            cat.add(temp);
        }


        Item item = new Item(null, title, first_bid_number, first_bid_number, buyout_number, country, location,
                latitude_number, longitude_number, new Date(), null, datetime, user.getUsername(), desc, cat, null);

        ItemDAO dao = new ItemDAO(true);
        dao.insertItem(item);

        //TODO: MAPA! na se petaei sto myauction.jsp tou xristi
        response.sendRedirect("index.jsp");
    }

}
