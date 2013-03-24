package com.vacuumhead.wesplit.application;

import com.vacuumhead.wesplit.constants.SessionConstants;
import com.vacuumhead.wesplit.responseobject.SessionWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: pratyushverma
 * Date: 20/02/13
 * Time: 2:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class SessionApplicationService implements ISessionApplicationService {

    public SessionApplicationService() {
    }

    public SessionWrapper createSession(HttpServletRequest request) {
        invalidateSession(request.getSession());
        HttpSession session = setNewSessionData(request.getSession());
        SessionWrapper sessionWrapper = new SessionWrapper(session);

        return sessionWrapper;
    }

    private HttpSession setNewSessionData(HttpSession session) {

        session.setMaxInactiveInterval(SessionConstants.maxInactiveTime);
        session.setAttribute("creationTime", session.getCreationTime());
        session.setAttribute("lastAccessTime", session.getLastAccessedTime());
        return session;
    }

    public SessionWrapper addDataToSession(HttpSession session, Map dataJson) {
        if(isSessionNew(session)) {
            session = setNewSessionData(session);
        }
        Iterator it = dataJson.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> keyVal = (Map.Entry<String, String>) it.next();
            session.setAttribute(keyVal.getKey(), keyVal.getValue());
        }
        SessionWrapper sessionWrapper = new SessionWrapper(session);
        return sessionWrapper;
    }

    public SessionWrapper getDataFromSession(HttpSession session) {
        SessionWrapper sessionWrapper = new SessionWrapper(session);
        if(sessionWrapper.getSessionData().isEmpty()) {
            session = setNewSessionData(session);

        }
        return sessionWrapper;
    }

    private boolean isSessionNew(HttpSession session) {
        return session.isNew();
    }

    public void invalidateSession(HttpSession session) {
        session.invalidate();
    }

}
