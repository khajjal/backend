package com.vacuumhead.wesplit.dao;

import com.vacuumhead.wesplit.tables.User;
import com.vacuumhead.wesplit.tables.UserAccount;

import javax.persistence.EntityManager;

/**
 * Created with IntelliJ IDEA.
 * User: shuklar
 * Date: 3/24/13
 * Time: 1:09 AM
 * To change this template use File | Settings | File Templates.
 */
public interface IUserAccountDao {
    public UserAccount retrieveUserAccount(EntityManager entityManager,Integer id);
    public UserAccount retrieveUserAccount(EntityManager entityManager,String userName);
    public void createUserAccount(EntityManager entityManager,UserAccount userAccount);
    public void updateUserAccount(EntityManager entityManager,UserAccount userAccount);
    public void deleteUserAccount(EntityManager entityManager,UserAccount userAccount);
}