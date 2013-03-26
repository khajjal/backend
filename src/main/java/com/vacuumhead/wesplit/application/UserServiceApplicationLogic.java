package com.vacuumhead.wesplit.application;

import com.vacuumhead.wesplit.constants.AccountCodes;
import com.vacuumhead.wesplit.dao.IUserAccountDao;
import com.vacuumhead.wesplit.dao.IUserDao;
import com.vacuumhead.wesplit.dao.UserAccountDao;
import com.vacuumhead.wesplit.tables.Group;
import com.vacuumhead.wesplit.tables.User;
import com.vacuumhead.wesplit.tables.UserAccount;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: pratyushverma
 * Date: 24/03/13
 * Time: 12:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserServiceApplicationLogic implements IUserServiceApplicationLogic {

    private IUserAccountDao userAccountDao;
    private IUserDao userDao;

    public UserServiceApplicationLogic(IUserAccountDao userAccountDao, IUserDao userDao) {
        this.userAccountDao = userAccountDao;
        this.userDao = userDao;
    }

    public UserServiceApplicationLogic(UserAccountDao userAccountDao) {
    }

    public AccountCodes createUser(String username, String password) {
        if (checkExistUser(username)) {
            return AccountCodes.ACCOUNT_ALREADY_EXIST;
        }
        UserAccount userAccount=  new UserAccount(username, password);
        User user=new User();
        userAccount.setUserEmbedded(user);
        user.setUserAccountEmbedded(userAccount);
        userAccountDao.createUserAccount(userAccount);
        return AccountCodes.ACCOUNT_CREATION_SUCCESSFUL;
    }

    public AccountCodes loginUser(String username, String password) {

        UserAccount userInfo = retrieveUserAccount(username);
        if (userInfo == null) {
            return AccountCodes.ACCOUNT_DOES_NOT_EXIST;
        }

        if (userInfo.getPassword().equals(password)) {
            return AccountCodes.CREDENTIALS_VALID;
        } else {
            return AccountCodes.CREDENTIALS_INVALID;
        }
    }

    public AccountCodes checkIfUserExist(String username) {
        return checkExistUser(username) ? AccountCodes.ACCOUNT_ALREADY_EXIST : AccountCodes.ACCOUNT_DOES_NOT_EXIST;
    }

    public List<Group> retrieveAllGroupForUser(Integer accountId) {
        Map<String, Group> groupMap = new HashMap<String, Group>();

        User user = userDao.retrieveUserById(accountId);
        return user.getGroupMemberList();
    }

    private boolean checkExistUser(String username) {
        return userAccountDao.retrieveUserAccount(username) != null;
    }

    private UserAccount retrieveUserAccount(String username) {
        return userAccountDao.retrieveUserAccount(username);
    }

    private UserAccount retrieveUserAccount(Integer accountId) {
        return userAccountDao.retrieveUserAccount(accountId);
    }
}
