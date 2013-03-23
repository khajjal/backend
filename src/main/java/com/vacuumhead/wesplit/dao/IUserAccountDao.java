package com.vacuumhead.wesplit.dao;

import com.vacuumhead.wesplit.tables.UserAccount;

/**
 * Created with IntelliJ IDEA.
 * User: shuklar
 * Date: 3/24/13
 * Time: 1:09 AM
 * To change this template use File | Settings | File Templates.
 */
public interface IUserAccountDao {
    public UserAccount retriveUserAccountExist(String userName);
    public void createUserAccount(UserAccount userAccount);
    public void updateUserAccount(UserAccount userAccount);
    public void deleteUserAccount(UserAccount userAccount);
}