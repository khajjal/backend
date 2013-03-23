package com.vacuumhead.wesplit.dao;

import com.vacuumhead.wesplit.constants.AccountCodes;

/**
 * Created with IntelliJ IDEA.
 * User: shuklar
 * Date: 3/24/13
 * Time: 2:05 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ISessionDao {

    public AccountCodes checkExistUser(String username);
    public AccountCodes createUser(String username, String password);
    public AccountCodes checkAlreadyLogged(String username);
    public AccountCodes setUserLoggedIn(String username);
    public AccountCodes logoutUser(String username);
    public AccountCodes checkCredentials(String username, String password);
}
