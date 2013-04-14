package com.vacuumhead.wesplit.application;

import com.vacuumhead.wesplit.constants.AccountCodes;
import com.vacuumhead.wesplit.tables.Group;
import com.vacuumhead.wesplit.tables.User;

import java.util.List;

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
    public List<Group> retrieveAllGroupForUser(Integer accountId);
    public User retrieveUser(Integer userId);
}
