package com.vacuumhead.wesplit.application;

import com.vacuumhead.wesplit.constants.AccountCodes;

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
}
