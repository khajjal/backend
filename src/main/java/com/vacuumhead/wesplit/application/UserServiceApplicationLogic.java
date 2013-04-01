package com.vacuumhead.wesplit.application;

import com.vacuumhead.wesplit.constants.AccountCodes;
import com.vacuumhead.wesplit.dao.IUserAccountDao;
import com.vacuumhead.wesplit.dao.IUserDao;
import com.vacuumhead.wesplit.dao.UserAccountDao;
import com.vacuumhead.wesplit.tables.Group;
import com.vacuumhead.wesplit.tables.User;
import com.vacuumhead.wesplit.tables.UserAccount;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
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
     @PersistenceUnit
private EntityManagerFactory emf;

public void setEmf(EntityManagerFactory emf) {
    this.emf = emf;
}
    private IUserAccountDao userAccountDao;
    private IUserDao userDao;

    public UserServiceApplicationLogic(IUserAccountDao userAccountDao, IUserDao userDao) {
        this.userAccountDao = userAccountDao;
        this.userDao = userDao;
    }

    public UserServiceApplicationLogic(UserAccountDao userAccountDao) {
    }

    public AccountCodes createUser(String username, String password) {
        EntityManager entityManager = emf.createEntityManager();
        /*try{
            entityManager.getTransaction().begin();
        */    if (checkExistUser(entityManager,username)) {
                return AccountCodes.ACCOUNT_ALREADY_EXIST;
            }
            UserAccount userAccount=  new UserAccount(username, password);
            User user=new User();
            userAccount.setUserEmbedded(user);
            user.setUserAccountEmbedded(userAccount);
            userAccountDao.createUserAccount(entityManager,userAccount);
            return AccountCodes.ACCOUNT_CREATION_SUCCESSFUL;
        /*}     finally {
            entityManager.getTransaction().commit();
        }*/

    }

    public AccountCodes loginUser(String username, String password) {
        EntityManager entityManager = emf.createEntityManager();
        UserAccount userInfo = retrieveUserAccount(entityManager,username);
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
        EntityManager entityManager = emf.createEntityManager();
        return checkExistUser(entityManager,username) ? AccountCodes.ACCOUNT_ALREADY_EXIST : AccountCodes.ACCOUNT_DOES_NOT_EXIST;
    }

    public List<Group> retrieveAllGroupForUser(Integer accountId) {
        EntityManager entityManager = emf.createEntityManager();
        Map<String, Group> groupMap = new HashMap<String, Group>();

        User user = userDao.retrieveUserById(entityManager,accountId);
        return user.getGroupMemberList();
    }

    private boolean checkExistUser(EntityManager entityManager, String username) {
        return userAccountDao.retrieveUserAccount(entityManager,username) != null;
    }

    private UserAccount retrieveUserAccount(EntityManager entityManager,String username) {
        return userAccountDao.retrieveUserAccount(entityManager,username);
    }

    private UserAccount retrieveUserAccount(EntityManager entityManager,Integer accountId) {
        return userAccountDao.retrieveUserAccount(entityManager,accountId);
    }
}
