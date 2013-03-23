package com.vacuumhead.wesplit.responseobject;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: pratyushverma
 * Date: 23/03/13
 * Time: 1:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class SessionWrapper {
    private String sessionId;

    private Map<String, Object> sessionData;

    public Map<String, Object> getSessionData() {
        return sessionData;
    }

    public void setSessionData(Map<String, Object> sessionData) {
        this.sessionData = sessionData;
    }

    public SessionWrapper(HttpSession session) {
        sessionData = new HashMap<String, Object>();
        this.sessionId = session.getId();
        Enumeration sessionMapData = session.getAttributeNames();
        while (sessionMapData.hasMoreElements()) {
            String key = (String) sessionMapData.nextElement();
            Object value = session.getAttribute(key);
            System.out.println(key + " . " + value);
            this.sessionData.put(key, value);
        }
    }

    @Override
    public String toString() {
        return "SessionWrapper{" +
                "sessionId=" + sessionId +
                ", sessionData=" + sessionData +
                '}';
    }
}
