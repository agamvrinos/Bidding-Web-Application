package servlets;

import entities.Items;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;

@WebServlet("/LoadXmlAuctions")
public class LoadXmlAuctions extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            File file = new File("/home/agg/Desktop/TedExamples/test.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Items.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Items items = (Items) jaxbUnmarshaller.unmarshal(file);

            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(items, response.getWriter());

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
