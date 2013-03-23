package com.vacuumhead.wesplit.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: pratyushverma
 * Date: 23/03/13
 * Time: 3:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class SessionListener implements HttpSessionListener {
    static int activeConnections = 0;
    static Map<String, HttpSessionEvent> activeUsers = new HashMap<String, HttpSessionEvent>();

    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        activeConnections++;
        HttpSession session = httpSessionEvent.getSession();
        activeUsers.put(session.getId(), httpSessionEvent);
        Iterator it = activeUsers.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, HttpSessionEvent> keyVal = (Map.Entry<String, HttpSessionEvent>) it.next();
            System.out.println(keyVal.getKey() + " . " + keyVal.getValue().getSession().getLastAccessedTime());
        }
    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        activeConnections--;
        activeUsers.remove(httpSessionEvent);
    }
}
