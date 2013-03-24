package com.vacuumhead.wesplit.application;

import com.vacuumhead.wesplit.constants.AccountCodes;

/**
 * Created by IntelliJ IDEA.
 * User: pratyushverma
 * Date: 24/03/13
 * Time: 1:23 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IUserServiceApplicationLogic {

    public AccountCodes createUser(String username, String password);
    public AccountCodes loginUser(String username, String password);
    public AccountCodes checkIfUserExist(String username);
}
