package net.golovach.example.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Enumeration;

public class SessionPersistenceListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        /*NOP*/
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        Enumeration<String> iter = session.getAttributeNames();
        while (iter.hasMoreElements()) {
            String attName = iter.nextElement();
            Object attValue = session.getAttribute(attName);
            // do something with attValue (save to DB for example)
        }
    }
}
