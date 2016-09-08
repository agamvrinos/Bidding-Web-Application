/**
 * Created by stef on 28/8/2016.
 */

import dao.DAOUtil;
import dao.ItemDAO;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

@WebListener()
public class Listener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    public Listener() {}

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */

        new ItemDAO(true).updateExpiredItems();

        TimerTask task = new TimerTask() {
            @Override
            public void run () {
                new ItemDAO(true).updateExpiredItems();
            }
        };
        Timer timer = new Timer();
        Calendar calendar = Calendar.getInstance();
        int sec = calendar.get(Calendar.SECOND);
        timer.scheduleAtFixedRate (task, 1000*(60 - sec), 1000*60);
    }

    public void contextDestroyed(ServletContextEvent sce) {}

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {}

    public void sessionDestroyed(HttpSessionEvent se) {}

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------
    public void attributeAdded(HttpSessionBindingEvent sbe) {}

    public void attributeRemoved(HttpSessionBindingEvent sbe) {}

    public void attributeReplaced(HttpSessionBindingEvent sbe) {}
}
