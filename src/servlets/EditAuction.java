package servlets;

import dao.ItemDAO;
import entities.AppEntities.Item;
import entities.AppEntities.User;
import support.ImageValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/EditAuction")
@MultipartConfig()

public class EditAuction extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("error_page.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Set request encoding to UTF-8
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        ItemDAO dao = new ItemDAO(true);

        // in case you are not logged in
        if (user == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        String id = "";
        String title = "";
        String first_bid = "";
        String buyout = "";
        String country = "";
        String location = "";
        String latitude = "";
        String longitude = "";
        String date = "";
        String desc = "";
        String image = "";

        for(Part part : request.getParts()) {

            String name = part.getName();

            if (name != null) {
                if (name.equals("title")) {
                    title = getValue(part);
                } else if (name.equals("id")){
                    id = getValue(part);
                } else if (name.equals("first-bid")) {
                    first_bid = getValue(part);
                } else if (name.equals("buyout")) {
                    buyout = getValue(part);
                } else if (name.equals("country")) {
                    country = getValue(part);
                } else if (name.equals("location")) {
                    location = getValue(part);
                } else if (name.equals("latitude")) {
                    latitude = getValue(part);
                } else if (name.equals("longitude")) {
                    longitude = getValue(part);
                } else if (name.equals("date")) {
                    date = getValue(part);
                } else if (name.equals("desc")) {
                    desc = getValue(part);
                } else if(name.equals("image")) {
//                    image = part.getSubmittedFileName();

                    // No image submitted
                    if (image.equals("")) {
                        System.out.println("No Image Submitted");
                        continue;
                    }

                    // Validate Image format (png/jpg/bmp/gif allowed)
                    ImageValidator validator = new ImageValidator();
                    boolean validation = validator.validate(image);

                    // If the format is not one of the above ~> Error
                    if (!validation){
                        request.setAttribute("item-creation-error","Η επιλεγμένη εικόνα πρέπει να είναι της μορφής png/jpg/bmp/gif");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("editAuction.jsp?id=" + id);
                        dispatcher.forward(request, response);
                        return;
                    }

                    // Upload File
                    try{
                        InputStream is = request.getPart(part.getName()).getInputStream();

                        int size = is.available();

                        byte[] b = new byte[size];
                        is.read(b);

                        // Creates "files" folder in tomcat/bin folder
                        File files_dir = new File("files");

                        if (!files_dir.exists())
                            files_dir.mkdirs();

                        if(image != null && !image.equals("")){

                            File uniqueFile = File.createTempFile("img", ".png", files_dir);

                            image = uniqueFile.getName();

                            FileOutputStream os = new FileOutputStream(uniqueFile);
                            os.write(b);
                            os.flush();
                            os.close();
                        }
                        is.close();

                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        Item old_item = dao.getItemByID(Integer.valueOf(id));

        Double first_bid_number = null;
        Double buyout_number = null;
        Double latitude_number = null;
        Double longitude_number = null;

        try {
            first_bid_number = Double.parseDouble(first_bid);

            if (!latitude.equals("") && !longitude.equals("")) {
                latitude_number = Double.parseDouble(latitude);
                longitude_number = Double.parseDouble(longitude);
            }

            if (!buyout.equals(""))
                buyout_number = Double.parseDouble(buyout);

        }
        catch (NumberFormatException ex){
            request.setAttribute("item-creation-error","Κάτι πήγε στραβά με τα αριθμητικά στοιχεία που συμπληρώσατε! Προσπαθήστε ξανά! " + ex.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("editAuction.jsp?id=" + id);
            dispatcher.forward(request, response);
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date datetime;
        try {
            datetime = sdf.parse(date);
        }
        catch (ParseException ex){
            request.setAttribute("item-creation-error","Κάτι πήγε στραβά με την ημερομηνία που συμπληρώσατε! Προσπαθήστε ξανά!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("editAuction.jsp?id=" + id);
            dispatcher.forward(request, response);
            return;
        }

        String[] categories = request.getParameterValues("categories");
        List<String> cat = new ArrayList<String>();
        for (String temp : categories){
            cat.add(temp);
        }


        Item item = new Item(Integer.valueOf(id), title, first_bid_number, first_bid_number, buyout_number, country, location,
                latitude_number, longitude_number, new Date(), null, datetime, user.getUsername(), desc, cat, old_item.getBids(), old_item.getState(), image, old_item.getTotal_offers());

        // Update item values
        dao.updateItem(item, old_item);

        request.setAttribute("auction-edit-success","yes");
        RequestDispatcher dispatcher = request.getRequestDispatcher("my_auctions.jsp");
        dispatcher.forward(request, response);
    }

    //Get the Value of the Form field other than type form field type file
    private String getValue(Part part) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(part.getInputStream(), "UTF-8"));
        StringBuilder value = new StringBuilder();
        char[] buffer = new char[1024];
        for (int length = 0; (length = reader.read(buffer)) > 0;) {
            value.append(buffer, 0, length);
        }
        return value.toString();
    }
}
