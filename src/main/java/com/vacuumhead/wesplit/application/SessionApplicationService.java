package com.vacuumhead.wesplit.application;

import com.vacuumhead.wesplit.constants.AccountCodes;
import com.vacuumhead.wesplit.dao.ISessionDao;
import com.vacuumhead.wesplit.dao.SessionDao;

/**
 * Created with IntelliJ IDEA.
 * User: pratyushverma
 * Date: 20/02/13
 * Time: 2:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class SessionApplicationService implements ISessionApplicationService {
    private ISessionDao sessionDao;

    public SessionApplicationService() {
        sessionDao = new SessionDao();
    }

    public AccountCodes createUser(String user, String password) {

        if(checkExistUser(user).equals(AccountCodes.ACCOUNT_ALREADY_EXIST)) {
            return AccountCodes.ACCOUNT_ALREADY_EXIST;
        }
        return sessionDao.createUser(user, password);
    }

    public AccountCodes checkExistUser(String username) {
        return sessionDao.checkExistUser(username);
    }

    public AccountCodes loginUser(String username, String password) {
        if(checkCredentials(username, password).equals(AccountCodes.CREDENTIALS_INVALID)) {
            return AccountCodes.CREDENTIALS_INVALID;
        }
        if(checkAlreadyLogged(username).equals(AccountCodes.USER_ALREADY_LOGGED_IN)) {
            return AccountCodes.USER_ALREADY_LOGGED_IN;
        }

        return sessionDao.setUserLoggedIn(username);
    }

    public AccountCodes checkCredentials(String username, String password) {
        return sessionDao.checkCredentials(username, password);
    }

    public AccountCodes logoutUser(String username) {
        if(checkAlreadyLogged(username).equals(AccountCodes.USER_NOT_LOGGED_IN)) {
            return AccountCodes.USER_NOT_LOGGED_IN;
        }
        return sessionDao.logoutUser(username);
    }

    public AccountCodes checkAlreadyLogged(String username) {
        return sessionDao.checkAlreadyLogged(username);
    }
}
