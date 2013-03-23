package com.vacuumhead.wesplit.dao;

import com.vacuumhead.wesplit.constants.AccountCodes;

/**
 * Created by IntelliJ IDEA.
 * User: pratyushverma
 * Date: 04/02/13
 * Time: 11:33 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IUserAccountDao {

    public AccountCodes checkExistUser(String username);
    public AccountCodes createUser(String username, String password);
    public AccountCodes checkAlreadyLogged(String username);
    public AccountCodes setUserLoggedIn(String username);
    public AccountCodes logoutUser(String username);
    public AccountCodes checkCredentials(String username, String password);

}
