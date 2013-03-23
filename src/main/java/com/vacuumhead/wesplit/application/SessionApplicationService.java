package com.vacuumhead.wesplit.application;

import com.vacuumhead.wesplit.constants.AccountCodes;
import com.vacuumhead.wesplit.constants.SessionConstants;
import com.vacuumhead.wesplit.dao.IUserAccountDaoOriginal;
import com.vacuumhead.wesplit.dao.UserAccountDaoOriginal;
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
    private IUserAccountDaoOriginal userAccountDao;

    public SessionApplicationService() {
        userAccountDao = new UserAccountDaoOriginal();
    }

    public AccountCodes createUser(String user, String password) {

        if(checkExistUser(user).equals(AccountCodes.ACCOUNT_ALREADY_EXIST)) {
            return AccountCodes.ACCOUNT_ALREADY_EXIST;
        }
        return userAccountDao.createUser(user, password);
    }

    public AccountCodes checkExistUser(String username) {
        return userAccountDao.checkExistUser(username);
    }

    public AccountCodes loginUser(String username, String password) {
        if(checkCredentials(username, password).equals(AccountCodes.CREDENTIALS_INVALID)) {
            return AccountCodes.CREDENTIALS_INVALID;
        }
        if(checkAlreadyLogged(username).equals(AccountCodes.USER_ALREADY_LOGGED_IN)) {
            return AccountCodes.USER_ALREADY_LOGGED_IN;
        }

        return userAccountDao.setUserLoggedIn(username);
    }

    public AccountCodes checkCredentials(String username, String password) {
        return userAccountDao.checkCredentials(username, password);
    }

    public AccountCodes logoutUser(String username) {
        if(checkAlreadyLogged(username).equals(AccountCodes.USER_NOT_LOGGED_IN)) {
            return AccountCodes.USER_NOT_LOGGED_IN;
        }
        return userAccountDao.logoutUser(username);
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

    private void invalidateSession(HttpSession session) {
        session.invalidate();
    }

    public AccountCodes checkAlreadyLogged(String username) {
        return userAccountDao.checkAlreadyLogged(username);
    }
}
