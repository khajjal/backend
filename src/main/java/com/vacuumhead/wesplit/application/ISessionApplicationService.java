package com.vacuumhead.wesplit.application;

import com.vacuumhead.wesplit.constants.AccountCodes;
import com.vacuumhead.wesplit.responseobject.SessionWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: pratyushverma
 * Date: 20/02/13
 * Time: 2:12 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ISessionApplicationService {
    AccountCodes createUser(String user, String password);
    public AccountCodes checkCredentials(String username, String password);
    AccountCodes checkExistUser(String username);
    AccountCodes loginUser(String username, String password);
    AccountCodes checkAlreadyLogged(String username);
    AccountCodes logoutUser(String username);

    SessionWrapper createSession(HttpServletRequest request);

    SessionWrapper addDataToSession(HttpSession session, Map dataJson);

    SessionWrapper getDataFromSession(HttpSession session);
}
