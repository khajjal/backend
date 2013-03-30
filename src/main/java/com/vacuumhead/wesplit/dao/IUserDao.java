package com.vacuumhead.wesplit.dao;

import com.vacuumhead.wesplit.tables.User;
import com.vacuumhead.wesplit.tables.UserAccount;

import javax.persistence.EntityManager;

/**
 * Created by IntelliJ IDEA.
 * User: pratyushverma
 * Date: 26/03/13
 * Time: 12:30 AM
 * To change this template use File | Settings | File Templates.
 */
public interface IUserDao {

    public User retrieveUserById(EntityManager entityManager,Integer accountId);
    public void updateUser(EntityManager entityManager,User user);
}
