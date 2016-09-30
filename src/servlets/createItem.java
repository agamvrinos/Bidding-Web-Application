package servlets;

import dao.ItemDAO;
import entities.Item;
import entities.User;
import support.ImageValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;


@WebServlet("/Create")
@MultipartConfig()

public class createItem extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Set request encoding to UTF-8
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // in case you are not logged in
        if (user == null) {
            response.sendRedirect("index.jsp");
            return;
        }

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
                    image = part.getSubmittedFileName();

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
                        RequestDispatcher dispatcher = request.getRequestDispatcher("newauction.jsp");
                        dispatcher.forward(request, response);
                        return;
                    }

                    // Upload File
                    try{
                        InputStream is = request.getPart(part.getName()).getInputStream();

                        int size = is.available();

                        //Allow file of size 50KB
                        //       if(size > 50*1024){
                        //        out.println("Your resume is not uploaded.File size should be less than 50KB");
                        //        return;
                        //       }

                        byte[] b = new byte[size];
                        is.read(b);

                        // Creates "files" folder in tomcat/bin folder
                        File files_dir = new File("files");

                        if (!files_dir.exists())
                            files_dir.mkdirs();

                        if(image != null && !image.equals("")){

                            File uniqueFile = File.createTempFile("img", ".png", files_dir);

                            System.out.println("Submitted Img name: " + image);
                            System.out.println("Generated Img name: " + uniqueFile.getName());

                            image = uniqueFile.getName();

                            FileOutputStream os = new FileOutputStream(uniqueFile);
                            os.write(b);
                            os.flush();
                            os.close();
                            System.out.println("Your resume is uploaded successfully");
                        }
                        is.close();

                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        System.out.println("Title: " + title);
        System.out.println("First Bid: " + first_bid);
        System.out.println("buyout: " + buyout);
        System.out.println("country: " + country);
        System.out.println("location: " + location);
        System.out.println("date: " + date);
        System.out.println("desc: " + desc);

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
                latitude_number, longitude_number, new Date(), null, datetime, user.getUsername(), desc, cat, null, 0, image, 0);

        ItemDAO dao = new ItemDAO(true);
        dao.insertItem(item, 0);

        request.setAttribute("auction-creation-success","yes");
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
